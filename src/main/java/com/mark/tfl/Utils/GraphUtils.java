package com.mark.tfl.Utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GraphUtils {

    private List<String> statuses;
    private List<String> distinctStatuses;

    public Object[][] populateChart(List<String> statuses) {
        this.statuses = statuses;
        listDistinctStatuses();
        Object[][] result = new Object[distinctStatuses.size() + 1][2];

        result[0][0] = "Status";
        result[0][1] = "Frequency";

        for (int row = 1; row <= distinctStatuses.size(); row++) {
            for (int column = 0; column < 2; column++) {
                if (column == 0) {
                    result[row][column] = distinctStatuses.get(row - 1);
                } else {
                    result[row][column] = findFrequencyOfStatus(distinctStatuses.get(row - 1));
                }
            }
        }
        return result;
    }

    private int findFrequencyOfStatus(String statusType) {
        return (int) statuses.stream()
                .filter(statusType::equals)
                .count();
    }

    private void listDistinctStatuses() {
        distinctStatuses = new ArrayList<>();
        statuses.stream()
                .filter(status -> !distinctStatuses.contains(status))
                .forEach(status -> distinctStatuses.add(status));
    }
}
