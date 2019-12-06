package ru.ssau.tk.frolvas.labworksoop.concurrent;

import ru.ssau.tk.frolvas.labworksoop.functions.ConstantFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.LinkedListTabulatedFunction;
import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;

public class ReadWriteTaskExecutor {
    public static void main(String[] args) {
        TabulatedFunction linkedListTabulatedFunction = new LinkedListTabulatedFunction(new ConstantFunction(-3), 1, 200, 200);
        Thread readThread = new Thread(new ReadClass(linkedListTabulatedFunction));
        Thread writeThread = new Thread(new WriteTask(linkedListTabulatedFunction, 0.5));
        readThread.start();
        writeThread.start();
    }
}
