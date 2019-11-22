package ru.ssau.tk.frolvas.labworksoop.operations;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

public class RightSteppingDifferentialOperator extends SteppingDifferentialOperator {
    RightSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x)) / step;
    }
}
