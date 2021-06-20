package org.psk.practice.java;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {

    private BlockingQueue<Runnable> taskQueue;
    private Set<PoolableThread> workers;
    private volatile boolean stopped = false;

    public ThreadPool(final int size) {
        taskQueue = new LinkedBlockingQueue<>();
        workers = new HashSet<>(size);

        for (int i = 0; i < size; i++) {
            workers.add(new PoolableThread(taskQueue));
        }
    }

    public void execute(final Runnable task) {
        if (stopped) {
            throw new IllegalStateException("Thread pool is stopped, cannot execute anymore");
        }

        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        stopped = true;
        synchronized (this) {
            for (final PoolableThread worker : workers) {
                worker.cancel();
            }
        }
    }

    private static class PoolableThread extends Thread {

        private final BlockingQueue<Runnable> taskQueue;
        private volatile boolean stopped = false;

        public PoolableThread(final BlockingQueue<Runnable> taskQueue) {
            this.taskQueue = taskQueue;
            start();
        }

        @Override
        public void run() {
            while (!stopped) {
                try {
                    final Runnable runnable = taskQueue.take();
                    runnable.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void cancel() {
            stopped = true;
            this.interrupt();
        }
    }
}
