package ru.ssau.tk.frolvas.labworksoop.operations;


import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.ArrayTabulatedFunctionFactory;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.TabulatedFunctionFactory;
import ru.ssau.tk.frolvas.labworksoop.operations.TabulatedFunctionOperatorService;

public class TabulatedDifferentialOperator implements DifferentialOperator<TabulatedFunction> {
    TabulatedFunctionFactory factory;


    public TabulatedDifferentialOperator(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedDifferentialOperator() {
        this.factory = new ArrayTabulatedFunctionFactory();
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    @Override
    public TabulatedFunction derive(TabulatedFunction function) {
        Point[] points = TabulatedFunctionOperatorService.asPoints(function);
        double[] xValues = new double[points.length];
        double[] yValues = new double[points.length];
        for (int i = 0; i < points.length; i++) {
            xValues[i] = points[i].x;
        }
        for (int i = 0; i < points.length - 1; i++) {
            yValues[i] = (points[i + 1].y - points[i].y) / (xValues[i + 1] - xValues[i]);
        }
        yValues[xValues.length - 1] = yValues[xValues.length - 2];
        return factory.create(xValues, yValues);
    }

    public double apply(double x) {
        return 0;
    }


}
