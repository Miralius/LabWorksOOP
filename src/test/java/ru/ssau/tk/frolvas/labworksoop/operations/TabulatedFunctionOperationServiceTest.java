package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.exceptions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    double[] firstXValues = new double[]{1., 2., 3., 4.};
    double[] firstYValues = new double[]{2., 4., 6., 8.};
    double[] secondXValues = new double[]{1., 2., 3., 5.};
    double[] secondYValues = new double[]{1., 2., 3., 4.};

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

    @Test
    public void testAddUp() {
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(firstXValues, firstYValues);
        TabulatedFunction b = linkedListFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.addUp(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.addUp(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] + secondYValues[i++]);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] + secondYValues[i++]);
        }
        assertThrows(InconsistentFunctionsException.class, () ->
                operationServiceThroughLinkedList.addUp(a, linkedListFactory.create(secondXValues, secondYValues)));
        assertThrows(InconsistentFunctionsException.class, () ->
                {
                    TabulatedFunction c = linkedListFactory.create(new double[]{1., 2., 3.}, new double[]{1., 2., 3.});
                    operationServiceThroughLinkedList.addUp(a, c);
                });
    }

    @Test
    public void testSubtract() {
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(firstXValues, firstYValues);
        TabulatedFunction b = linkedListFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.subtract(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.subtract(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, secondYValues[i++]);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, secondYValues[i++]);
        }
    }

    @Test
    public void testMultiply() {
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(firstXValues, firstYValues);
        TabulatedFunction b = linkedListFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.multiply(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.multiply(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] * secondYValues[i++]);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstXValues[i]);
            assertEquals(point.y, firstYValues[i] * secondYValues[i++]);
        }
    }

    @Test
    public void testDivide() {
        TabulatedFunctionFactory linkedListFactory = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService operationServiceThroughArray = new TabulatedFunctionOperationService();
        TabulatedFunctionOperationService operationServiceThroughLinkedList = new TabulatedFunctionOperationService(linkedListFactory);
        TabulatedFunction a = new ArrayTabulatedFunctionFactory().create(firstXValues, firstYValues);
        TabulatedFunction b = linkedListFactory.create(firstXValues, secondYValues);
        TabulatedFunction resultSumThroughArray = operationServiceThroughArray.divide(a, b);
        TabulatedFunction resultSumThroughLinkedList = operationServiceThroughLinkedList.divide(a, b);
        int i = 0;
        for (Point point : resultSumThroughArray) {
            assertEquals(point.x, firstXValues[i++]);
            assertEquals(point.y, 2.);
        }
        i = 0;
        for (Point point : resultSumThroughLinkedList) {
            assertEquals(point.x, firstXValues[i++]);
            assertEquals(point.y, 2.);
        }
    }

    @Test
    public void testGetFactory() {
        assertTrue((new TabulatedFunctionOperationService()).getFactory() instanceof ArrayTabulatedFunctionFactory);
        assertTrue((new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory())).getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService obj = new TabulatedFunctionOperationService();
        obj.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(obj.getFactory() instanceof LinkedListTabulatedFunctionFactory);
    }
}