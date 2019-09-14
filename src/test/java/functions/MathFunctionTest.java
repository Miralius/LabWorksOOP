package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class MathFunctionTest {

    MathFunction x = new IdentityFunction();
    MathFunction sqrX = new SqrFunction();
    MathFunction cubeX = new Cube();
    MathFunction one = new UnitFunction();
    MathFunction XToTheSixthPower = cubeX.andThen(sqrX).andThen(x);


    @Test
    public void testAndThen() {
        assertEquals(XToTheSixthPower.apply(10), 1_000_000, 0.001);
        assertEquals(XToTheSixthPower.andThen(one).apply(8), 1, 0.00001);
    }
}