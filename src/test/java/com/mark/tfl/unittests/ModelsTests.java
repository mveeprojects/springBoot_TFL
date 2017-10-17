package com.mark.tfl.unittests;

import com.mark.tfl.Models.LineStatus;
import org.junit.Assert;
import org.junit.Test;

public class ModelsTests {

    @Test
    public void newLineStatusTest(){
        LineStatus lineStatus = new LineStatus("sampleStation", "SampleStatus");
        String linename = lineStatus.getLineName();
        String linestatus = lineStatus.getLineStatus();
        Assert.assertEquals("sampleStation", linename);
        Assert.assertEquals("SampleStatus", linestatus);
    }

}
