package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

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
            LinkedListTabulatedFunction unitLinkedList = new LinkedListTabulatedFunction(valuesX, valuesY);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            MathFunction sqrFunc = new SqrFunction();
            LinkedListTabulatedFunction unitLinkedList = new LinkedListTabulatedFunction(sqrFunc, 1, 1, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            LinkedListTabulatedFunction firstList = listOfArray();
            firstList.floorIndexOfX(-5);
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
        assertEquals(firstList.extrapolateLeft(1.5), 2.5);
        assertEquals(secondList.extrapolateLeft(0.5), 0.5);
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
        assertEquals(firstList.interpolate(2.5, 2), 3.5);
        assertEquals(secondList.interpolate(7.5, 3), 193.5);
    }
}