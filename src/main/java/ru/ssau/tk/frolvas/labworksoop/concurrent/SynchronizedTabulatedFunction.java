package ru.ssau.tk.frolvas.labworksoop.concurrent;

import ru.ssau.tk.frolvas.labworksoop.functions.Point;
import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.operations.TabulatedFunctionOperationService;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class SynchronizedTabulatedFunction implements TabulatedFunction {
    TabulatedFunction tabulatedFunction;

    SynchronizedTabulatedFunction(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public int getCount() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getCount();
        }
    }

    @Override
    public double getX(int index) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getX(index);
        }
    }

    @Override
    public double getY(int index) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.getY(index);
        }
    }

    @Override
    public void setY(int index, double value) {
        synchronized (tabulatedFunction) {
            tabulatedFunction.setY(index, value);
        }
    }

    @Override
    public int indexOfX(double x) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.indexOfX(x);
        }
    }

    @Override
    public int indexOfY(double y) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.indexOfY(y);
        }
    }

    @Override
    public double leftBound() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.leftBound();
        }
    }

    @Override
    public double rightBound() {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.rightBound();
        }
    }

    @Override
    public Iterator<Point> iterator() {
        synchronized (tabulatedFunction) {
            Point[] copy = TabulatedFunctionOperationService.asPoints(tabulatedFunction);
            return new Iterator<>() {
                int i = 0;

                @Override
                public boolean hasNext() {
                    return i != copy.length;
                }

                @Override
                public Point next() {
                    if (i==copy.length) {
                        throw new NoSuchElementException();
                    } else {
                        return new Point(copy[i].x, copy[i++].y);
                    }
                }
            };
        }
    }

    @Override
    public double apply(double x) {
        synchronized (tabulatedFunction) {
            return tabulatedFunction.apply(x);
        }
    }

}
