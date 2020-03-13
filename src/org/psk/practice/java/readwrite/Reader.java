package org.psk.practice.java.readwrite;

import java.util.Vector;

public class Reader extends Thread {
    // The queue is the communication channel between
    // this reader and the writer.
    private final Vector<Integer> queue;

    // A name so we can tell the readers apart.
    private final String name;

    /**
     * Create a reader to read objects from a queue.
     *
     * @param queue Contains the flow of objects from the writer.
     * @param name  A name so we can distinguish readers.
     */
    public Reader(Vector<Integer> queue,
                  String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        for (; ; ) {
            synchronized (queue) { // acquire the monitor
                while (queue.isEmpty()) {
                    try {
                        queue.wait(); // releases the monitor
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        // Nothing to do here. If the queue is empty,
                        // might as well go back to waiting.
                    }
                }

                // At this point the monitor has been re-acquired
                Object o = queue.remove(0);
                System.out.println(name + " got job number: " + o);
            } // release the monitor at the end of the synchronized block
        }
    }
}