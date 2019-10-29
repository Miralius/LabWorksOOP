package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.ArrayTabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.LinkedListTabulatedFunctionFactory;

import static org.testng.Assert.*;

public class TabulatedDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double[] xValues = {1, 2.3, 13.4};
        double[] yValues = {8.1, 1.1, 8.8};
        TabulatedDifferentialOperator linkedList = new TabulatedDifferentialOperator(new LinkedListTabulatedFunctionFactory());
        LinkedListTabulatedFunction myFunc = new LinkedListTabulatedFunction(xValues, yValues);

        var diffArray = linkedList.derive(myFunc);
        assertEquals(diffArray.getY(0), (1.1 - 8.1) / (2.3 - 1), 0.0001);

    }
}