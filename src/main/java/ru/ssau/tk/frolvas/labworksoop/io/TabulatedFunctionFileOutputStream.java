package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

import java.io.*;

public class TabulatedFunctionFileOutputStream {
    public static void main(String[] args) {
        double[] xValues = {1, 3, 5};
        double[] yValues = {1, 3, 5};
        ArrayTabulatedFunction arrayFunc = new ArrayTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction linkFunc = new LinkedListTabulatedFunction(xValues, yValues);
        try (BufferedOutputStream outputStreamArr = new BufferedOutputStream(new FileOutputStream(new File("output/array function.bin")));
             BufferedOutputStream outputStreamLink = new BufferedOutputStream(new FileOutputStream(new File("output/linked list function.bin")))) {
            FunctionsIO.writeTabulatedFunction(outputStreamArr, arrayFunc);
            FunctionsIO.writeTabulatedFunction(outputStreamLink, linkFunc);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
