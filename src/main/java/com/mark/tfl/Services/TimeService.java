package com.mark.tfl.Services;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeService {

    public static String getCurrentTime() {
        return getCurrentLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    private static LocalTime getCurrentLocalTime(){
        return LocalTime.now();
    }
}