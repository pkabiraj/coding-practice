package org.psk.practice.java.readwrite;

import java.util.Vector;

public class Writer {

    /*
     * Put some Integers on the queue so the
     * readers have something to read.
     */
    public void fill(Vector<Integer> queue) {
        for (int i = 0; i < 50; i++) {
            queue.add(i);
            synchronized (queue) {
                queue.notifyAll();
            }
        }
    }

    public static void main(String[] args) {
        // This vector will be the communication channel between the writer and the readers.
        Vector<Integer> queue = new Vector<>();
        Writer writer1 = new Writer();

        // Start two readers.
        Reader reader1 = new Reader(queue, "Reader 1");
        reader1.start();
        Reader reader2 = new Reader(queue, "Reader 2");
        reader2.start();

        // Fill up the queue. The waiting readers will wake up and start emptying the queue.
        writer1.fill(queue);
    }
}
