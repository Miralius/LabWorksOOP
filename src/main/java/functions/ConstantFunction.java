package functions;

public class ConstantFunction implements MathFunction {

    @Override
    public double apply(double x) {
        return number;
    }

    final private double number;

    public ConstantFunction(double number) {
        this.number = number;
    }

    public double getNumber() {
        return number;
    }
}
