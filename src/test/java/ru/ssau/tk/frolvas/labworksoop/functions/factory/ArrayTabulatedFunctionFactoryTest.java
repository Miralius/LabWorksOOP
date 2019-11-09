package ru.ssau.tk.frolvas.labworksoop.functions.factory;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.*;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionFactoryTest {
    private final double[] xValues = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
    private final double[] yValues = new double[]{2.0, 4.0, 6.0, 8.0, 10.0};

    @Test
    public void testCreate() {
        TabulatedFunctionFactory linkedListTabulatedFunctionFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunction funcTwo = linkedListTabulatedFunctionFactory.create(xValues, yValues);
        assertTrue(funcTwo instanceof LinkedListTabulatedFunction);
    }
}