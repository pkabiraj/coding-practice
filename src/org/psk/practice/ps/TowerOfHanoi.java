package org.psk.practice.ps;

import java.util.Stack;

public class TowerOfHanoi {

    public static void main(String[] args) {
        int n = 3;
        Tower[] towers = new Tower[n];
        for (int i = 0; i < 3; i++) {
            towers[i] = new Tower(i);
        }
        for (int i = n - 1; i >= 0; i--) {
            towers[0].add(i);
        }
        towers[0].moveDisks(n, towers[2], towers[1]);
    }

    private static class Tower {

        private final Stack<Integer> disks;
        private final int index;

        public Tower(int index) {
            this.index = index;
            disks = new Stack<>();
        }

        public int index() {
            return index;
        }

        public void add(Integer disk) {
            if (!disks.isEmpty() && disks.peek() <= disk) {
                System.out.println("Error placing disk " + disk);
            } else {
                disks.push(disk);
            }
        }

        public void moveTopTo(Tower t) {
            Integer top = disks.pop();
            t.add(top);

            System.out.println("Moved disk " + (top + 1) + " from " + (index() + 1) + " to " + (t.index() + 1));
        }

        public void moveDisks(Integer n, Tower destination, Tower buffer) {
            if (n > 0) {
                moveDisks(n - 1, buffer, destination);
                moveTopTo(destination);
                buffer.moveDisks(n - 1, destination, this);
            }
        }
    }
}