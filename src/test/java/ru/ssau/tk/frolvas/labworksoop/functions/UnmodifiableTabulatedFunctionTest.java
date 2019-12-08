package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class UnmodifiableTabulatedFunctionTest {
    private final double DOUBLE_EPSILON = 1E-12;
    TabulatedFunction array = new UnmodifiableTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{1., 2., 3.}, new double[]{1., 4., 9.}));
    TabulatedFunction list = new UnmodifiableTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{1., 4., 9., 16}));

    @Test
    public void testGetCount() {
        assertEquals(array.getCount(), 3);
        assertEquals(list.getCount(), 4);
    }

    @Test
    public void testGetX() {
        assertEquals(array.getX(1), 2., DOUBLE_EPSILON);
        assertEquals(list.getX(3), 4., DOUBLE_EPSILON);
    }

    @Test
    public void testGetY() {
        assertEquals(array.getY(1), 4., DOUBLE_EPSILON);
        assertEquals(list.getY(3), 16., DOUBLE_EPSILON);
    }

    @Test
    public void testSetY() {
        assertThrows(UnsupportedOperationException.class, () -> array.setY(0, 2.));
        assertThrows(UnsupportedOperationException.class, () -> list.setY(3, 1.));
    }

    @Test
    public void testIndexOfX() {
        assertEquals(array.indexOfX(3.), 2);
        assertEquals(list.indexOfX(1.), 0);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(array.indexOfY(9.), 2);
        assertEquals(list.indexOfY(16.), 3);
    }

    @Test
    public void testLeftBound() {
        assertEquals(array.leftBound(), 1., DOUBLE_EPSILON);
        assertEquals(list.leftBound(), 1., DOUBLE_EPSILON);
    }

    @Test
    public void testRightBound() {
        assertEquals(array.rightBound(), 3., DOUBLE_EPSILON);
        assertEquals(list.rightBound(), 4., DOUBLE_EPSILON);
    }

    @Test
    public void testIterator() {
        assertEquals(array.iterator().next().x, 1., DOUBLE_EPSILON);
        assertEquals(list.iterator().next().x, 1., DOUBLE_EPSILON);
    }

    @Test
    public void testApply() {
        assertEquals(array.apply(3.5), 11.5, DOUBLE_EPSILON);
        assertEquals(list.apply(4.1), 16.699999999999999, DOUBLE_EPSILON);
    }
}