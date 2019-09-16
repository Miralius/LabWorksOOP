package ru.ssau.tk.frolvas.labworksoop.functions;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class CubeTest {
    @Test
    public void test(){
        Cube firstFunction = new Cube();
        assertEquals(firstFunction.apply(2), 8, 0.00001);
    }

}