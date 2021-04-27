package org.psk.practice.ds.graph;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Boggle {

    private static final Set<String> ENGLISH_WORDS = new HashSet<>(
            Arrays.asList("apple", "pickle", "side", "kick", "sick", "mood", "cat",
                          "cats", "man", "super", "antman", "godzilla", "dog", "dot",
                          "sine", "cos", "signal", "bitcoin", "cool", "zapper"));

    private enum Direction {
        UL_UL(-1, -1, "↖"),
        UL_TL(-1, 0, "↑"),
        UL_RL(-1, 1, "↗"),
        RL_LR(0, 1, "→"),
        RL_RR(1, 1, "↘"),
        BL_BR(1, 0, "↓"),
        RL_LL(1, -1, "↙"),
        SL_LL(0, -1, "←");

        private final int row;
        private final int col;
        private final String direction;

        Direction(final int row, final int col, final String direction) {
            this.row = row;
            this.col = col;
            this.direction = direction;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public String getDirection() {
            return direction;
        }
    }

    public static void main(String[] args) {
        final char[][] matrix = {
                {'c', 'n', 't', 's', 's'},
                {'d', 'a', 't', 'i', 'n'},
                {'o', 'o', 'm', 'e', 'l'},
                {'s', 'i', 'k', 'n', 'd'},
                {'p', 'i', 'c', 'l', 'e'}
        };

        final Map<String, Set<String>> edgeNgrams = buildPrefixMap(ENGLISH_WORDS);

        final Boggle boggle = new Boggle();
        boggle.boggle(edgeNgrams, matrix);
    }

    private static Map<String, Set<String>> buildPrefixMap(final Set<String> englishWords) {
        final Map<String, Set<String>> prefixMap = new HashMap<>();
        for (final String word : englishWords) {
            for (int i = 0; i < word.length(); i++) {
                final String prefix = word.substring(0, i + 1);
                final Set<String> words = prefixMap.getOrDefault(prefix, new HashSet<>());
                words.add(word);
                prefixMap.put(prefix, words);
            }
        }
        return prefixMap;
    }

    public void boggle(final Map<String, Set<String>> edgeNgrams,
                       final char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                dfs(matrix, edgeNgrams, new HashSet<>(), row, col, "",
                    String.format("directions from (%d,%d)(%s) go ", row, col, matrix[row][col]));
            }
        }
    }

    private void dfs(final char[][] matrix,
                     final Map<String, Set<String>> edgeNgrams,
                     final Set<String> visited,
                     final int row,
                     final int col,
                     final String prefix,
                     final String direction) {
        if (visited.contains(getKey(row, col))) {
            return;
        }

        visited.add(getKey(row, col));
        final String nowWord = prefix + matrix[row][col];
        if (!edgeNgrams.containsKey(nowWord)) {
            return;
        }
        if (edgeNgrams.getOrDefault(nowWord, Collections.emptySet())
                .contains(nowWord)) {
            System.out.printf("Found '%s' %s%n", nowWord, direction);
        }

        for (Direction neighbour : Direction.values()) {
            int r = row + neighbour.getRow();
            int c = col + neighbour.getCol();
            if (isValid(r, c, matrix, visited, edgeNgrams, nowWord)) {
                dfs(matrix, edgeNgrams, visited, r, c, nowWord, direction + " " + neighbour.getDirection());
            }
        }
    }

    private boolean isValid(final int row,
                            final int col,
                            final char[][] matrix,
                            final Set<String> visited,
                            final Map<String, Set<String>> edgeNgrams,
                            final String prefix) {
        return row >= 0 && row < matrix.length
               && col >= 0 && col < matrix[0].length
               && !visited.contains(getKey(row, col))
               && edgeNgrams.containsKey(prefix + matrix[row][col]);
    }

    private String getKey(int row, int col) {
        return row + "::" + col;
    }
}
