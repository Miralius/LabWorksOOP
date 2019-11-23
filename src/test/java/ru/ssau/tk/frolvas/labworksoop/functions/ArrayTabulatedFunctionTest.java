package ru.ssau.tk.frolvas.labworksoop.functions;

import org.testng.annotations.Test;
import ru.ssau.tk.frolvas.labworksoop.exceptions.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.testng.Assert.*;

public class ArrayTabulatedFunctionTest {
    private final double DOUBLE_EPSILON = 1E-12;
    private double[] valuesX = new double[]{-3., -2., -1, -0., 1., 2., 3., 4., 5.};
    private double[] valuesY = new double[]{9., 4., 1., 0., 1., 4., 9., 16., 25.};
    private MathFunction sqrFunc = new SqrFunction();
    private ArrayTabulatedFunction definedThroughArrays = new ArrayTabulatedFunction(valuesX, valuesY);
    private ArrayTabulatedFunction definedThroughMathFunction = new ArrayTabulatedFunction(sqrFunc, 20, 0, 1000);

    @Test
    public void testExceptions() {
        assertThrows(IllegalArgumentException.class, () -> {
            double[] valuesX = new double[]{-3.};
            double[] valuesY = new double[]{9.};
            new ArrayTabulatedFunction(valuesX, valuesY);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            MathFunction sqrFunc = new SqrFunction();
            new ArrayTabulatedFunction(sqrFunc, 1, 1, 1);
        });
        assertThrows(IllegalArgumentException.class, () -> definedThroughArrays.floorIndexOfX(-5));
        assertThrows(InterpolationException.class, () -> {
            double[] valuesX = new double[]{-3., -2., -1, -0., 1., 2., 3., 4., 5.};
            double[] valuesY = new double[]{9., 4., 1., 0., 1., 4., 9., 16., 25.};
            ArrayTabulatedFunction array = new ArrayTabulatedFunction(valuesX, valuesY);
            array.interpolate(4.5, 0);
        });
        assertThrows(NoSuchElementException.class, () -> {
            Iterator<Point> iterator = definedThroughArrays.iterator();
            for (int i = 0; i <= definedThroughArrays.getCount(); i++) {
                iterator.next();
            }
        });
        assertThrows(IllegalArgumentException.class, () -> {
            double[] valuesXTwice = new double[]{-3., -2.};
            double[] valuesYTwice = new double[]{9., 4.};
            ArrayTabulatedFunction testRemoveTwiceArray = new ArrayTabulatedFunction(valuesXTwice, valuesYTwice);
            testRemoveTwiceArray.remove(0);
        });
    }

    @Test
    public void testFloorIndexOfX() {
        assertEquals(definedThroughArrays.floorIndexOfX(30.), 9);
        for (int i = -2; i < 5; i++) {
            assertEquals(definedThroughArrays.floorIndexOfX(i - 0.5), i + 2);
        }
        assertEquals(definedThroughMathFunction.floorIndexOfX(20.1), 1000);
        for (int i = 0; i < 999; i++) {
            assertEquals(definedThroughMathFunction.floorIndexOfX(20. * i / 999), i);
        }
    }

    @Test
    public void testExtrapolateLeft() {
        assertEquals(definedThroughArrays.extrapolateLeft(-5.), 19., DOUBLE_EPSILON);
        assertEquals(definedThroughMathFunction.extrapolateLeft(-1.), -0.0200200200200200200, DOUBLE_EPSILON);
    }

    @Test
    public void testExtrapolateRight() {
        assertEquals(definedThroughArrays.extrapolateRight(6.), 34., DOUBLE_EPSILON);
        assertEquals(definedThroughMathFunction.extrapolateRight(20.), 400., DOUBLE_EPSILON);
    }

    @Test
    public void testInterpolate() {
        assertEquals(definedThroughArrays.interpolate(0.5, definedThroughArrays.floorIndexOfX(0.5)), 0.5, DOUBLE_EPSILON);
        assertEquals(definedThroughMathFunction.interpolate(0.041, definedThroughMathFunction.floorIndexOfX(0.041)), 0.0016992968944920899, DOUBLE_EPSILON);
    }

    @Test
    public void testIndexOfX() {
        assertEquals(definedThroughArrays.indexOfX(1.), 4);
        assertEquals(definedThroughArrays.indexOfX(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfX(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfX(0.1), -1);
    }

    @Test
    public void testIndexOfY() {
        assertEquals(definedThroughArrays.indexOfY(1.), 2);
        assertEquals(definedThroughArrays.indexOfY(1.1), -1);
        assertEquals(definedThroughMathFunction.indexOfY(0.), 0);
        assertEquals(definedThroughMathFunction.indexOfY(0.1), -1);
    }

    @Test
    public void testLeftBound() {
        assertEquals(definedThroughArrays.leftBound(), -3., DOUBLE_EPSILON);
        assertEquals(definedThroughMathFunction.leftBound(), 0., DOUBLE_EPSILON);
    }

    @Test
    public void testRightBound() {
        assertEquals(definedThroughArrays.rightBound(), 5., DOUBLE_EPSILON);
        assertEquals(definedThroughMathFunction.rightBound(), 20., DOUBLE_EPSILON);
    }

    @Test
    public void testGetCount() {
        assertEquals(definedThroughArrays.getCount(), 9);
        assertEquals(definedThroughMathFunction.getCount(), 1000);
    }

    @Test
    public void testGetX() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughArrays.getX(i), i - 3., DOUBLE_EPSILON);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getX(i), i * 20. / 999, DOUBLE_EPSILON);
        }
    }

    @Test
    public void testGetY() {
        for (int i = 0; i < 9; i++) {
            assertEquals(definedThroughArrays.getY(i), Math.pow(i - 3., 2), DOUBLE_EPSILON);
        }
        for (int i = 0; i < 1000; i++) {
            assertEquals(definedThroughMathFunction.getY(i), Math.pow(i * 20. / 999, 2), DOUBLE_EPSILON);
        }
    }

    @Test
    public void testSetY() {
        definedThroughArrays.setY(5, 100500.);
        definedThroughMathFunction.setY(0, 1009.);
        assertEquals(definedThroughArrays.getY(5), 100500., DOUBLE_EPSILON);
        assertEquals(definedThroughMathFunction.getY(0), 1009., DOUBLE_EPSILON);
    }

    //Additional test for composite function
    @Test
    public void testArrayCreatedThroughCompositeFunction() {
        MathFunction cubeFunc = new Cube();
        MathFunction sqrtFunc = new SqrtFunction();
        MathFunction compositeFunc = sqrtFunc.andThen(cubeFunc);
        ArrayTabulatedFunction definedThroughCompositeFunction = new ArrayTabulatedFunction(compositeFunc, 4, 8, 5);
        assertEquals(definedThroughCompositeFunction.floorIndexOfX(30.), 5);
        for (int i = 5; i < 9; i++) {
            assertEquals(definedThroughCompositeFunction.floorIndexOfX(i - 0.5), i - 5);
        }
        assertEquals(definedThroughCompositeFunction.extrapolateLeft(3), 4.819660112502, DOUBLE_EPSILON);
        assertEquals(definedThroughCompositeFunction.extrapolateRight(9), 26.734574818486, DOUBLE_EPSILON);
        assertEquals(definedThroughCompositeFunction.interpolate(5.5, definedThroughCompositeFunction.floorIndexOfX(5.5)), 12.938639172099, DOUBLE_EPSILON);
        assertEquals(definedThroughCompositeFunction.indexOfX(4.), 0);
        assertEquals(definedThroughCompositeFunction.indexOfX(1.), -1);
        assertEquals(definedThroughCompositeFunction.indexOfY(8.), 0);
        assertEquals(definedThroughCompositeFunction.indexOfY(-1.), -1);
        assertEquals(definedThroughCompositeFunction.leftBound(), 4., DOUBLE_EPSILON);
        assertEquals(definedThroughCompositeFunction.rightBound(), 8., DOUBLE_EPSILON);
        assertEquals(definedThroughCompositeFunction.getCount(), 5);
        for (int i = 0; i < 5; i++) {
            assertEquals(definedThroughCompositeFunction.getX(i), i + 4., DOUBLE_EPSILON);
        }
        for (int i = 0; i < 5; i++) {
            assertEquals(definedThroughCompositeFunction.getY(i), Math.pow(i + 4, 3. / 2), DOUBLE_EPSILON);
        }
        definedThroughCompositeFunction.setY(4, 1000.);
        assertEquals(definedThroughCompositeFunction.getY(4), 1000., DOUBLE_EPSILON);
    }

    @Test
    public void testRemove() {
        double[] valuesX = new double[]{-3., -2., -1, -0., 1., 2., 3., 4., 5.};
        double[] valuesY = new double[]{9., 4., 1., 0., 1., 4., 9., 16., 25.};
        ArrayTabulatedFunction testRemoveArray = new ArrayTabulatedFunction(valuesX, valuesY);
        testRemoveArray.remove(0);
        for (int i = -2; i < 6; i++) {
            assertEquals(testRemoveArray.getX(i + 2), i, DOUBLE_EPSILON);
            assertEquals(testRemoveArray.getY(i + 2), i * i, DOUBLE_EPSILON);
        }
        testRemoveArray.remove(7);
        for (int i = -2; i < 5; i++) {
            assertEquals(testRemoveArray.getX(i + 2), i, DOUBLE_EPSILON);
            assertEquals(testRemoveArray.getY(i + 2), i * i, DOUBLE_EPSILON);
        }
        testRemoveArray.remove(3);
        for (int i = -2; i < 1; i++) {
            assertEquals(testRemoveArray.getX(i + 2), i, DOUBLE_EPSILON);
            assertEquals(testRemoveArray.getY(i + 2), i * i, DOUBLE_EPSILON);
        }
        for (int i = 2; i < 5; i++) {
            assertEquals(testRemoveArray.getX(i + 1), i, DOUBLE_EPSILON);
            assertEquals(testRemoveArray.getY(i + 1), i * i, DOUBLE_EPSILON);
        }
    }

    @Test
    void testIteratorThroughWhile() {
        Iterator<Point> iterator = definedThroughArrays.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            Point point = iterator.next();
            assertEquals(definedThroughArrays.getX(i), point.x, DOUBLE_EPSILON);
            assertEquals(definedThroughArrays.getY(i++), point.y, DOUBLE_EPSILON);
        }
        assertThrows(NoSuchElementException.class, iterator::next);
    }

    @Test
    void testIteratorThroughForEach() {
        int i = 0;
        for (Point point : definedThroughArrays) {
            assertEquals(definedThroughArrays.getX(i), point.x, DOUBLE_EPSILON);
            assertEquals(definedThroughArrays.getY(i++), point.y, DOUBLE_EPSILON);
        }
    }

    @Test
    public void testInsert() {
        double[] valuesXFirst = new double[]{1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11., 12., 13., 14., 15., 16., 17., 18., 19., 20.};
        double[] valuesYFirst = new double[]{2., 4., 6., 8., 10., 12., 14., 16., 18., 20., 22., 24., 26., 28., 30., 32., 34., 36., 38., 40.};
        ArrayTabulatedFunction testInsertArrayFirst = new ArrayTabulatedFunction(valuesXFirst, valuesYFirst);
        testInsertArrayFirst.insert(0., 0.);
        testInsertArrayFirst.insert(0., 0.);
        for (int i = 0; i < testInsertArrayFirst.getCount(); i++) {
            assertEquals(testInsertArrayFirst.getX(i), i, DOUBLE_EPSILON);
            assertEquals(testInsertArrayFirst.getY(i), 2 * i, DOUBLE_EPSILON);
        }
        testInsertArrayFirst.insert(-1., -2.);
        for (int i = 0; i < testInsertArrayFirst.getCount(); i++) {
            assertEquals(testInsertArrayFirst.getX(i), i - 1, DOUBLE_EPSILON);
            assertEquals(testInsertArrayFirst.getY(i), 2 * (i - 1), DOUBLE_EPSILON);
        }

        double[] valuesXSecond = new double[]{0., 1., 2., 3., 4., 7., 8., 9., 10., 11., 12., 13., 14., 15., 16., 17., 18., 19., 20.};
        double[] valuesYSecond = new double[]{0., 2., 4., 6., 8., 14., 16., 18., 20., 22., 24., 26., 28., 30., 32., 34., 36., 38., 40.};
        ArrayTabulatedFunction testInsertArraySecond = new ArrayTabulatedFunction(valuesXSecond, valuesYSecond);
        testInsertArraySecond.insert(5., 10.);
        for (int i = 0; i < 6; i++) {
            assertEquals(testInsertArraySecond.getX(i), i, DOUBLE_EPSILON);
            assertEquals(testInsertArraySecond.getY(i), 2 * i, DOUBLE_EPSILON);
        }
        for (int i = 6; i < testInsertArraySecond.getCount(); i++) {
            assertEquals(testInsertArraySecond.getX(i), i + 1, DOUBLE_EPSILON);
            assertEquals(testInsertArraySecond.getY(i), 2 * (i + 1), DOUBLE_EPSILON);
        }
        testInsertArraySecond.insert(6., 12.);
        for (int i = 0; i < testInsertArraySecond.getCount(); i++) {
            assertEquals(testInsertArraySecond.getX(i), i, DOUBLE_EPSILON);
            assertEquals(testInsertArraySecond.getY(i), 2 * i, DOUBLE_EPSILON);
        }

        double[] valuesXThird = new double[]{0., 1., 2., 3., 4., 5., 6., 7., 8., 9., 10., 11., 12., 13., 14., 15., 16., 17., 18., 19., 20.};
        double[] valuesYThird = new double[]{0., 2., 4., 6., 8., 10., 12., 14., 16., 18., 20., 22., 24., 26., 28., 30., 32., 34., 36., 38., 40.};
        ArrayTabulatedFunction testInsertArrayThird = new ArrayTabulatedFunction(valuesXThird, valuesYThird);
        testInsertArrayThird.insert(21., 42.);
        for (int i = 0; i < testInsertArrayThird.getCount(); i++) {
            assertEquals(testInsertArrayThird.getX(i), i, DOUBLE_EPSILON);
            assertEquals(testInsertArrayThird.getY(i), 2 * i, DOUBLE_EPSILON);
        }
        testInsertArrayThird.insert(22., 44.);
        for (int i = 0; i < testInsertArrayThird.getCount(); i++) {
            assertEquals(testInsertArrayThird.getX(i), i, DOUBLE_EPSILON);
            assertEquals(testInsertArrayThird.getY(i), 2 * i, DOUBLE_EPSILON);
        }
    }
}