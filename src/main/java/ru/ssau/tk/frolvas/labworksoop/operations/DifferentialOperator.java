package ru.ssau.tk.frolvas.labworksoop.operations;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

public interface DifferentialOperator<T extends MathFunction> {
    T derive(T function);
}
