package com.mark.tfl.Controllers;

import com.mark.tfl.Models.TFLLineHistoryObject;
import com.mark.tfl.Services.TFLStatusService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.mark.tfl.Utils.MathUtils.getPercentage;

class HistoryStatsObject {

    private TFLStatusService tflStatusService;

    private List<TFLLineHistoryObject> lineHistory;
    private long historyCount, goodHistoryCount, notGoodHistoryCount;
    private double percentageUptime;
    private List<String> statuses;

    @Autowired
    HistoryStatsObject(String lineName, TFLStatusService tflStatusService) {
        this.tflStatusService = tflStatusService;
        lineHistory = this.tflStatusService.getLineStatusHistoryFromMongo(lineName);
        this.historyCount = this.tflStatusService.getHistoryCount(lineName);
        this.goodHistoryCount = this.tflStatusService.getGoodHistoryCount(lineName);
        this.notGoodHistoryCount = this.tflStatusService.getNotGoodHistoryCount(lineName);
        this.percentageUptime = getPercentage(historyCount, goodHistoryCount);
        this.statuses = this.tflStatusService.getLineStatusesForLine(lineName);
    }

    List<TFLLineHistoryObject> getLineHistory() {
        return lineHistory;
    }

    long getHistoryCount() {
        return historyCount;
    }

    long getGoodHistoryCount() {
        return goodHistoryCount;
    }

    long getNotGoodHistoryCount() {
        return notGoodHistoryCount;
    }

    double getPercentageUptime() {
        return percentageUptime;
    }

    List<String> getStatuses() {
        return statuses;
    }
}
