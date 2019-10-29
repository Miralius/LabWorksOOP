package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AbstractTabulatedFunctionTest {
    double[] xValues={0,0.5,1};
    double[] yValues={0,0.25,1};
    LinkedListTabulatedFunction link=new LinkedListTabulatedFunction(xValues,yValues);
    String sLink="LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]";
    @Test
    public void testTestToString() {
        assertEquals(sLink,link.toString());
    }
}