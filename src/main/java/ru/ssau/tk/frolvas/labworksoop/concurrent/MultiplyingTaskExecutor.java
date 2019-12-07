package ru.ssau.tk.frolvas.labworksoop.concurrent;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

import java.util.*;

public class MultiplyingTaskExecutor {
    @SuppressWarnings("SynchronizationOnLocalVariableOrMethodParameter")
    public static void main(String[] args) throws InterruptedException {
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 0, 1000, 10000);
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 1; i++) {
            MultiplyingTask task = new MultiplyingTask(function);
            synchronized (task) {
                threads.add(new Thread(task));
            }
        }
        for (Thread thread : threads) {
            thread.start();
        }
        Thread.sleep(2000);
        System.out.println(function);
    }
}
