package TubeLineStatus.Services;

import TubeLineStatus.POJOs.LineStatus;
import TubeLineStatus.POJOs.TFLResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public List<LineStatus> getLineStatuses() {
        runAllStatusChecks();
        return allLineStatuses;
    }

    public List<LineStatus> getLineIssues() {
        runAllStatusChecks();
        return linesWithIssues;
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
