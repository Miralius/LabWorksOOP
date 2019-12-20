package ru.ssau.tk.frolvas.labworksoop.functions;

import org.jetbrains.annotations.NotNull;
import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static java.lang.Math.abs;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction implements Insertable, Removable, Serializable {
    private static final long serialVersionUID = 978751603564990109L;
    private double[] xValues, yValues;
    private int count;
    private final double DOUBLE_EPSILON = 1E-12;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        if (xValues.length < 2) {
            throw new IllegalArgumentException("Size of array is less than minimum (2)");
        }
        checkLengthIsTheSame(xValues, yValues);
        checkSorted(xValues);
        count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, count);
        this.yValues = Arrays.copyOf(yValues, count);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (count < 2) {
            throw new IllegalArgumentException("Number of values is less than minimum (2)");
        }
        this.count = count;
        double xStart = xFrom, xFinish = xTo;
        if (xTo < xFrom) {
            xStart = xTo;
            xFinish = xFrom;
        }
        xValues = new double[count];
        yValues = new double[count];
        double samplingStep = (xFinish - xStart) / (count - 1);
        double xValue = xStart;
        for (int i = 0; i < count; i++) {
            xValues[i] = xValue;
            yValues[i] = source.apply(xValue);
            xValue += samplingStep;
        }
    }

    public ArrayTabulatedFunction() {

    }

    @Override
    protected int floorIndexOfX(double x) {
        if (x < xValues[0]) {
            throw new IllegalArgumentException("X is less than the left border of tabulated function");
        }
        for (int i = 1; i < count; i++) {
            if (xValues[i] > x) return (i - 1);
        }
        return count;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, xValues[0], xValues[1], yValues[0], yValues[1]);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, xValues[count - 2], xValues[count - 1], yValues[count - 2], yValues[count - 1]);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        if (x < xValues[floorIndex] || xValues[floorIndex + 1] < x) {
            throw new InterpolationException("X is out of bounds of interpolation");
        }
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (abs(xValues[i] - x) < DOUBLE_EPSILON) return i;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (abs(yValues[i] - y) < DOUBLE_EPSILON) return i;
        }
        return -1;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public void insert(double x, double y) {
        int i = indexOfX(x);
        if (i != -1) {
            setY(i, y);
        } else {
            int index = x < xValues[0] ? 0 : floorIndexOfX(x);
            count++;
            if (xValues.length == (count - 1)) {
                double[] xTmp = new double[(int) (count * 1.1)];
                double[] yTmp = new double[(int) (count * 1.1)];
                if (index == 0) {
                    xTmp[0] = x;
                    yTmp[0] = y;
                    System.arraycopy(xValues, 0, xTmp, 1, count - 1);
                    System.arraycopy(yValues, 0, yTmp, 1, count - 1);
                } else if (index == count - 1) {
                    System.arraycopy(xValues, 0, xTmp, 0, count - 1);
                    System.arraycopy(yValues, 0, yTmp, 0, count - 1);
                    xTmp[index] = x;
                    yTmp[index] = y;
                } else {
                    System.arraycopy(xValues, 0, xTmp, 0, index + 1);
                    System.arraycopy(yValues, 0, yTmp, 0, index + 1);
                    xTmp[index + 1] = x;
                    yTmp[index + 1] = y;
                    System.arraycopy(xValues, index + 1, xTmp, index + 2, (count - index - 2));
                    System.arraycopy(yValues, index + 1, yTmp, index + 2, (count - index - 2));
                }
                this.xValues = xTmp;
                this.yValues = yTmp;
            } else {
                if (index == 0) {
                    for (int j = (count - 1); j != 0; j--) {
                        this.xValues[j] = this.xValues[j - 1];
                        this.yValues[j] = this.yValues[j - 1];
                    }
                    this.xValues[0] = x;
                    this.yValues[0] = y;
                } else if (index == count - 1) {
                    this.xValues[index] = x;
                    this.yValues[index] = y;
                } else {
                    for (int j = (count - 1); j != index + 1; j--) {
                        this.xValues[j] = this.xValues[j - 1];
                        this.yValues[j] = this.yValues[j - 1];
                    }
                    this.xValues[index + 1] = x;
                    this.yValues[index + 1] = y;
                }
            }
        }
    }

    @Override
    public void remove(int index) {
        if (count == 2) throw new IllegalArgumentException("Number of values will being less than minimum (2) after removing");
        count--;
        double[] xTempValues = new double[count];
        double[] yTempValues = new double[count];
        if (index == 0) {
            System.arraycopy(xValues, 1, xTempValues, 0, count);
            System.arraycopy(yValues, 1, yTempValues, 0, count);
        } else if (index == count) {
            System.arraycopy(xValues, 0, xTempValues, 0, count);
            System.arraycopy(yValues, 0, yTempValues, 0, count);
        } else {
            System.arraycopy(xValues, 0, xTempValues, 0, index);
            System.arraycopy(yValues, 0, yTempValues, 0, index);
            System.arraycopy(xValues, index + 1, xTempValues, index, count - index);
            System.arraycopy(yValues, index + 1, yTempValues, index, count - index);
        }
        this.xValues = xTempValues;
        this.yValues = yTempValues;
    }

    @NotNull
    @Override
    public Iterator<Point> iterator() {
        return new Iterator<>() {
            private int i = 0;

            public boolean hasNext() {
                return (i < count);
            }

            public Point next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Point point = new Point(xValues[i], yValues[i]);
                i++;
                return point;
            }
        };
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int index) {
        return xValues[index];
    }

    @Override
    public double getY(int index) {
        return yValues[index];
    }

    @Override
    public void setY(int index, double value) {
        yValues[index] = value;
    }
}
