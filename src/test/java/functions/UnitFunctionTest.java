package functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class UnitFunctionTest {
    UnitFunction testFunction = new UnitFunction();

    @Test
    public void testApply() {
        assertEquals(testFunction.apply(479.), 1, 0.0001);
    }

    @Test
    public void testGetNumber() {
        assertEquals(testFunction.getNumber(), 1, 0.0001);
    }
}