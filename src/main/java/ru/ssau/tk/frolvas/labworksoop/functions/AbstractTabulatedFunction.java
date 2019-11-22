package ru.ssau.tk.frolvas.labworksoop.functions;

import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

public abstract class AbstractTabulatedFunction implements TabulatedFunction {

    abstract protected int floorIndexOfX(double x);

    abstract protected double extrapolateLeft(double x);

    abstract protected double extrapolateRight(double x);

    abstract protected double interpolate(double x, int floorIndex);

    protected double interpolate(double x, double leftX, double rightX, double leftY, double rightY) {
        return leftY + (rightY - leftY) * (x - leftX) / (rightX - leftX);
    }

    @Override
    public double apply(double x) {
        if (x < leftBound()) {
            return extrapolateLeft(x);
        } else if (x > rightBound()) {
            return extrapolateRight(x);
        } else if (indexOfX(x) != -1) {
            return getY(indexOfX(x));
        } else {
            return interpolate(x, floorIndexOfX(x));
        }
    }

    void checkLengthIsTheSame(double[] xValues, double[] yValues) {
        if (xValues.length != yValues.length) {
            throw new DifferentLengthOfArraysException("Lengths of arrays are different");
        }
    }

    void checkSorted(double[] xValues) {
        for (int i = 0; i < xValues.length - 1; i++) {
            if (xValues[i] > xValues[i + 1]) {
                throw new ArrayIsNotSortedException("xValues array isn't sorted");
            }
        }
    }

    public String toString() {
        StringBuilder representString = new StringBuilder();
        representString.append(this.getClass().getSimpleName()).append(" ").append("size = ").append(this.getCount());
        for (Point newPoint : this) {
            representString.append("\n").append("[").append(newPoint.x).append(";").append(" ").append(newPoint.y).append("]");
        }
        return representString.toString();
    }
}
