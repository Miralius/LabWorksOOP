package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.functions.*;

import static org.testng.Assert.*;

public class RightSteppingDifferentialOperatorTest {

    @Test
    public void testDerive() {
        double step = 0.001;
        SteppingDifferentialOperator differentialOperator = new RightSteppingDifferentialOperator(step);
        assertEquals(differentialOperator.derive(new SqrFunction()).apply(1), 2, 0.01);
    }
}