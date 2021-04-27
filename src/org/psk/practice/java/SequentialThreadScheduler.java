package org.psk.practice.java;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * There are three threads in a process. How do you schedule these three threads in order to print A B C ... Z A B C ...
 * Z picked up sequentially by the threads? So, Thread_1 will print A, Thread_2 will print B, ... Thread_2 will print Z,
 * Thread_3 will print A, Thread_1 will print B ...
 */
public class SequentialThreadScheduler {

    private static final int COUNT = 3;
    private static final int SLEEP = 500;
    private static final char[] ALPHABETS =
            {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
             'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void main(String[] args) {
        final SimpleThread[] threads = new SimpleThread[COUNT];
        final AtomicInteger counter = new AtomicInteger(0);
        for (int i = 0; i < COUNT; ++i) {
            threads[i] = new SimpleThread(i + 1, counter);
        }

        int index = 0;
        while (true) {
            synchronized (threads[index]) {
                threads[index].notify();
            }

            try {
                Thread.sleep(SLEEP);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            index = (++index) % COUNT;
        }
    }

    public static class SimpleThread extends Thread {

        private final AtomicInteger counter;

        public SimpleThread(final int value,
                            final AtomicInteger counter) {
            this.counter = counter;
            setName(getClass().getSimpleName() + "_" + value);
            start();
        }

        public void run() {
            while (true) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.println(
                            Thread.currentThread().getName() + "....." + ALPHABETS[counter.getAndIncrement()]);
                    if (counter.get() == ALPHABETS.length) {
                        counter.set(0);
                    }
                }
            }
        }
    }
}