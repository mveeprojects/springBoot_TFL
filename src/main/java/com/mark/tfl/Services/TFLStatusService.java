package com.mark.tfl.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.tfl.Models.*;
import com.mark.tfl.Utils.TimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TFLStatusService {

    private TFLMongoRepo tflRepository;

    private static final Logger log = LoggerFactory.getLogger(TFLStatusService.class);
    private ObjectMapper objectMapper;
    private List<TFLLineStatus> allLineStatuses;
    private List<TFLLineStatus> linesWithIssues;
    private List<TFLResponse> tflRawLineStatuses;
    private List<TFLLineHistoryObject> lineHistories;

    @Autowired
    public TFLStatusService(TFLMongoRepo tflRepository) {
        this.tflRepository = tflRepository;
        objectMapper = new ObjectMapper();
        linesWithIssues = new ArrayList<>();
        tflRawLineStatuses = new ArrayList<>();
        allLineStatuses = new ArrayList<>();
        lineHistories = new ArrayList<>();
    }

    @PostConstruct
    public void initialCall() {
        scheduleAPICall();
    }

    public void scheduleAPICall() {
        log.info("Updating local data on tube statuses...");
//        TODO: Commenting out to prevent making more calls to TFL
        runAllStatusChecks();
        log.info("Update complete");
    }

    private void runAllStatusChecks() {
        callTFLEndpoint();
        getAllLineStatuses();
        getLinesWithIssues();
        saveToMongo();
    }

    private void saveToMongo() {
        String currentDateAndTime = TimeUtility.getCurrentDateAndTimeAsString();
        TFLMongoObject mongoTFLObject = new TFLMongoObject(currentDateAndTime, allLineStatuses);
        log.info("Saving to mongo...");
        tflRepository.save(mongoTFLObject);
    }

    public List<TFLLineHistoryObject> getLineStatusHistoryFromMongo(String lineName) {
        lineHistories.clear();
        tflRepository.findAll()
                .forEach(tflMongoObject -> tflMongoObject.getStatusList()
                        .forEach(tflLineStatus -> {
                            if (tflLineStatus.getLineName().contains(lineName)) {
                                lineHistories.add(new TFLLineHistoryObject(tflMongoObject.getTime(), tflLineStatus.getLineName(), tflLineStatus.getLineStatus()));
                            }
                        }));
        return lineHistories;
    }

    public List<String> getLineStatusesForLine(String lineName) {

        List<String> statuses = new ArrayList<>();

        for (TFLMongoObject mongoObject : tflRepository.findAll()) {
            for (TFLLineStatus tflLineStatus : mongoObject.getStatusList()) {
                if (Objects.equals(tflLineStatus.getLineName(), lineName)) {
                    statuses.add(tflLineStatus.getLineStatus());
                }
            }
        }

        return statuses;
    }

    public long getHistoryCount() {
        return lineHistories.size();
    }

    public long getGoodHistoryCount() {
        return lineHistories.stream()
                .filter(status -> "Good Service".equals(status.getLineStatus()))
                .count();
    }

    public long getNotGoodHistoryCount() {
        return lineHistories.stream()
                .filter(status -> !"Good Service".equals(status.getLineStatus()))
                .count();
    }

    private void callTFLEndpoint() {
        try {
            URL url = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
            tflRawLineStatuses = objectMapper.readValue(url, new TypeReference<List<TFLResponse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllLineStatuses() {
        List<TFLLineStatus> newLineStatuses = new ArrayList<>();
        tflRawLineStatuses
                .forEach(result -> newLineStatuses.add(new TFLLineStatus(result.getName(), result.getStatus())));
        setAllLineStatuses(newLineStatuses);
    }

    private void getLinesWithIssues() {
        List<TFLLineStatus> newlinesWithIssues = new ArrayList<>();
        allLineStatuses
                .stream()
                .filter(status -> !"Good Service".equals(status.getLineStatus()))
                .forEach(newlinesWithIssues::add);
        setLinesWithIssues(newlinesWithIssues);
    }

    private void setAllLineStatuses(List<TFLLineStatus> allLineStatuses) {
        this.allLineStatuses = allLineStatuses;
    }

    private void setLinesWithIssues(List<TFLLineStatus> linesWithIssues) {
        this.linesWithIssues = linesWithIssues;
    }

    public List<TFLLineStatus> getLineStatuses() {
        if (allLineStatuses == null) {
            scheduleAPICall();
        }
        return allLineStatuses;
    }

    public List<TFLLineStatus> getLineIssues() {
        if (linesWithIssues == null) {
            scheduleAPICall();
        }
        return linesWithIssues;
    }
}