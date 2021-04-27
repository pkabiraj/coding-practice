package org.psk.practice.ds.recursionAndDP;

import java.util.ArrayList;
import java.util.List;

/**
 * The power set of a set S is the set of all subsets of S, including both the empty set 0 and S itself. The power set
 * of (0,1, 2) is (0,(O),(1),(2),(O,1),(1,2),(O,2),(O,1,2)).
 */
public class GeneratePowerSet {

    public static List<List<Integer>> generatePowerSet(List<Integer> inputSet) {
        List<List<Integer>> powerSet = new ArrayList<>();
        directedPowerSet(inputSet, 0, new ArrayList<Integer>(), powerSet);
        return powerSet;
    }

    // Generate all subsets whose intersection with inputSet[<9] , ..., inputSet[toBeSelected - 1] is exactly
    // selectedSoFar .
    private static void directedPowerSet(List<Integer> inputSet, int toBeSelected, List<Integer> selectedSoFar,
                                         List<List<Integer>> powerSet) {
        if (toBeSelected == inputSet.size()) {
            powerSet.add(new ArrayList<>(selectedSoFar));
            return;
        }
        // Generate all subsets that contain inputSet[toBeSelected].
        selectedSoFar.add(inputSet.get(toBeSelected));
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
        // Generate all subsets that do not contain inputSet[toBeSelected].
        selectedSoFar.remove(selectedSoFar.size() - 1);
        directedPowerSet(inputSet, toBeSelected + 1, selectedSoFar, powerSet);
    }
}
