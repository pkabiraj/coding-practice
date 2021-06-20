package org.psk.practice.java;

import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LRUCache<K, V> {

    private final int maxSize;
    private final Map<K, V> internalCache;
    private final Deque<K> trackingQueue;

    private final Lock readLock;
    private final Lock writeLock;

    public LRUCache(final int maxSize) {
        this.maxSize = maxSize;
        internalCache = new ConcurrentHashMap<>(maxSize);
        trackingQueue = new ConcurrentLinkedDeque<>();

        final ReadWriteLock lock = new ReentrantReadWriteLock();
        readLock = lock.readLock();
        writeLock = lock.writeLock();
    }

    public V get(final K key) {
        readLock.lock();
        try {
            final V value = internalCache.get(key);
            if (value != null) {
                makeKeyRecent(key);
            }
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public V put(final K key, final V value) {
        writeLock.lock();
        try {
            if (size() >= maxSize && !internalCache.containsKey(key)) {
                final K lruKey = trackingQueue.poll();
                evict(lruKey);
            }

            final V oldValue = internalCache.put(key, value);
            makeKeyRecent(key);
            return oldValue;
        } finally {
            writeLock.unlock();
        }
    }

    public V evict(final K key) {
        writeLock.lock();
        try {
            final V value = internalCache.remove(key);
            if (value != null) {
                trackingQueue.remove(key);
            }
            return value;
        } finally {
            writeLock.unlock();
        }
    }

    public int size() {
        return internalCache.size();
    }

    private void makeKeyRecent(final K key) {
        trackingQueue.remove(key);
        trackingQueue.offerLast(key);
    }

    public static void main(String[] args) {
        final LRUCache<Integer, Integer> cache = new LRUCache<>(5);

        System.out.println(cache.put(1, 1));
        System.out.println(cache.put(2, 2));
        System.out.println(cache.put(3, 3));
        System.out.println(cache.put(4, 4));
        System.out.println(cache.put(5, 5));

        System.out.println(cache.size());

        System.out.println(cache.get(4));

        System.out.println(cache.put(4, 44));

        System.out.println(cache.get(4));

        System.out.println(cache.size());

        System.out.println(cache.put(6, 6));
        System.out.println(cache.size());

        System.out.println(cache.evict(4));

        System.out.println(cache.size());
        System.out.println(cache.get(4));
        System.out.println(cache.get(1));
        System.out.println(cache.get(6));
    }
}
