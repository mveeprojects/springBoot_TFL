package com.mark.tfl.Models;

public class LineStatus {
    private String lineName;
    private String lineStatus;

    public LineStatus(String lineName, String lineStatus) {
        this.lineName = lineName;
        this.lineStatus = lineStatus;
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineStatus() {
        return lineStatus;
    }
}
