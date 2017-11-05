package com.mark.tfl.Models;

public class TFLLineHistoryObject {

    private String time;
    private String lineName;
    private String lineStatus;

    public String getTime() {
        return time;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineStatus() {
        return lineStatus;
    }

    public TFLLineHistoryObject(String time, String lineName, String lineStatus) {
        this.time = time;
        this.lineName = lineName;
        this.lineStatus = lineStatus;
    }
}
