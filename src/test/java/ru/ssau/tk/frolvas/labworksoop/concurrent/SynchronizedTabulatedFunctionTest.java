package ru.ssau.tk.frolvas.labworksoop.concurrent;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class SynchronizedTabulatedFunctionTest {
    private final double[] xValues = new double[]{1.0, 2.0, 3.0, 4.0, 5.0};
    private final double[] yValues = new double[]{6.0, 7.0, 8.0, 9.0, 10.0};
    private final double ACCURACY = 0.00001;
    private MathFunction func = new SqrFunction();
    LinkedListTabulatedFunction firstList = new LinkedListTabulatedFunction(xValues, yValues);
    LinkedListTabulatedFunction secondList = new LinkedListTabulatedFunction(func, 1, 10, 10);
    ArrayTabulatedFunction firstArray = new ArrayTabulatedFunction(xValues, yValues);
    ArrayTabulatedFunction secondArray = new ArrayTabulatedFunction(func, 1, 10, 10);
    SynchronizedTabulatedFunction syncFirstList = new SynchronizedTabulatedFunction(firstList);
    SynchronizedTabulatedFunction syncSecondList = new SynchronizedTabulatedFunction(secondList);
    SynchronizedTabulatedFunction syncFirstArray = new SynchronizedTabulatedFunction(firstArray);
    SynchronizedTabulatedFunction syncSecondArray = new SynchronizedTabulatedFunction(secondArray);

    @Test
    public void testGetCount() {
        assertEquals(syncFirstList.getCount(), 5, ACCURACY);
        assertEquals(syncSecondList.getCount(), 10, ACCURACY);
        assertEquals(syncFirstArray.getCount(), 5, ACCURACY);
        assertEquals(syncSecondArray.getCount(), 10, ACCURACY);
    }

    @Test
    public void testGetX() {
        assertEquals(syncFirstList.getX(1), 2.0, ACCURACY);
        assertEquals(syncSecondList.getX(2), 3.0, ACCURACY);
        assertEquals(syncFirstArray.getX(3), 4.0, ACCURACY);
        assertEquals(syncSecondArray.getX(4), 5.0, ACCURACY);
    }

    @Test
    public void testGetY() {
        assertEquals(syncFirstList.getY(1), 7.0, ACCURACY);
        assertEquals(syncSecondList.getY(2), 9.0, ACCURACY);
        assertEquals(syncFirstArray.getY(3), 9.0, ACCURACY);
        assertEquals(syncSecondArray.getY(4), 25.0, ACCURACY);
    }

    @Test
    public void testSetY() {
        syncFirstList.setY(0, 2);
        assertEquals(syncFirstList.getY(0), 2, ACCURACY);
        syncSecondList.setY(1, 3);
        assertEquals(syncSecondList.getY(1), 3, ACCURACY);
        syncFirstArray.setY(0, 4);
        assertEquals(syncFirstArray.getY(0), 4, ACCURACY);
        syncSecondArray.setY(2, 5);
        assertEquals(syncSecondArray.getY(2), 5, ACCURACY);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(syncFirstList.indexOfX(3.0), 2);
        assertEquals(syncSecondList.indexOfX(4.0), 3);
        assertEquals(syncFirstArray.indexOfX(5.0), 4);
        assertEquals(syncSecondArray.indexOfX(8.0), 7);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(syncFirstList.indexOfY(9.0), 3);
        assertEquals(syncSecondList.indexOfY(36.0), 5);
        assertEquals(syncFirstArray.indexOfY(10.0), 4);
        assertEquals(syncSecondArray.indexOfY(81.0), 8);
    }

    @Test
    public void testLeftBound() {
        assertEquals(syncFirstList.leftBound(), 1.0);
        assertEquals(syncSecondList.leftBound(), 1.0);
        assertEquals(syncFirstArray.leftBound(), 1.0);
        assertEquals(syncSecondArray.leftBound(), 1.0);
    }

    @Test
    public void testRightBound() {
        assertEquals(syncFirstList.rightBound(), 5.0);
        assertEquals(syncSecondList.rightBound(), 10.0);
        assertEquals(syncFirstArray.rightBound(), 5.0);
        assertEquals(syncSecondArray.rightBound(), 10.0);
    }

    @Test
    public void testApply() {
        assertEquals(syncFirstList.apply(3.0), 8.0);
        assertEquals(syncSecondList.apply(4.0), 16.0);
        assertEquals(syncFirstArray.apply(5.0), 10.0);
        assertEquals(syncSecondList.apply(10.0), 100.0);
    }

    @Test
    public void testIterator() {
        Iterator<Point> iteratorArray = firstArray.iterator();
        int i = 0;
        while (iteratorArray.hasNext()) {
            Point point = iteratorArray.next();
            assertEquals(syncFirstArray.getX(i), point.x, 0.0001);
            assertEquals(syncFirstArray.getY(i++), point.y, 0.0001);
        }
        assertThrows(NoSuchElementException.class, iteratorArray::next);
        i = 0;
        for (Point point : syncFirstArray) {
            assertEquals(syncFirstArray.getX(i), point.x, 0.0001);
            assertEquals(syncFirstArray.getY(i++), point.y, 0.0001);
        }
        assertThrows(NoSuchElementException.class, iteratorArray::next);
    }

    @Test
    public void testDoSynchronously() {
        assertEquals((int) syncFirstArray.doSynchronously(SynchronizedTabulatedFunction::getCount), 5);
        assertEquals(syncFirstArray.doSynchronously(syncFirstArray -> syncFirstArray.tabulatedFunction.apply(4.)), 9.);
        assertEquals(syncFirstArray.doSynchronously(SynchronizedTabulatedFunction::rightBound), 5.);
        assertNull(syncFirstArray.doSynchronously(x -> {
            x.setY(1, 2);
            return null;
        }));
    }
}