package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * <pre>You manage a team of developers. You have to give concert tickets as a bonus to
 * the developers. For each developer, you know how many lines of code he wrote the
 * previous week, and you want to reward more productive developers.
 * The developers sit in a row. Each developer, save for the first and last, has two
 * neighbors. You must give each developer one or more tickets in such a way that if
 * a developer has written more lines of code than a neighbor, then he receives more
 * tickets than his neighbor.
 * Your task is to develop an algorithm that computes the minimum number of tickets
 * you need to buy to satisfy the constraint. For example, if Andy, Bob, Charlie, and
 * David sit in a row from left to right, and they wrote 300, 400, 500, and 200 lines of
 * code, respectively, the previous week, then Andy and David should receive one ticket
 * each, Bob should receive two tickets, and Charlie should receive three tickets, for a
 * total of seven tickets.
 * Write a program for computing the minimum number of tickets to distribute to the
 * developers, while ensuring that if a developer has written more lines of code than a
 * neighbor, then he receives more tickets than his neighbor.</pre>
 */
public class FairBonus {

    private static class EmployeeData {

        public Integer productivity;
        public Integer index;

        public EmployeeData(Integer productivity, Integer index) {
            this.productivity = productivity;
            this.index = index;
        }
    }

    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public static List<Integer> calculateBonusHeap(List<Integer> productivity) {
        PriorityQueue<EmployeeData> minHeap = new PriorityQueue<>(DEFAULT_INITIAL_CAPACITY,
                                                                  Comparator.comparingInt(
                                                                          (EmployeeData ed) -> ed.productivity)
                                                                          .thenComparingInt(ed -> ed.index));
        for (int i = 0; i < productivity.size(); ++i) {
            minHeap.offer(new EmployeeData(productivity.get(i), i));
        }
        // Initially assigns one ticket to everyone.
        List<Integer> tickets = new ArrayList<>(Collections.nCopies(productivity.size(), 1));
        // Fills tickets from lowest rating to highest rating.
        while (!minHeap.isEmpty()) {
            int nextDev = minHeap.peek().index;
            // Handles the left neighbor.
            if (nextDev > 0) {
                if (productivity.get(nextDev) > productivity.get(nextDev - 1)) {
                    tickets.set(nextDev, tickets.get(nextDev - 1) + 1);
                }
            }
            // Handles the right neighbor.
            if (nextDev + 1 < tickets.size()) {
                if (productivity.get(nextDev) > productivity.get(nextDev + 1)) {
                    tickets.set(nextDev, Math.max(tickets.get(nextDev), tickets.get(nextDev + 1) + 1));
                }
            }
            minHeap.poll();
        }
        return tickets;
    }

    public static List<Integer> calculateBonus(List<Integer> productivity) {
        // Initially assigns one ticket to everyone.
        List<Integer> tickets = new ArrayList<>(Collections.nCopies(productivity.size(), 1));
        // From left to right.
        for (int i = 1; i < productivity.size(); ++i) {
            if (productivity.get(i) > productivity.get(i - 1)) {
                tickets.set(i, tickets.get(i - 1) + 1);
            }
        }
        // From right to left.
        for (int i = productivity.size() - 2; i >= 0; --i) {
            if (productivity.get(i) > productivity.get(i + 1)) {
                tickets.set(i, Math.max(tickets.get(i), tickets.get(i + 1) + 1));
            }
        }
        return tickets;
    }
}
