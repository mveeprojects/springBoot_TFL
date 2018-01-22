package com.mark.tfl;

import com.mark.tfl.Models.TFLMongoRepo;
import com.mark.tfl.Services.TFLStatusService;
import com.mark.tfl.Utils.TimeUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static com.mark.tfl.Utils.MathUtils.getPercentage;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private TFLMongoRepo tflRepository;

    @InjectMocks
    private TFLStatusService tflStatusService;

    @InjectMocks
    private TimeUtility timeUtility;

//    @Test
//    public void refreshLineStatusesTest(){
//        List<TFLLineStatusObject> oldStatuses = tflStatusService.getLineStatuses();
//        tflStatusService.scheduleAPICall();
//        List<TFLLineStatusObject> newStatuses = tflStatusService.getLineStatuses();
//
//        Assert.assertNotEquals(oldStatuses, newStatuses);
//    }

//    @Test
//    public void refreshLineIssuesTest(){
//        List<TFLLineStatusObject> oldIssues = tflStatusService.getLineIssues();
//        tflStatusService.scheduleAPICall();
//        List<TFLLineStatusObject> newIssues = tflStatusService.getLineIssues();
//
//        Assert.assertEquals(emptyList(), oldIssues);
//        Assert.assertNotEquals(emptyList(), newIssues);
//    }

    @Test
    public void checkTimeTest(){
        String actual = timeUtility.getCurrentTimeAsString();
        LocalTime time = LocalTime.now();
        String expected = time.format(DateTimeFormatter.ofPattern("h:mm a"));

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPercentageTest(){
        double actual = getPercentage(101, 65);
        Assert.assertEquals(64.0, actual, 1);
    }
}