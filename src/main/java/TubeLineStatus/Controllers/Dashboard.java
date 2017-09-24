package TubeLineStatus.Controllers;

import TubeLineStatus.POJOs.LineStatus;
import TubeLineStatus.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Dashboard {

    @Autowired
    private TFLStatusService tflStatusService;

    public Dashboard() {
        tflStatusService = new TFLStatusService();
    }

    @RequestMapping("/")
    public List<LineStatus> allLinesStatuses() throws IOException {
        return tflStatusService.getLineStatuses();
    }

    @RequestMapping("/issues")
    public List<LineStatus> linesWithIssues() {
        return tflStatusService.getLineIssues();
    }
}
