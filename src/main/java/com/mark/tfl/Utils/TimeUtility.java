package com.mark.tfl.Utils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeUtility {

    public static String getCurrentTimeAsString() {
        return getCurrentLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    public static String getCurrentDateAndTimeAsString(){
        return getCurrentLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a"));
    }

    private static LocalTime getCurrentLocalTime(){
        return LocalTime.now();
    }

    private static LocalDateTime getCurrentLocalDateTime(){
        return LocalDateTime.now();
    }


}