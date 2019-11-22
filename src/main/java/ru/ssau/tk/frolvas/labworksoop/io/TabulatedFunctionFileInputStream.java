package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.factory.ArrayTabulatedFunctionFactory;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;

public class TabulatedFunctionFileInputStream {
    public static void main(String[] args) throws IOException {
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("input/binary function.bin"))) {
            TabulatedFunction arrayFunc = FunctionsIO.readTabulatedFunction(inputStream, new ArrayTabulatedFunctionFactory());
            System.out.println(arrayFunc.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
