package ru.ssau.tk.frolvas.labworksoop.concurrent;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

public class MultiplyingTask implements Runnable {
    private TabulatedFunction tabulatedFunction;

    public MultiplyingTask(TabulatedFunction tabulatedFunction) {
        this.tabulatedFunction = tabulatedFunction;
    }

    @SuppressWarnings("SynchronizeOnNonFinalField")
    @Override
    public void run() {
        for (int i = 0; i < tabulatedFunction.getCount(); i++) {
            synchronized (tabulatedFunction) {
                tabulatedFunction.setY(i, 2 * tabulatedFunction.getY(i));
            }
        }
        System.out.println("The thread " + Thread.currentThread().getName() + " has completed execution");
    }
}