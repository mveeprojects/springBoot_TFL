package com.mark.tfl.Utils;

import com.mark.tfl.Models.TFLLineStatusObject;
import com.mark.tfl.Models.TFLRawResponseObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TFLEndpointDataUtils {

    private List<TFLRawResponseObject> tflRawLineStatuses = new ArrayList<>();
    private List<TFLLineStatusObject> allLineStatuses = new ArrayList<>();
    private List<TFLLineStatusObject> linesWithIssues = new ArrayList<>();

    public List<TFLLineStatusObject> getAllLineStatuses() {
        return allLineStatuses;
    }

    public List<TFLLineStatusObject> getLinesWithIssues() {
        return linesWithIssues;
    }

    public void setTflRawLineStatuses(List<TFLRawResponseObject> tflRawLineStatuses) {
        this.tflRawLineStatuses = tflRawLineStatuses;
    }

    public void setAllLineStatuses() {
        List<TFLLineStatusObject> newLineStatuses = new ArrayList<>();
        tflRawLineStatuses
                .forEach(result -> newLineStatuses.add(new TFLLineStatusObject(result.getName(), result.getStatus())));
        allLineStatuses = newLineStatuses;
    }

    public void setLinesWithIssues() {
        List<TFLLineStatusObject> newlinesWithIssues = new ArrayList<>();
        allLineStatuses
                .stream()
                .filter(status -> !"Good Service".equals(status.getLineStatus()))
                .forEach(newlinesWithIssues::add);
        linesWithIssues = newlinesWithIssues;
    }
}
