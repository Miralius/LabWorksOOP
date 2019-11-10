package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;


public class LinkedListTabulatedFunctionTest {
    private MathFunction testFunc = new Cube();
    private final double[] xValues = new double[]{1., 3., 5., 7., 9.};
    private final double[] yValues = new double[]{2., 4., 6., 8., 10.};
    private final double ACCURACY = 0.01;

    private LinkedListTabulatedFunction listOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction listOfSecondDesigner() {
        int count = 11;
        return new LinkedListTabulatedFunction(testFunc, 10, 0, count);
    }

    @Test
    public void testExceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[] valuesX = new double[]{-3.};
            double[] valuesY = new double[]{9.};
            new LinkedListTabulatedFunction(valuesX, valuesY);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            MathFunction sqrFunc = new SqrFunction();
            new LinkedListTabulatedFunction(sqrFunc, 1, 1, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction firstList = listOfArray();
            firstList.floorIndexOfX(-5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction firstList = listOfArray();
            firstList.floorNodeOfX(-5);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction firstList = listOfArray();
            firstList.getX(-5);
        });
        assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction firstList = listOfArray();
            firstList.interpolate(5.5, 0);
        });
        assertThrows(NoSuchElementException.class, () -> {
            double[] valuesX = new double[]{-3., 5};
            double[] valuesY = new double[]{9., 5};
            LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(valuesX, valuesY);
            Iterator<Point> iterator = linkedList.iterator();
            for (int i = 0; i <= 2; i++) {
                iterator.next();
            }
        });
        assertThrows(InterpolationException.class, () -> {
            LinkedListTabulatedFunction firstList = listOfArray();
            firstList.interpolate(5.5, firstList.floorNodeOfX(7.5));
        });
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction firstList = listOfArray();
        firstList.addNode(3, 4);
        assertEquals(firstList.rightBound(), 3, ACCURACY);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.getCount(), 5, ACCURACY);
        assertEquals(secondList.getCount(), 11, ACCURACY);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.leftBound(), 1, ACCURACY);
        assertEquals(secondList.leftBound(), 0, ACCURACY);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(secondList.rightBound(), 10, ACCURACY);
        assertEquals(firstList.rightBound(), 9, ACCURACY);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        for (int i = 0; i < 5; i++) {
            assertEquals(firstList.getX(i), 2 * i + 1, ACCURACY);
        }
        for (int i = 0; i < 11; i++) {
            assertEquals(secondList.getX(i), i, ACCURACY);
        }
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.getY(0), 2, ACCURACY);
        assertEquals(secondList.getY(5), 125, ACCURACY);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction firstList = listOfArray();
        firstList.setY(4, 10);
        assertEquals(firstList.getY(4), 10, ACCURACY);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.indexOfX(5), 2);
        assertEquals(secondList.indexOfX(0), 0);
        assertEquals(secondList.indexOfX(11), -1);
    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.indexOfY(12), -1);
        assertEquals(secondList.indexOfY(0), 0);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.floorIndexOfX(3.5), 1);
        assertEquals(secondList.floorIndexOfX(7.5), 7);
        assertEquals(secondList.floorIndexOfX(15), 11);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.extrapolateLeft(1.5), 2.5, ACCURACY);
        assertEquals(secondList.extrapolateLeft(0.5), 0.5, ACCURACY);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.extrapolateRight(8), 9, ACCURACY);
        assertEquals(secondList.extrapolateRight(10.2), 1054.2, ACCURACY);
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction firstList = listOfArray();
        LinkedListTabulatedFunction secondList = listOfSecondDesigner();
        assertEquals(firstList.interpolate(2.5, 0), 3.5, ACCURACY);
        assertEquals(secondList.interpolate(7.5, 7), 427.5, ACCURACY);
    }

    @Test
    void testIteratorThroughWhile() {
        LinkedListTabulatedFunction firstList = listOfArray();
        Iterator<Point> iterator = firstList.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(firstList.getX(i), point.x, ACCURACY);
            assertEquals(firstList.getY(i++), point.y, ACCURACY);
        }
    }

    @Test
    void testIteratorThroughForEach() {
        LinkedListTabulatedFunction firstList = listOfArray();
        int i = 0;
        for (Point point : firstList) {
            assertEquals(firstList.getX(i), point.x, ACCURACY);
            assertEquals(firstList.getY(i++), point.y, ACCURACY);
        }
    }

    @Test
    public void testApply() {
        LinkedListTabulatedFunction firstList = listOfArray();
        assertEquals(firstList.apply(0.), 1., ACCURACY);
        assertEquals(firstList.apply(10.), 11., ACCURACY);
        assertEquals(firstList.apply(1), 2., ACCURACY);
        assertEquals(firstList.apply(1.5), 2.5, ACCURACY);
    }
}