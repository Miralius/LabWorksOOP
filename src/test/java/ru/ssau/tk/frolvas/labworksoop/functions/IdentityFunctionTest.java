package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class IdentityFunctionTest {
    @Test
    public void test() {
        IdentityFunction firstFunction = new IdentityFunction();
        assertEquals(firstFunction.apply(5), 5, 0.00001);
    }


}