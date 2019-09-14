package functions;

public class CompositeFunction implements MathFunction {
    private MathFunction functionH;
    private MathFunction functionG;

    public CompositeFunction(MathFunction functionH, MathFunction functionG) {
        this.functionG = functionH;
        this.functionH = functionG;
    }

    public double apply(double x) {
        return functionH.apply(functionG.apply(x));
    }
}
