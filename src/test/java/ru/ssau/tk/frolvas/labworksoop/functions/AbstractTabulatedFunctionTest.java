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
    }
}