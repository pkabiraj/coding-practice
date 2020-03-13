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

    private Map<T, Integer> elemMap = new HashMap<>();
    private List<T> elemIdxList = new ArrayList<>();
    private Random random = new Random();

    public void add(T elem) {
        elemIdxList.add(elem);
        int index = elemIdxList.indexOf(elem);
        elemMap.put(elem, index);
    }

    public void remove(T elem) {
        int index = elemMap.get(elem);
        elemMap.remove(elem);
        T t = elemIdxList.get(elemIdxList.size() - 1);
        elemIdxList.remove(elemIdxList.size() - 1);
        elemIdxList.set(index, t);
        elemMap.put(t, index);
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