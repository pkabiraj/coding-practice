package org.psk.practice.ps;

import java.util.Arrays;
import java.util.List;

public class GasUp {

    private static class CityAndRemainingGas {

        public Integer city;
        public Integer remainingGallons;

        public CityAndRemainingGas(Integer city, Integer remainingGallons) {
            this.city = city;
            this.remainingGallons = remainingGallons;
        }

        @Override
        public String toString() {
            return "CityAndRemainingGas{" +
                   "city=" + city +
                   ", remainingGallons=" + remainingGallons +
                   '}';
        }
    }

    private static final int MPG = 20;

    // gallons[i] is the amount of gas in city i, and distances[i] is the distance city i to the next city.
    public static CityAndRemainingGas findAmpleCity(List<Integer> gallons, List<Integer> distances) {
        int remainingGallons = 0;
        CityAndRemainingGas min = new CityAndRemainingGas(0, 0);
        final int numCities = gallons.size();
        for (int i = 1; i < numCities; ++i) {
            remainingGallons += gallons.get(i - 1) - distances.get(i - 1) / MPG;
            if (remainingGallons < min.remainingGallons) {
                min = new CityAndRemainingGas(i, remainingGallons);
            }
        }
        return min;
    }

    public static void main(String[] args) {
        final List<Integer> gallons = Arrays.asList(50, 20, 5, 30, 25, 10, 10);
        final List<Integer> distances = Arrays.asList(900, 600, 200, 400, 600, 200, 100);
        final CityAndRemainingGas ampleCity = findAmpleCity(gallons, distances);

        System.out.println(ampleCity);
    }
}
