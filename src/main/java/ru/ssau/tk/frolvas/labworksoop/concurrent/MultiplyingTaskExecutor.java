package ru.ssau.tk.frolvas.labworksoop.concurrent;

import ru.ssau.tk.frolvas.labworksoop.functions.*;

import java.util.*;

public class MultiplyingTaskExecutor {

    public static void main(String[] args) {
        TabulatedFunction function = new LinkedListTabulatedFunction(new UnitFunction(), 0, 1000, 10000);
        List<Thread> threads = new ArrayList<>();
        ArrayList<MultiplyingTask> collection = new ArrayList<>();
        for (int i = 0; i < Runtime.getRuntime().availableProcessors() + 1; i++) {
            MultiplyingTask task = new MultiplyingTask(function);
            collection.add(task);
            threads.add(new Thread(task));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        while (!collection.isEmpty()) {
            Thread.onSpinWait();
            collection.removeIf(MultiplyingTask::isCompleted);
        }
        System.out.println(function);

    }
}
