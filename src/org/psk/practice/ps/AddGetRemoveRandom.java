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