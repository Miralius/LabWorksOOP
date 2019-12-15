package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class StrictTabulatedFunctionTest {
    private final double DOUBLE_EPSILON = 1E-12;
    TabulatedFunction array = new StrictTabulatedFunction(
            new ArrayTabulatedFunction(new double[]{1., 2., 3.}, new double[]{1., 4., 9.}));
    TabulatedFunction list = new StrictTabulatedFunction(
            new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{1., 4., 9., 16}));
    TabulatedFunction strictInUnmodifiable = new UnmodifiableTabulatedFunction(array);

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
        TabulatedFunction array = new StrictTabulatedFunction(
                new ArrayTabulatedFunction(new double[]{1., 2.}, new double[]{1., 4.}));
        TabulatedFunction list = new StrictTabulatedFunction(
                new LinkedListTabulatedFunction(new double[]{1., 2., 3., 4.}, new double[]{1., 4., 9., 16}));
        array.setY(0, 2.);
        list.setY(3, 1.);
        assertEquals(array.getY(0), 2., DOUBLE_EPSILON);
        assertEquals(list.getY(3), 1., DOUBLE_EPSILON);
        assertThrows(UnsupportedOperationException.class, () -> strictInUnmodifiable.setY(1, 2.5));
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
        assertEquals(array.apply(3.), 9., DOUBLE_EPSILON);
        assertEquals(list.apply(4.), 16., DOUBLE_EPSILON);
        assertThrows(UnsupportedOperationException.class, () -> array.apply(3.5));
        assertThrows(UnsupportedOperationException.class, () -> list.apply(-1.));
        assertThrows(UnsupportedOperationException.class, () -> strictInUnmodifiable.apply(-1.));
    }
}