package com.mark.tfl.Utils;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class TFLGraphUtils {

    private List<String> statuses;
    private List<String> distinctStatuses;

    public Object[][] populateChart(List<String> statuses) {
        this.statuses = statuses;
        listDistinctStatuses();
        int rows = distinctStatuses.size() + 1, columns = 2;
        Object[][] result = new Object[rows][columns];

        result[0][0] = "Status";
        result[0][1] = "Frequency";

        IntStream.range(1, rows)
                .forEach(r -> IntStream.range(0, columns)
                        .forEach(c -> {
                            if (c % 2 == 0) {
                                result[r][c] = distinctStatuses.get(r - 1);
                            } else {
                                result[r][c] = findFrequencyOfStatus(distinctStatuses.get(r - 1));
                            }
                        })
                );
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
