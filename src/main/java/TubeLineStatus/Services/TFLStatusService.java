package TubeLineStatus.Services;

import TubeLineStatus.POJOs.LineStatus;
import TubeLineStatus.POJOs.TFLResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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

    public List<LineStatus> getAllLineStatuses() {
        runAllStatusCheck();
        return allLineStatuses;
    }

    public List<LineStatus> getLinesWithIssues() {
        return linesWithIssues;
    }

    private void runAllStatusCheck() {
        if (tflResp.isEmpty() || allLineStatuses.isEmpty()) {
            callTFLAPI();
            getLineStatuses();
        }
    }

    private void callTFLAPI() {
        try {
            URL url = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
            tflResp = objectMapper.readValue(url, new TypeReference<List<TFLResponse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getLineStatuses() {
        for (TFLResponse tflResponse : tflResp) {
            LineStatus lineStatus = new LineStatus(tflResponse.getName(), tflResponse.getStatus());
            allLineStatuses.add(lineStatus);
        }
    }


//    private List<LineStatus> detectLineIssues() {
//
//        if (allLineStatuses.isEmpty()) {
//            getAllLineStatuses = allLineStatuses();
//        }
//
//        if (linesWithIssues.isEmpty()) {
//            getAllLineStatuses();
//        }
//        for (LineStatus lineStatus : linesWithIssues) {
//            String status = lineStatus.getLineStatus();
//            if (!Objects.equals(status, "Good Service")) {
//                linesWithIssues.add(lineStatus);
//            }
//        }
//        return linesWithIssues;
//    }

}
