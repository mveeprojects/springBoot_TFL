package com.mark.tfl.Utils;

import com.mark.tfl.Services.TFLStatusService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class TFLGraphUtils {

    private List<String> statuses;
    private List<String> statuses1;
    private List<String> distinctStatuses;
    private List<String> distinctStatuses1;
    private Object[][] result;
    private Object[][] result1;

    @Autowired
    private TFLStatusService tflStatusService;

    public Object[][] populateStatusGraphs(List<String> lineStatuses) {
        statuses = lineStatuses;
        listDistinctStatuses();
        int rows = distinctStatuses.size() + 1, columns = 2;
        result = new Object[rows][columns];

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

    @Test
    public void myTest() {
        List<String> lineStatuses = new ArrayList<>();
        lineStatuses.add("Good Service");
        lineStatuses.add("Good Service");
        lineStatuses.add("Part Closure");
        lineStatuses.add("Part Closure");
        lineStatuses.add("Part Closure");
        lineStatuses.add("Part Closure");
        lineStatuses.add("Part Closure");
        lineStatuses.add("Part Closure");
        lineStatuses.add("Part Closure");
        populateHistoricalLineChart(lineStatuses);
    }

    public Object[][] populateHistoricalLineChart(List<String> lineStatuses) {
        statuses1 = lineStatuses;
        listDistinctStatuses1();
        int rows = distinctStatuses1.size() + 1, columns = 3;
        result1 = new Object[rows][columns];

//        result1[0][0] = "Month";
        result1[1][0] = "February";
        result1[2][0] = "March   ";
//        result1[0][1] = "Status";
//        result1[0][2] = "Frequency";

        IntStream.range(1, rows)
                .forEach(r -> IntStream.range(1, columns)
                        .forEach(c -> {
                            if (c == 1) {
                                result1[r][c] = distinctStatuses1.get(r - 1);
                            } else if (c == 2) {
                                result1[r][c] = findFrequencyOfStatus1(distinctStatuses1.get(r - 1));
                            }
                        })
                );

//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                System.out.print(result1[i][j] + "\t\t\t");
//            }
//            System.out.println();
//        }

        return result1;
    }

    private int findFrequencyOfStatus(String statusType) {
        return (int) statuses.stream()
                .filter(statusType::equals)
                .count();
    }

    private int findFrequencyOfStatus1(String statusType) {
        return (int) statuses1.stream()
                .filter(statusType::equals)
                .count();
    }

    private void listDistinctStatuses() {
        distinctStatuses = new ArrayList<>();
        statuses.stream()
                .filter(status -> !distinctStatuses.contains(status))
                .forEach(status -> distinctStatuses.add(status));
    }

    private void listDistinctStatuses1() {
        distinctStatuses1 = new ArrayList<>();
        statuses1.stream()
                .filter(status -> !distinctStatuses1.contains(status))
                .forEach(status -> distinctStatuses1.add(status));
    }
}
