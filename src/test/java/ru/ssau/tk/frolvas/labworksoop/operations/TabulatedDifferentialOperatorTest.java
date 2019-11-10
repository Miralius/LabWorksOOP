package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    @Test
    public void testGetFactory() {
        TabulatedDifferentialOperator defaultDiffOperator = new TabulatedDifferentialOperator();
        assertTrue(defaultDiffOperator.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedDifferentialOperator diffOperator = new TabulatedDifferentialOperator();
        diffOperator.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(diffOperator.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testDerive() {
        final double DOUBLE_EPSILON = 1E-12;
        final int count = 1000;
        double[] XValues = new double[count];
        double[] YValues = new double[count];
        for (int i = 0; i < count; i++) {
            XValues[i] = i;
            YValues[i] = 5 * i;
        }
        TabulatedFunction diffArray = new TabulatedDifferentialOperator().derive(
                (new ArrayTabulatedFunctionFactory().create(XValues, YValues)));
        for (Point point : diffArray) {
            assertEquals(point.y, 5., DOUBLE_EPSILON);
        }
        TabulatedFunction diffLinkedList = new TabulatedDifferentialOperator().derive(
                (new LinkedListTabulatedFunctionFactory().create(XValues, YValues)));
        for (Point point : diffLinkedList) {
            assertEquals(point.y, 5., DOUBLE_EPSILON);
        }
    }
}