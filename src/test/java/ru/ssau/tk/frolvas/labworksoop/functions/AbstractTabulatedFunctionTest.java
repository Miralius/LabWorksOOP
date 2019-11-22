package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

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

    @Test
    public void checkLengthIsTheSame() {
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            double[] valuesX = new double[]{-3., 5};
            double[] valuesY = new double[]{9.};
            mockObj.checkLengthIsTheSame(valuesX, valuesY);
        });
    }

    @Test
    public void checkSorted() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            double[] valuesX = new double[]{-3., 5, 7, 9, 0};
            mockObj.checkSorted(valuesX);
        });
    }

    @Test
    public void testAllExceptions() {
        assertThrows(ArrayIsNotSortedException.class, () -> {
            throw new ArrayIsNotSortedException();
        });
        assertThrows(DifferentLengthOfArraysException.class, () -> {
            throw new DifferentLengthOfArraysException();
        });
        assertThrows(InterpolationException.class, () -> {
            throw new InterpolationException();
        });
        assertThrows(InconsistentFunctionsException.class, () -> {
            throw new InconsistentFunctionsException();
        });
    }

    @Test
    public void testToString() {
        double[] xValues = {0.0, 0.5, 1.0};
        double[] yValues = {0.0, 0.25, 1.0};
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkFunck = new LinkedListTabulatedFunction(xValues, yValues);
        String stringOfLink = "LinkedListTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]";
        String stringOfArr = "ArrayTabulatedFunction size = 3\n[0.0; 0.0]\n[0.5; 0.25]\n[1.0; 1.0]";
        assertEquals(stringOfArr, arrayFunc.toString());
        assertEquals(stringOfLink, linkFunck.toString());
    }
}