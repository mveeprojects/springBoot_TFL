package com.mark.tfl.Services;

import com.mark.tfl.Models.TFLLineStatus;
import com.mark.tfl.Models.TFLResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TFLEndpointDataUtils {

    private List<TFLResponse> tflRawLineStatuses = new ArrayList<>();
    private List<TFLLineStatus> allLineStatuses = new ArrayList<>();
    private List<TFLLineStatus> linesWithIssues = new ArrayList<>();

    List<TFLLineStatus> getAllLineStatuses() {
        return allLineStatuses;
    }

    List<TFLLineStatus> getLinesWithIssues() {
        return linesWithIssues;
    }

    void setTflRawLineStatuses(List<TFLResponse> tflRawLineStatuses) {
        this.tflRawLineStatuses = tflRawLineStatuses;
    }

    void setAllLineStatuses() {
        List<TFLLineStatus> newLineStatuses = new ArrayList<>();
        tflRawLineStatuses
                .forEach(result -> newLineStatuses.add(new TFLLineStatus(result.getName(), result.getStatus())));
        allLineStatuses = newLineStatuses;
    }

    void setLinesWithIssues() {
        List<TFLLineStatus> newlinesWithIssues = new ArrayList<>();
        allLineStatuses
                .stream()
                .filter(status -> !"Good Service".equals(status.getLineStatus()))
                .forEach(newlinesWithIssues::add);
        linesWithIssues = newlinesWithIssues;
    }
}
