package functions;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ConstantFunctionTest {
    ConstantFunction testFunction = new ConstantFunction(473.1783);

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(479.), 473.1783, 0.0001);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getNumber(), 473.17833, 0.0001);
    }
}