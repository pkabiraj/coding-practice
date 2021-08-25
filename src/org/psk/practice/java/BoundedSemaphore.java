package org.psk.practice.java;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedSemaphore {

    private final int bound;

    private final AtomicInteger count = new AtomicInteger(0);
    private final Lock lock = new ReentrantLock();

    public BoundedSemaphore(final int bound) {
        this.bound = bound;
    }

    public boolean acquireLock() throws InterruptedException {
        lock.lock();
        try {
            if (count.get() >= bound) {
                return false;
            }
            count.incrementAndGet();
            return true;
        } finally {
            lock.unlock();
        }
    }

    public void releaseLock() {
        lock.lock();
        try {
            count.decrementAndGet();
        } finally {
            lock.unlock();
        }
    }

//    private int signals = 0;
//    private int bound   = 0;
//
//    public BoundedSemaphore(int upperBound){
//        this.bound = upperBound;
//    }
//
//    public synchronized void take() throws InterruptedException{
//        while(this.signals == bound) wait();
//        this.signals++;
//        this.notify();
//    }
//
//    public synchronized void release() throws InterruptedException{
//        while(this.signals == 0) wait();
//        this.signals--;
//        this.notify();
//    }
}
