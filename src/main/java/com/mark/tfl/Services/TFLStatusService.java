package com.mark.tfl.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mark.tfl.Models.*;
import com.mark.tfl.Utils.TFLEndpointDataUtils;
import com.mark.tfl.Utils.TFLServiceHIstoryUtils;
import com.mark.tfl.Utils.TimeUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class TFLStatusService {

    private static final Logger log = LoggerFactory.getLogger(TFLStatusService.class);

    private TFLMongoRepo tflRepository;
    private ObjectMapper objectMapper = new ObjectMapper();
    private URL url;

    @Autowired
    public TFLStatusService(TFLMongoRepo tflRepository) throws MalformedURLException {
        this.tflRepository = tflRepository;
        url = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
    }

    @Autowired
    private TimeUtility timeUtility;

    @Autowired
    private TFLServiceHIstoryUtils tflServiceHIstoryUtils;

    @Autowired
    private TFLEndpointDataUtils tflEndpointDataUtils;

    @PostConstruct
    public void initialCall() {
        scheduleAPICall();
    }

    public void scheduleAPICall() {
        log.info("Updating local data on tube statuses...");
        runAllStatusChecks();
        log.info("Update complete");
    }

    private void runAllStatusChecks() {
        callTFLEndpoint();
        runAllLineStatusesCheck();
        runLinesWithIssuesCheck();
        saveToMongo();
    }

    public void runManualStatusChecks() {
        callTFLEndpoint();
        runAllLineStatusesCheck();
        runLinesWithIssuesCheck();
    }

    private void saveToMongo() {
        String currentDateAndTime = timeUtility.getCurrentDateAndTimeAsString();
        TFLMongoObject mongoTFLObject = new TFLMongoObject(currentDateAndTime, tflEndpointDataUtils.getAllLineStatuses());
        log.info("Saving to mongo...");
        tflRepository.save(mongoTFLObject);
    }

    public List<TFLLineHistoryObject> getLineStatusHistoryFromMongo(String lineName) {
        return tflServiceHIstoryUtils.getLineStatusHistoryFromMongo(lineName);
    }

    public List<String> getLineStatusesForLine(String lineName) {
        return tflServiceHIstoryUtils.getLineStatusesForLine(lineName);
    }

    public long getHistoryCount(String lineName) {
        return tflServiceHIstoryUtils.getLineStatusHistoryFromMongo(lineName).size();
    }

    public long getGoodHistoryCount(String lineName) {
        return tflServiceHIstoryUtils.getGoodHistoryCount(lineName);
    }

    public long getNotGoodHistoryCount(String lineName) {
        return tflServiceHIstoryUtils.getNotGoodHistoryCount(lineName);
    }

    private void callTFLEndpoint() {
        try {
            tflEndpointDataUtils.setTflRawLineStatuses(objectMapper.readValue(url, new TypeReference<List<TFLRawResponseObject>>() {
            }));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void runAllLineStatusesCheck() {
        tflEndpointDataUtils.setAllLineStatuses();
    }

    private void runLinesWithIssuesCheck() {
        tflEndpointDataUtils.setLinesWithIssues();
    }

    public List<TFLLineStatusObject> getLineStatuses() {
        List<TFLLineStatusObject> lineStatuses = tflEndpointDataUtils.getAllLineStatuses();
        if (lineStatuses == null) {
            scheduleAPICall();
        }
        return lineStatuses;
    }

    public List<TFLLineStatusObject> getLineIssues() {
        List<TFLLineStatusObject> lineStatusesIssues = tflEndpointDataUtils.getLinesWithIssues();
        if (lineStatusesIssues == null) {
            scheduleAPICall();
        }
        return lineStatusesIssues;
    }
}