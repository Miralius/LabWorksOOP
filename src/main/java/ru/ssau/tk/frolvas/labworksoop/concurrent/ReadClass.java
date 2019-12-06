package ru.ssau.tk.frolvas.labworksoop.concurrent;

import ru.ssau.tk.frolvas.labworksoop.functions.TabulatedFunction;

public class ReadClass implements Runnable {
    private TabulatedFunction tabulatedFunction;

    ReadClass(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                System.out.printf("After read: i = %d, x = %f, y = %f\n", i, tabulatedFunction.getX(i), tabulatedFunction.getY(i));
            }
        }
    }
}
