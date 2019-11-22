package ru.ssau.tk.frolvas.labworksoop.operations;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

public class MiddleSteppingDifferentialOperator extends SteppingDifferentialOperator {
    MiddleSteppingDifferentialOperator(double step) {
        super(step);
    }

    @Override
    public MathFunction derive(MathFunction function) {
        return x -> (function.apply(x + step) - function.apply(x - step)) / (2 * step);
    }
}
