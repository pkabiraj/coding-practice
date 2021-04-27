package org.psk.practice.ds;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given an array of student objects. Each student hasan integer-valued age field that is to be treated as a
 * key. Rearrange the elements of the array so that students of equal age appear together. The order in which different
 * ages appear is not important. How would your solution change if ages have to appear in sorted order?
 */
public class CountingSort {

    private static class Person {

        public Integer age;
        public String name;

        public Person(Integer k, String n) {
            age = k;
            name = n;
        }
    }

    /**
     * <pre>For example, consider the array ((Greg,14), (John,12), (Andy,11), (Jim,13),
     * (Phil,12), (Bob,13), (Chip,13), (Tim,14)). We can iterate through the array and record
     * the number of students of each age in a hash. Specifically, keys are ages, and values
     * are the corresponding counts. For the given example, on completion of the iteration,
     * the hash is (14, 2), (12, 2), (11,1), (13,3). This tells us that we need to write two students
     * of age 14, two students of age 12, one student of age 11 and three students of age 13.
     * We can write these students in any order, as long as we keep students of equal age
     * adjacent.
     * If we had a new array to write to, we can write the two students of age 14 starting
     * at index 0, the two students of age 12 starting at index 0 + 2 = 2, the one student of
     * age 11 at index 2+2 = 4, and the three students of age 13 starting at index 4 + 1 = 5.
     * We would iterate through the original array, and write each entry into the new array
     * according to these offsets. For example, after the first four iterations, the new array
     * would be ((Greg,14), (John,12), (Andy,11), (Jim,13), -).
     * The time complexity of this approach is 0(n), but it entails 0(n) additional space
     * for the result array. We can avoid having to allocate a new array by performing the
     * updates in-place. The idea is to maintain a subarray for each of the different types
     * of elements. Each subarray marks out entries which have not yet been assigned
     * elements of that type. We swap elements across these subarrays to move them to
     * their correct position.
     * In the program below, we use two hash tables to track the subarrays. One is the
     * starting offset of the subarray, the other its size. As soon as the subarray becomes
     * empty, we remove it.</pre>
     */
    public static void groupByAge(List<Person> people) {
        Map<Integer, Integer> ageToCount = new HashMap<>();
        for (Person p : people) {
            if (ageToCount.containsKey(p.age)) {
                ageToCount.put(p.age, ageToCount.get(p.age) + 1);
            } else {
                ageToCount.put(p.age, 1);
            }
        }
        Map<Integer, Integer> ageToOffset = new HashMap<>();
        int offset = 0;
        for (Map.Entry<Integer, Integer> kc : ageToCount.entrySet()) {
            ageToOffset.put(kc.getKey(), offset);
            offset += kc.getValue();
        }
        while (!ageToOffset.isEmpty()) {
            Map.Entry<Integer, Integer> from = ageToOffset.entrySet().iterator().next();
            Integer toAge = people.get(from.getValue()).age;
            Integer toValue = ageToOffset.get(toAge);
            Collections.swap(people, from.getValue(), toValue);
            // Use ageToCount to see when we are finished with a particular age.
            Integer count = ageToCount.get(toAge) - 1;
            ageToCount.put(toAge, count);
            if (count > 0) {
                ageToOffset.put(toAge, toValue + 1);
            } else {
                ageToOffset.remove(toAge);
            }
        }
    }
}
