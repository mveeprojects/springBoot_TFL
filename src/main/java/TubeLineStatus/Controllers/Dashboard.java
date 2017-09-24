package TubeLineStatus.Controllers;

import TubeLineStatus.POJOs.LineStatus;
import TubeLineStatus.POJOs.TFLResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class Dashboard {

    private ObjectMapper objectMapper;
    private List<LineStatus> lineStatusList;
    private List<LineStatus> lineIssuesList;
    List<TFLResponse> tflResponse;

    public Dashboard() {
        objectMapper = new ObjectMapper();
        lineStatusList = new ArrayList<>();
        lineIssuesList = new ArrayList<>();
    }

    @RequestMapping("/")
    public List<LineStatus> appStatus() throws IOException {
        if (lineStatusList.isEmpty()) {
            callTFLAPI();
        }
        allLineStatuses();
        return lineStatusList;
    }

    @RequestMapping("/issues")
    public List<LineStatus> affectedLines() {
        if (lineIssuesList.isEmpty()) {
            detectLineIssues();
        }
        return lineIssuesList;
    }

    private void allLineStatuses() {
        for (TFLResponse t : tflResponse) {
            LineStatus lineStatus = new LineStatus(t.getName(), t.getStatus());
            lineStatusList.add(lineStatus);
        }
    }

    private void callTFLAPI() {
        try {
            URL url = new URL("https://api.tfl.gov.uk/line/mode/tube/status");
            tflResponse = objectMapper.readValue(url, new TypeReference<List<TFLResponse>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void detectLineIssues() {
        if (lineStatusList.isEmpty()) {
            callTFLAPI();
            allLineStatuses();
        }
        for (LineStatus lineStatus : lineStatusList) {
            String status = lineStatus.getLineStatus();
            if (!Objects.equals(status, "Good Service")) {
                lineIssuesList.add(lineStatus);
            }
        }
    }
}
