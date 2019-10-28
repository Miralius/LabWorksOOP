package ru.ssau.tk.frolvas.labworksoop.operations;

import ru.ssau.tk.frolvas.labworksoop.exceptions.InconsistentFunctionsException;
import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.TabulatedFunctionFactory;

public class TabulatedFunctionOperationService {
    private TabulatedFunctionFactory factory;

    public TabulatedFunctionOperationService(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public TabulatedFunctionOperationService() {
        factory = (xValues, yValues) -> null;
    }

    public TabulatedFunctionFactory getFactory() {
        return factory;
    }

    public void setFactory(TabulatedFunctionFactory factory) {
        this.factory = factory;
    }

    public static Point[] asPoints(TabulatedFunction tabulatedFunction) {
        int i = 0;
        Point[] points = new Point[tabulatedFunction.getCount()];
        for (Point newPoint : tabulatedFunction) {
            points[i++] = newPoint;
        }
        return points;
    }

    private TabulatedFunction doOperation(TabulatedFunction a, TabulatedFunction b, BiOperation operation) {
        if (a.getCount() != b.getCount()) {
            throw new InconsistentFunctionsException("The number of points in the tabulated functions don't match");
        }
        Point[] aPoints = asPoints(a);
        Point[] bPoints = asPoints(b);
        double[] xValues = new double[a.getCount()];
        double[] yValues = new double[a.getCount()];
        for (int i = 0; i < a.getCount(); i++) {
            if (aPoints[i].x != bPoints[i].x) {
                throw new InconsistentFunctionsException("The corresponding x values don't match");
            }
            xValues[i] = aPoints[i].x;
            yValues[i] = operation.apply(aPoints[i].y, bPoints[i].y);
        }
        return factory.create(xValues, yValues);
    }

    public TabulatedFunction sum(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u + v);
    }

    public TabulatedFunction subtract(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u - v);
    }

    public TabulatedFunction multiplication(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u * v);
    }

    public TabulatedFunction division(TabulatedFunction a, TabulatedFunction b) {
        return doOperation(a, b, (u, v) -> u / v);
    }

    private interface BiOperation {
        double apply(double u, double v);
    }
}
