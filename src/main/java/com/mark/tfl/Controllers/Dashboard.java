package com.mark.tfl.Controllers;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

import static org.springframework.util.StringUtils.isEmpty;
@RestController
public class Dashboard {

    @Autowired
    private static TFLStatusService tflStatusService;

    public Dashboard() {
        tflStatusService = new TFLStatusService();
    }

    public static void lastScheduledRuntime(String time) {
        System.out.println("Dashboard - " + time);
        tflStatusService.scheduleAPICall();
    }

    @RequestMapping("/")
    public List<LineStatus> allLinesStatuses() throws IOException {
        return tflStatusService.getLineStatuses();
    }

    @RequestMapping("/issues")
    public List<LineStatus> linesWithIssues() {
        return tflStatusService.getLineIssues();
    }

    @RequestMapping("/checkline")
    public LineStatus checkLineStatus(@RequestParam("line") String line) {
        if (isEmpty(line)) {
            return new LineStatus("Error", "No line name entered");
        }
        return tflStatusService.checkLineStatus(line);
    }
}