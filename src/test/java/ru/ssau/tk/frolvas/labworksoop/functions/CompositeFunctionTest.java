package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CompositeFunctionTest {
    @Test
    public void test() {
        MathFunction functionH = new IdentityFunction();
        MathFunction functionG = new Cube();
        MathFunction functionF = new CompositeFunction(functionH, functionG);
        assertEquals(functionF.apply(2), 8, 0.0001);

    }


}