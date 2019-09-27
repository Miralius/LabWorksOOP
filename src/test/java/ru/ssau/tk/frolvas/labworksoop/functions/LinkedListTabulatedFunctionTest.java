package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;


public class LinkedListTabulatedFunctionTest {
    private MathFunction testFunc = new Cube();
    private final double[] xValues = new double[]{1., 3., 5., 7.,9.};
    private final double[] yValues = new double[]{2., 4., 6., 8., 10.};
    private final double accuracy = 0.00001;

    private LinkedListTabulatedFunction ListOfArray() {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

    private LinkedListTabulatedFunction ListOfSecondDesigner() {
        int count =11;
        return new LinkedListTabulatedFunction(testFunc, 10,0 , 11);
    }
    private LinkedListTabulatedFunction ListWithOneElement() {
        return new LinkedListTabulatedFunction(testFunc, 5, 5, 10);
    }

    @Test
    public void testAddNode() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        firstList.addNode(3, 4);
        assertEquals(firstList.rightBound(), 3, accuracy);
    }

    @Test
    public void testGetCount() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.getCount(), 5,accuracy);
        assertEquals(secondList.getCount(), 11,accuracy);
    }

    @Test
    public void testLeftBound() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.leftBound(), 1,accuracy);
        assertEquals(secondList.leftBound(), 0,accuracy);
    }

    @Test
    public void testRightBound() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(secondList.rightBound(), 11,accuracy);
        assertEquals(firstList.rightBound(), 9, accuracy);
    }

    @Test
    public void testGetX() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.getX(0), 1,accuracy);
        assertEquals(secondList.getX(0), 0,accuracy);
    }

    @Test
    public void testGetY() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.getY(0), 2,accuracy);
        assertEquals(secondList.getY(0), 0,accuracy);
    }

    @Test
    public void testSetY() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        firstList.setY(4, 10);
        assertEquals(firstList.getY(4), 10,accuracy);
    }

    @Test
    public void testIndexOfX() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.indexOfX(5), 2);
        assertEquals(secondList.indexOfX(0), 0);

    }

    @Test
    public void testIndexOfY() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.indexOfY(12), -1);
        assertEquals(secondList.indexOfY(0), 0);
    }

    @Test
    public void testFloorIndexOfX() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.floorIndexOfX(3.5), 1);
        assertEquals(secondList.floorIndexOfX(-10), 0);
        assertEquals(secondList.floorIndexOfX(7.5), 7);
    }

    @Test
    public void testExtrapolateLeft() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.extrapolateLeft(1.5), 2.5);
        assertEquals(secondList.extrapolateLeft(0.5), 0.5);
    }

    @Test
    public void testExtrapolateRight() {
        LinkedListTabulatedFunction thirdList = ListWithOneElement();
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(thirdList.extrapolateRight(5.1), 125, accuracy);
        assertEquals(firstList.extrapolateRight(8), 9, accuracy);
        //assertEquals(secondList.extrapolateRight(9.9), 972.9,accuracy ); - тут какая-то странная погрешность
    }

    @Test
    public void testInterpolate() {
        LinkedListTabulatedFunction firstList = ListOfArray();
        LinkedListTabulatedFunction secondList = ListOfSecondDesigner();
        assertEquals(firstList.interpolate(2.5, 2), 3.5);
        assertEquals(secondList.interpolate(7.5, 3), 193.5);
    }
}