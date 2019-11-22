package ru.ssau.tk.frolvas.labworksoop.operations;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

import static org.testng.Assert.*;

public class SteppingDifferentialOperatorTest {

    SteppingDifferentialOperator objSDO = new LeftSteppingDifferentialOperator(5.);

    @Test
    public void testGetStep() {
        assertEquals(objSDO.getStep(), 5.);
        assertThrows(IllegalArgumentException.class, () -> new LeftSteppingDifferentialOperator(-5.));
    }

    @Test
    public void testSetStep() {
        objSDO.setStep(10.);
        assertEquals(objSDO.getStep(), 10.);
    }
}