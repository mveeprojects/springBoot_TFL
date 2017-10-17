package com.mark.tfl.unittests;

import com.mark.tfl.Controllers.RestControllers;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ControllerTests {

    @Test
    public void testing() {
        int actual = RestControllers.add(1,2);
        int expected = 3;
        assertEquals(expected, actual);
    }
}
