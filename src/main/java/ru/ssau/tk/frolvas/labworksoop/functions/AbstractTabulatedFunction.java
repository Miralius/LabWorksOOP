package ru.ssau.tk.frolvas.labworksoop.functions;

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
    @Override
    public String toString() {
        StringBuilder neededString = new StringBuilder();
        neededString.append(this.getClass().getSimpleName()).append(" ").append("size = ").append(this.getCount());
        for (Point newPoint : this) {
            neededString.append("\n").append("[").append(newPoint.x).append(";").append(" ").append(newPoint.y).append("]");
        }
        return neededString.toString();
    }

}
