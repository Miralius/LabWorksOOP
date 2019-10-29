package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.Point;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.LinkedListTabulatedFunctionFactory;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.TabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedFunctionOperationServiceTest {
    double[] firstXValues = new double[]{1.2, 2.3, 3.4, 5.6};
    double[] firstYValues = new double[]{7, 6, 5, 4};
    double[] secondXValues = new double[]{1.3, 3.4, 4.5, 6.7};
    double[] secondYValues = new double[]{1, 2, 3, 4};

    @Test
    public void testGetFactory() {
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        assertTrue(tabulatedFunctionOperationService.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        tabulatedFunctionOperationService = new TabulatedFunctionOperationService(new ArrayTabulatedFunctionFactory());
        assertTrue(tabulatedFunctionOperationService.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    @Test
    public void testSetFactory() {
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        tabulatedFunctionOperationService.setFactory(new LinkedListTabulatedFunctionFactory());
        assertTrue(tabulatedFunctionOperationService.getFactory() instanceof LinkedListTabulatedFunctionFactory);
        tabulatedFunctionOperationService.setFactory(new ArrayTabulatedFunctionFactory());
        assertTrue(tabulatedFunctionOperationService.getFactory() instanceof ArrayTabulatedFunctionFactory);
    }

    /*@Test
    public void testAsPoints() {
        TabulatedFunction array = (new ArrayTabulatedFunctionFactory()).create(firstXValues, firstYValues);
        Point[] points = TabulatedFunctionOperationService.asPoints((TabulatedFunction) array);
        int i=0;
        for (Point point : points) {
            assertEquals(point.x, array.getX(i));
            assertEquals(point.y, array.getY(i++));
        }
    }
    это не работает, так как для массивов не переписан итератор*/

    @Test
    public void testSum() {
        /*не могла написать тест для array из-за непереопредленного иттератора в array*/
        TabulatedFunctionFactory listTest = new LinkedListTabulatedFunctionFactory();
        TabulatedFunctionOperationService tabulatedFunctionOperationService = new TabulatedFunctionOperationService(new LinkedListTabulatedFunctionFactory());
        TabulatedFunction aNew = listTest.create(firstXValues, firstYValues);
        TabulatedFunction bNew = listTest.create(firstXValues, secondYValues);
        TabulatedFunction cNew = tabulatedFunctionOperationService.sum(aNew, bNew);
        int j = 0;
        for (Point point : cNew) {
            assertEquals(point.x, firstXValues[j]);
            assertEquals(point.y, firstYValues[j] + secondYValues[j++]);
        }
    }

    @Test
    public void testSubtract() {

    }

    @Test
    public void testMultiplication() {

    }

    @Test
    public void testDivision() {

    }
}