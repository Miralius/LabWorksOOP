package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {

    @Test
    public void test() {
        final double ACCURACY = 0.1;

        MathFunction functionH = new IdentityFunction();
        MathFunction functionG = new Cube();
        MathFunction functionF = new CompositeFunction(functionH, functionG);
        assertEquals(functionF.apply(2), 8, ACCURACY);
        MathFunction cube = new Cube();
        MathFunction sqrt = new SqrtFunction();
        MathFunction funcF = new CompositeFunction(cube, sqrt);
        final double[] xValues = new double[]{1, 2, 3, 4, 5, 6, 7};
        final double[] yValues = new double[]{8, 9, 10, 11, 12, 13, 14};
        MathFunction list = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(funcF.apply(5), Math.sqrt(125), ACCURACY);
        assertEquals(list.andThen(sqrt).andThen(cube).apply(3), 12.196, ACCURACY);
        assertEquals(list.andThen(cube).apply(20), 8007, ACCURACY);
    }


}