package com.mark.tfl.unittests;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Services.TFLStatusService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelTests {

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
        TFLStatusService tflStatusService = new TFLStatusService();
        tflStatusService.scheduleAPICall();
        assertNotEquals(null, tflStatusService.getLineStatuses());
        assertNotEquals(null, tflStatusService.getLineIssues());
    }
}
