package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    double[] firstXValues = new double[]{1.2, 2.3, 3.4, 5.6};
    double[] firstYValues = new double[]{7, 6, 5, 4};

    @Test
    public void testAsPoints() {
        TabulatedFunction array = new ArrayTabulatedFunctionFactory().create(firstXValues, firstYValues);
        Point[] points = TabulatedFunctionOperationService.asPoints(array);
        int i = 0;
        for (Point point : points) {
            assertEquals(point.x, array.getX(i));
            assertEquals(point.y, array.getY(i++));
        }
    }
}