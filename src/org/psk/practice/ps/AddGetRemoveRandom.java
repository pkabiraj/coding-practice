package org.psk.practice.ps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * ADD, REMOVE and GETRANDOM, REMOVERANDOM with O(1)
 */
public class AddGetRemoveRandom<T> {

    static class RandomizedSet {

        private final Random random;
        private final Map<Integer, Integer> set;
        private final List<Integer> list;

        /** Initialize your data structure here. */
        public RandomizedSet() {
            random = new Random();
            set = new HashMap<>();
            list = new ArrayList<>();
        }

        /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
        public boolean insert(int val) {
            if (set.containsKey(val)) {
                return false;
            }
            set.put(val, list.size());
            list.add(list.size(), val);

            return true;
        }

        /** Removes a value from the set. Returns true if the set contained the specified element. */
        public boolean remove(int val) {
            if (!set.containsKey(val)) {
                return false;
            }
            int index = set.get(val);
            int lastValue = list.get(list.size() - 1);

            list.set(index, lastValue);
            set.put(lastValue, index);

            set.remove(val);
            list.remove(list.size() - 1);

            return true;
        }

        /** Get a random element from the set. */
        public int getRandom() {
            int rand = random.nextInt(set.size());
            return list.get(rand);
        }
    }

    private final Map<T, Integer> elemMap = new HashMap<>();
    private final List<T> elemIdxList = new ArrayList<>();
    private final Random random = new Random();

    public void add(T elem) {
        elemIdxList.add(elem);
        int index = elemIdxList.indexOf(elem);
        elemMap.put(elem, index);
    }

    public void remove(T elem) {
        int index = elemMap.get(elem);
        T t = elemIdxList.get(elemIdxList.size() - 1);

        elemIdxList.set(index, t);
        elemMap.put(t, index);

        elemMap.remove(elem);
        elemIdxList.remove(elemIdxList.size() - 1);
    }

    public T getRandom() {
        int nextInt = random.nextInt(elemIdxList.size());

        return elemIdxList.get(nextInt);
    }

    public T removeRandom() {
        int nextInt = random.nextInt(elemIdxList.size());
        T elem = elemIdxList.get(nextInt);

        remove(elem);

        return elem;
    }
}