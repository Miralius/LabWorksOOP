package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

import java.io.*;

public class TabulatedFunctionFileWriter {
    public static void main(String[] args) {
        ArrayTabulatedFunction arrayTabulatedFunction = new ArrayTabulatedFunction(new SqrtFunction(), 0, 10, 11);
        LinkedListTabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new Cube(), 0, 10, 11);

        try (BufferedWriter fileWriterFirst = new BufferedWriter(new FileWriter("output/array function.txt"));
             BufferedWriter fileWriterSecond = new BufferedWriter(new FileWriter("output/linked list function.txt"))) {
            FunctionsIO.writeTabulatedFunction(fileWriterFirst, arrayTabulatedFunction);
            FunctionsIO.writeTabulatedFunction(fileWriterSecond, linkedListTabulatedFunction);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
