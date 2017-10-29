package com.mark.tfl;

import com.mark.tfl.Models.MongoTFLRepository;
import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Services.TFLStatusService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static com.mark.tfl.Services.TimeService.getCurrentTime;
import static java.util.Collections.emptyList;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private MongoTFLRepository tflRepository;

    @InjectMocks
    private TFLStatusService tflStatusService;

    @Test
    public void refreshLineStatusesTest(){
        List<LineStatus> oldStatuses = tflStatusService.getLineStatuses();
        tflStatusService.scheduleAPICall();
        List<LineStatus> newStatuses = tflStatusService.getLineStatuses();

        Assert.assertNotEquals(oldStatuses, newStatuses);
    }

    @Test
    public void refreshLineIssuesTest(){
        List<LineStatus> oldIssues = tflStatusService.getLineIssues();
        tflStatusService.scheduleAPICall();
        List<LineStatus> newIssues = tflStatusService.getLineIssues();

        Assert.assertEquals(emptyList(), oldIssues);
        Assert.assertNotEquals(emptyList(), newIssues);
    }

    @Test
    public void checkTimeTest(){
        String actual = getCurrentTime();
        LocalTime time = LocalTime.now();
        String expected = time.format(DateTimeFormatter.ofPattern("h:mm a"));

        Assert.assertEquals(expected, actual);
    }
}