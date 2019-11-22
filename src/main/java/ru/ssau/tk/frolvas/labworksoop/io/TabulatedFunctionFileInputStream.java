package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.*;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;
import ru.ssau.tk.frolvas.labworksoop.operations.*;

import java.io.*;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunction arrayFunc = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            System.out.println("Введите размер и значения функции:");
            System.out.println(new TabulatedDifferentialOperator().derive(FunctionsIO.readTabulatedFunction(new BufferedReader(new InputStreamReader(System.in)), new LinkedListTabulatedFunctionFactory())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
