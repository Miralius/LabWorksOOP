package ru.ssau.tk.frolvas.labworksoop.functions;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class UnmodifiableTabulatedFunction implements TabulatedFunction {
    private TabulatedFunction function;

    public UnmodifiableTabulatedFunction(TabulatedFunction function) {
        this.function = function;
    }

    @Override
    public int getCount() {
        return function.getCount();
    }

    @Override
    public double getX(int index) {
        return function.getX(index);
    }

    @Override
    public double getY(int index) {
        return function.getY(index);
    }

    @Override
    public void setY(int index, double value) {
        throw new UnsupportedOperationException("Changing values isn't supported");
    }

    @Override
    public int indexOfX(double x) {
        return function.indexOfX(x);
    }

    @Override
    public int indexOfY(double y) {
        return function.indexOfY(y);
    }

    @Override
    public double leftBound() {
        return function.leftBound();
    }

    @Override
    public double rightBound() {
        return function.rightBound();
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        return function.iterator();
    }

    @Override
    public double apply(double x) {
        return function.apply(x);
    }
}
