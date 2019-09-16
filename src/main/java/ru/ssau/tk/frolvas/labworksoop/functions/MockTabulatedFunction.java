package ru.ssau.tk.frolvas.labworksoop.functions;

public class MockTabulatedFunction extends AbstractTabulatedFunction {
    private final double x0 = 1, x1 = 3, y0 = 5, y1 = 7;

    @Override
    protected int floorIndexOfX(double x) {
        if (x < x0) {
            return 0;
        } else if (x > x0 && x < x1) {
            return 1;
        } else {
            return 2;
        }
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, x0, x1, y0, y1);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
