package ru.ssau.tk.frolvas.labworksoop.functions.factory;


import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.ArrayTabulatedFunction;

public class ArrayTabulatedFunctionFactory implements TabulatedFunctionFactory {
    @Override
    public TabulatedFunction create(double[] xValues, double[] yValues) {
        return new ArrayTabulatedFunction(xValues, yValues);
    }

}
