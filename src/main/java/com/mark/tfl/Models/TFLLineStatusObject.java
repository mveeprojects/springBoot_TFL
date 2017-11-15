package com.mark.tfl.Models;

public class TFLLineStatusObject {
    private String lineName;
    private String lineStatus;
    private boolean nightTube;

    public TFLLineStatusObject(String lineName, String lineStatus) {
        this.lineName = lineName;
        this.lineStatus = lineStatus;
        this.nightTube = determineNightTube(lineName);
    }

    public String getLineName() {
        return lineName;
    }

    public String getLineStatus() {
        return lineStatus;
    }

    public boolean isNightTube() {
        return nightTube;
    }

    private boolean determineNightTube(String lineName) {
        switch (lineName) {
            case "Victoria":
            case "Central":
            case "Jubilee":
            case "Northern":
            case "Piccadilly":
                return true;
            default:
                return false;
        }
    }

    @Override
    public String toString() {
        return "LineStatus{" +
                "lineName='" + lineName + '\'' +
                ", lineStatus='" + lineStatus + '\'' +
                ", nightTube=" + nightTube +
                '}';
    }
}