package com.mark.tfl;

import com.mark.tfl.Models.TFLMongoRepo;
import com.mark.tfl.Models.TFLLineStatus;
import com.mark.tfl.Models.TFLMongoObject;
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

    private TFLLineStatus lineStatus = new TFLLineStatus("testline", "teststatus");

    private TFLMongoObject mongoTFLObject = new TFLMongoObject("testtime", Arrays.asList(lineStatus));

    @Mock
    private TFLMongoRepo tflRepository;

    @InjectMocks
    private TFLStatusService tflStatusService;

    @Test
    public void newLineStatusTest() {
        String expectedLine = "sampleLine";
        String expectedStatus = "sampleStatus";
        TFLLineStatus lineStatus = new TFLLineStatus(expectedLine, expectedStatus);
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
        TFLLineStatus lineStatus = new TFLLineStatus("Victoria", "testStatus");
        boolean actual = lineStatus.isNightTube();
        assertEquals(true, actual);

        lineStatus = new TFLLineStatus("Metropolitan", "testStatus");
        boolean actual1 = lineStatus.isNightTube();
        assertEquals(false, actual1);
    }
}