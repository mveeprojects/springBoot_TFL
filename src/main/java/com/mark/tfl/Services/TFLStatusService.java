package com.mark.tfl.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.tfl.Models.MongoTFLRepository;
import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Models.MongoTFLObject;
import com.mark.tfl.Models.TFLResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TFLStatusService {

    private MongoTFLRepository tflRepository;

    private static final Logger log = LoggerFactory.getLogger(TFLStatusService.class);
    private ObjectMapper objectMapper;
    private List<LineStatus> allLineStatuses;
    private List<LineStatus> linesWithIssues;
    private List<TFLResponse> tflRawLineStatuses;

    @Autowired
    public TFLStatusService(MongoTFLRepository tflRepository) {
        this.tflRepository = tflRepository;
        objectMapper = new ObjectMapper();
        linesWithIssues = new ArrayList<>();
        tflRawLineStatuses = new ArrayList<>();
        allLineStatuses = new ArrayList<>();
    }

    @PostConstruct
    public void initialCall(){
        scheduleAPICall();
    }

    public void scheduleAPICall() {
        log.info("Updating local data on tube statuses...");
        runAllStatusChecks();
        log.info("Update complete");
    }

    private void runAllStatusChecks() {
        if (tflRawLineStatuses.isEmpty() || allLineStatuses.isEmpty() || linesWithIssues.isEmpty()) {
            callTFLEndpoint();
            getAllLineStatuses();
            getLinesWithIssues();
            saveToMongo();
        }
    }

    private void saveToMongo(){
        LocalTime time = LocalTime.now();
        String ttime = time.format(DateTimeFormatter.ISO_LOCAL_TIME);
        MongoTFLObject mongoTFLObject = new MongoTFLObject(ttime, allLineStatuses);
        tflRepository.save(mongoTFLObject);
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
        List<LineStatus> newLineStatuses = new ArrayList<>();
        for (TFLResponse tflResponse : tflRawLineStatuses) {
            LineStatus lineStatus = new LineStatus(tflResponse.getName(), tflResponse.getStatus());
            newLineStatuses.add(lineStatus);
        }
        setAllLineStatuses(newLineStatuses);
    }

    private void getLinesWithIssues() {
        List<LineStatus> newlinesWithIssues = new ArrayList<>();
        for (LineStatus lineStatus : allLineStatuses) {
            String status = lineStatus.getLineStatus();
            if (!Objects.equals(status, "Good Service")) {
                newlinesWithIssues.add(lineStatus);
            }
        }
        setLinesWithIssues(newlinesWithIssues);
    }

    private void setAllLineStatuses(List<LineStatus> allLineStatuses) {
        this.allLineStatuses = allLineStatuses;
    }

    private void setLinesWithIssues(List<LineStatus> linesWithIssues) {
        this.linesWithIssues = linesWithIssues;
    }

    public List<LineStatus> getLineStatuses() {
        if (allLineStatuses == null) {
            scheduleAPICall();
        }
        return allLineStatuses;
    }

    public List<LineStatus> getLineIssues() {
        if (linesWithIssues == null) {
            scheduleAPICall();
        }
        return linesWithIssues;
    }
}
