package ru.ssau.tk.frolvas.labworksoop.functions;

public class ConstantFunction implements MathFunction {

    final private double number;

    public ConstantFunction(double number) {
        this.number = number;
    }

    @Override
    public double apply(double x) {
        return number;
    }
    
    public double getNumber() {
        return number;
    }
}
