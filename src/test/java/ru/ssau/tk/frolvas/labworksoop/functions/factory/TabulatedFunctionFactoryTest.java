package ru.ssau.tk.frolvas.labworksoop.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.ArrayTabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.LinkedListTabulatedFunction;

import static org.testng.Assert.*;

public class TabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
    private final double[] yValues = new double[]{2.0, 4.0, 6.0, 8.0, 10.0};

    @Test
    public void testCreate() {
        TabulatedFunctionFactory arrayTabulatedFunctionFactory = new ArrayTabulatedFunctionFactory();
        TabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction funcOne = arrayTabulatedFunctionFactory.create(xValues, yValues);
        assertTrue(funcOne instanceof ArrayTabulatedFunction);
        TabulatedFunction funcTwo = linkedListTabulatedFunctionFactory.create(xValues, yValues);
        assertTrue(funcTwo instanceof LinkedListTabulatedFunction);
    }
}