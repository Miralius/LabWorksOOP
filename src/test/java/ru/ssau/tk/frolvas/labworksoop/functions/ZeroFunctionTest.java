package ru.ssau.tk.frolvas.labworksoop.functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class ZeroFunctionTest {
    ZeroFunction testFunction = new ZeroFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(479.), 0, 0.0001);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getNumber(), 0, 0.0001);
    }
}