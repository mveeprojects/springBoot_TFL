package com.mark.tfl;

import com.mark.tfl.Controllers.TFLRepository;
import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Models.MongoTFLObject;
import com.mark.tfl.Services.TFLStatusService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class ModelTests {

    private LineStatus lineStatus = new LineStatus("testline", "teststatus");

    private MongoTFLObject mongoTFLObject = new MongoTFLObject("testtime", Arrays.asList(lineStatus));

    @Mock
    private TFLRepository tflRepository;

    @InjectMocks
    private TFLStatusService tflStatusService;

    @Test
    public void newLineStatusTest() {
        String expectedLine = "sampleLine";
        String expectedStatus = "sampleStatus";
        LineStatus lineStatus = new LineStatus(expectedLine, expectedStatus);
        String linename = lineStatus.getLineName();
        String linestatus = lineStatus.getLineStatus();
        assertEquals(expectedLine, linename);
        assertEquals(expectedStatus, linestatus);
    }

    @Test
    public void newTFLResponseTest() {
        Mockito.when(tflRepository.save(mongoTFLObject)).thenReturn(mongoTFLObject);
        tflStatusService.scheduleAPICall();
        assertNotEquals(null, tflStatusService.getLineStatuses());
        assertNotEquals(null, tflStatusService.getLineIssues());
    }

    @Test
    public void nightTubeTest(){
        LineStatus lineStatus = new LineStatus("Victoria", "testStatus");
        boolean actual = lineStatus.isNightTube();
        assertEquals(true, actual);

        lineStatus = new LineStatus("Metropolitan", "testStatus");
        boolean actual1 = lineStatus.isNightTube();
        assertEquals(false, actual1);
    }
}