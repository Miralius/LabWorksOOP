package functions;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

public class SqrFunctionTest {
    @Test
    public void test(){
        SqrFunction testFunction = new SqrFunction();
        assertEquals(testFunction.apply(5), 25, 0.001);
    }
}