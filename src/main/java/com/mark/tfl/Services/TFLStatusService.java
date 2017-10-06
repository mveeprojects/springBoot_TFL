package com.mark.tfl.Services;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Models.TFLResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class TFLStatusService {

    private ObjectMapper objectMapper;
    private List<LineStatus> allLineStatuses;
    private List<LineStatus> linesWithIssues;
    private List<TFLResponse> tflResp;

    public TFLStatusService() {
        objectMapper = new ObjectMapper();
        linesWithIssues = new ArrayList<>();
        tflResp = new ArrayList<>();
        allLineStatuses = new ArrayList<>();
    }

    public void scheduleAPICall() {
        System.out.println("Updating local data on tube statuses...");
        runAllStatusChecks();
        System.out.println("Update complete");
    }

    public List<LineStatus> getLineStatuses() {
        return allLineStatuses;
    }

    public List<LineStatus> getLineIssues() {
        return linesWithIssues;
    }

    public LineStatus checkLineStatus(String line) {
        runAllStatusChecks();
        for (LineStatus lineStatus : allLineStatuses) {
            if (lineStatus.getLineName().equalsIgnoreCase(line)) {
                return lineStatus;
            }
        }
        return new LineStatus("Error", "Line \"" + line + "\" is not recognised");
    }

    private void runAllStatusChecks() {
        if (tflResp.isEmpty() || allLineStatuses.isEmpty() || linesWithIssues.isEmpty()) {
            getTFLResponse();
            getAllLineStatuses();
            getLinesWithIssues();
        }
    }

    private void getTFLResponse() {
        try {
            URL url = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
            tflResp = objectMapper.readValue(url, new TypeReference<List<TFLResponse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getAllLineStatuses() {
        for (TFLResponse tflResponse : tflResp) {
            LineStatus lineStatus = new LineStatus(tflResponse.getName(), tflResponse.getStatus());
            allLineStatuses.add(lineStatus);
        }
    }

    private void getLinesWithIssues() {
        for (LineStatus lineStatus : allLineStatuses) {
            String status = lineStatus.getLineStatus();
            if (!Objects.equals(status, "Good Service")) {
                linesWithIssues.add(lineStatus);
            }
        }
    }
}