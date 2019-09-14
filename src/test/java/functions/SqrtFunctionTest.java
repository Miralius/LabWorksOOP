package functions;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SqrtFunctionTest {

    @Test
    public void testApply() {
        SqrtFunction testFunction = new SqrtFunction();
        assertEquals(testFunction.apply(25), 5, 0.001);
    }
}