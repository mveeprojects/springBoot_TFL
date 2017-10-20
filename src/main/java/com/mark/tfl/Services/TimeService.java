package com.mark.tfl.Services;

import java.time.LocalTime;

public class TimeService {

    public static String getCurrentTime() {
        LocalTime currentTime = LocalTime.now();
        return String.format("%02d:%02d:%02d", currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
    }
}
