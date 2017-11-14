package com.mark.tfl.Utils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class TimeUtility {

    public String getCurrentTimeAsString() {
        return getCurrentLocalTime().format(DateTimeFormatter.ofPattern("h:mm a"));
    }

    public String getCurrentDateAndTimeAsString() {
        return getCurrentLocalDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yyyy h:mm a"));
    }

    private LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    private LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }


}