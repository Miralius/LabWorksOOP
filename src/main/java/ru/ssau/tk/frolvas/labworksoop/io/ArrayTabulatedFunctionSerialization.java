package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.operations.*;

import java.io.*;

import static ru.ssau.tk.frolvas.labworksoop.io.FunctionsIO.*;

public class ArrayTabulatedFunctionSerialization {
    public static void main(String[] args) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("output/serialized array functions.bin"))) {
            ArrayTabulatedFunction function = new ArrayTabulatedFunction(new SqrtFunction(), 0, 20, 100);
            TabulatedFunction firstDerivative = new TabulatedDifferentialOperator().derive(function);
            TabulatedFunction secondDerivative = new TabulatedDifferentialOperator().derive(firstDerivative);
            serialize(outputStream, function);
            serialize(outputStream, firstDerivative);
            serialize(outputStream, secondDerivative);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("output/serialized array functions.bin"))) {
            System.out.println(deserialize(inputStream).toString() + '\n' + deserialize(inputStream).toString() + '\n' + deserialize(inputStream));
        } catch (IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
    }
}
