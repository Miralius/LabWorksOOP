package ru.ssau.tk.frolvas.labworksoop.functions.factory;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

public interface TabulatedFunctionFactory {
    TabulatedFunction create(double[] xValues, double[] yValues);
}
