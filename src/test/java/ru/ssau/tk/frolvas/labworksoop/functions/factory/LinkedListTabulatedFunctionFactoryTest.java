package ru.ssau.tk.frolvas.labworksoop.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.*;

import static org.testng.Assert.*;

public class LinkedListTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
    private final double[] yValues = new double[]{2.0, 4.0, 6.0, 8.0, 10.0};

    @Test
    public void testCreate() {
        TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunction funcOne = arrayTabulatedFunctionFactory.create(xValues, yValues);
        TabulatedFunction strict = arrayTabulatedFunctionFactory.createStrict(xValues, yValues);
        assertTrue(funcOne instanceof ArrayTabulatedFunction);
        assertTrue(strict instanceof StrictTabulatedFunction);
    }
}