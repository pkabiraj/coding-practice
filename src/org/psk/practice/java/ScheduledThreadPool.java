package org.psk.practice.java;

import static java.util.concurrent.TimeUnit.NANOSECONDS;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class ScheduledThreadPool {

    private final BlockingQueue<ScheduledTask> taskQueue;
    private final Set<PoolableThread> workers;
    private volatile boolean stopped = false;

    public ScheduledThreadPool(final int size) {
        taskQueue = new DelayQueue<>();
        workers = new HashSet<>(size);

        for (int i = 0; i < size; i++) {
            workers.add(new PoolableThread(taskQueue));
        }
    }

    public void schedule(final Runnable task, long delay, TimeUnit unit) {
        if (stopped) {
            throw new IllegalStateException("Scheduled thread pool has stopped, cannot execute anymore");
        }

        try {
            final ScheduledTask scheduledTask = new ScheduledTask(task, getTriggerTime(delay, unit));
            taskQueue.put(scheduledTask);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private long getTriggerTime(final long delay, final TimeUnit unit) {
        return System.nanoTime() + unit.toNanos(delay);
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

        private final BlockingQueue<ScheduledTask> taskQueue;
        private volatile boolean stopped = false;

        public PoolableThread(final BlockingQueue<ScheduledTask> taskQueue) {
            this.taskQueue = taskQueue;
            start();
        }

        @Override
        public void run() {
            while (!stopped) {
                try {
                    final ScheduledTask scheduledTask = taskQueue.take();
                    scheduledTask.runnable.run();
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

    private static class ScheduledTask implements Delayed {

        private final Runnable runnable;
        private final long triggerTime;

        public ScheduledTask(final Runnable runnable, final long triggerTime) {
            this.runnable = runnable;
            this.triggerTime = triggerTime;
        }

        @Override
        public long getDelay(final TimeUnit unit) {
            return unit.convert(triggerTime - System.nanoTime(), NANOSECONDS);
        }

        @Override
        public int compareTo(final Delayed other) {
            if (other == this) { // compare zero if same object
                return 0;
            }
            if (other instanceof ScheduledTask) {
                return Long.compare(triggerTime, ((ScheduledTask) other).triggerTime);
            }
            return Long.compare(getDelay(NANOSECONDS), other.getDelay(NANOSECONDS));
        }
    }
}
