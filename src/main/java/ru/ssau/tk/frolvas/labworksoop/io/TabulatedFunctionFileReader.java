package ru.ssau.tk.frolvas.labworksoop.io;

import ru.ssau.tk.frolvas.labworksoop.functions.factory.*;

import java.io.*;

public class TabulatedFunctionFileReader {
    public static void main(String[] args) {
        try (BufferedReader readerLink = new BufferedReader(new FileReader("input/function.txt"));
        BufferedReader readerArr = new BufferedReader(new FileReader("input/function.txt"))) {
            System.out.println(FunctionsIO.readTabulatedFunction(readerLink, new LinkedListTabulatedFunctionFactory()).toString());
            System.out.println(FunctionsIO.readTabulatedFunction(readerArr, new ArrayTabulatedFunctionFactory()).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
