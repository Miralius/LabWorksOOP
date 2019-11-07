package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    MockTabulatedFunction mockObj = new MockTabulatedFunction();

    @Test
    public void testInterpolate() {
        assertEquals(mockObj.interpolate(2, 1, 3, 5, 7), 6, 0.0001);
    }

    @Test
    public void testApply() {
        assertEquals(mockObj.apply(7), 11, 0.0001);
        assertEquals(mockObj.apply(-7), -3, 0.0001);
        assertEquals(mockObj.apply(2), 1, 0.0001);
        assertEquals(mockObj.apply(1), 5, 0.0001);
    }
}