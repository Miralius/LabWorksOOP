package ru.ssau.tk.frolvas.labworksoop.functions.factory;

import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.LinkedListTabulatedFunction;

public class LinkedListTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new LinkedListTabulatedFunction(xValues, yValues);
    }

}
