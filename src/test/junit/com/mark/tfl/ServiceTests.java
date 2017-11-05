package com.mark.tfl;

import com.mark.tfl.Models.TFLMongoRepo;
import com.mark.tfl.Models.TFLLineStatus;
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

import static com.mark.tfl.Utils.MathUtils.getPercentage;
import static com.mark.tfl.Utils.TimeUtility.getCurrentTimeAsString;
import static java.util.Collections.emptyList;

@RunWith(MockitoJUnitRunner.class)
public class ServiceTests {

    @Mock
    private TFLMongoRepo tflRepository;

    @InjectMocks
    private TFLStatusService tflStatusService;

    @Test
    public void refreshLineStatusesTest(){
        List<TFLLineStatus> oldStatuses = tflStatusService.getLineStatuses();
        tflStatusService.scheduleAPICall();
        List<TFLLineStatus> newStatuses = tflStatusService.getLineStatuses();

        Assert.assertNotEquals(oldStatuses, newStatuses);
    }

    @Test
    public void refreshLineIssuesTest(){
        List<TFLLineStatus> oldIssues = tflStatusService.getLineIssues();
        tflStatusService.scheduleAPICall();
        List<TFLLineStatus> newIssues = tflStatusService.getLineIssues();

        Assert.assertEquals(emptyList(), oldIssues);
        Assert.assertNotEquals(emptyList(), newIssues);
    }

    @Test
    public void checkTimeTest(){
        String actual = getCurrentTimeAsString();
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