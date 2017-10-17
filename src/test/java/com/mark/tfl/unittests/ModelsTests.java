package com.mark.tfl.unittests;

import com.mark.tfl.Models.LineStatus;
import com.mark.tfl.Services.TFLStatusService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class ModelsTests {

    @Test
    public void newLineStatusTest() {
        LineStatus lineStatus = new LineStatus("sampleStation", "SampleStatus");
        String linename = lineStatus.getLineName();
        String linestatus = lineStatus.getLineStatus();
        assertEquals("sampleStation", linename);
        assertEquals("SampleStatus", linestatus);
    }

    @Test
    public void newTFLResponseTest() {
        TFLStatusService tflStatusService = new TFLStatusService();
        tflStatusService.scheduleAPICall();
        assertNotEquals(null, tflStatusService.getLineStatuses());
        assertNotEquals(null, tflStatusService.getLineIssues());
    }

}
