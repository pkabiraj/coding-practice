package org.psk.practice.ds.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <pre>Suppose you are building a library and have following definition of a function and two methods register and
 * findMatches. Register method registers a function and its argumentTypes in the library and findMatches accepts an
 * input argument list and tries to find all the functions that match this input argument list.
 * If a function is marked as isVariadic=true, then the last argument can occur one or more number of times.</pre>
 *<pre>
 * Example:
 * FuncA: [String, Integer, Integer]; isVariadic = false
 * FuncB: [String, Integer]; isVariadic = true
 * FuncC: [Integer]; isVariadic = true
 * FuncD: [Integer, Integer]; isVariadic = true
 * FuncE: [Integer, Integer, Integer]; isVariadic = false
 * FuncF: [String]; isVariadic = false
 * FuncG: [Integer]; isVariadic = false
 *
 * findMatches({String}) -> [FuncF]
 * findMatches({Integer}) -> [FuncC, FuncG]
 * findMatches({Integer, Integer, Integer, Integer}) -> [FuncC, FuncD]
 * findMatches({Integer, Integer, Integer}) -> [FuncC, FuncD, FuncE]
 * findMatches({String, Integer, Integer, Integer}) -> [FuncB]
 * findMatches({String, Integer, Integer}) -> [FuncA, FuncB]</pre>
 */
public class FunctionLibrary {

    public static void main(String[] args) {
        final FunctionLibrary functionLibrary = new FunctionLibrary();
        final Set<Function> functions = new HashSet<>();
        functions.add(new Function("A", Arrays.asList("String", "Integer", "Integer"), false));
        functions.add(new Function("B", Arrays.asList("String", "Integer"), true));
        functions.add(new Function("C", Arrays.asList("Integer"), true));
        functions.add(new Function("D", Arrays.asList("Integer", "Integer"), true));
        functions.add(new Function("E", Arrays.asList("Integer", "Integer", "Integer"), false));
        functions.add(new Function("F", Arrays.asList("String"), false));
        functions.add(new Function("G", Arrays.asList("Integer"), false));

        functionLibrary.register(functions);

        System.out.println(functionLibrary.findMatches(Arrays.asList("String")));
        System.out.println(functionLibrary.findMatches(Arrays.asList("Integer")));
        System.out.println(functionLibrary.findMatches(Arrays.asList("Integer", "Integer", "Integer", "Integer")));
        System.out.println(functionLibrary.findMatches(Arrays.asList("Integer", "Integer", "Integer")));
        System.out.println(functionLibrary.findMatches(Arrays.asList("String", "Integer", "Integer", "Integer")));
        System.out.println(functionLibrary.findMatches(Arrays.asList("String", "Integer", "Integer")));
    }

    private static class Function {
        String name;
        List<String> argumentTypes;
        boolean isVariadic;

        Function(String name, List<String> argumentTypes, boolean isVariadic) {
            this.name = name;
            this.argumentTypes = argumentTypes;
            this.isVariadic = isVariadic;
        }

        @Override
        public String toString() {
            return '{' + name + ", " + isVariadic + '}';
        }
    }

    private static class TrieNode {
        Map<String, TrieNode> children;
        Set<Function> functions;

        TrieNode() {
            children = new HashMap<>();
            functions = new HashSet<>();
        }
    }

    private final TrieNode root = new TrieNode();

    public void register(Set<Function> functionSet)  {
        if (functionSet == null || functionSet.isEmpty()) {
            return;
        }
        for (final Function function : functionSet) {
            register(function);
        }
    }

    private void register(final Function function) {
        TrieNode current = root;
        for (final String argument : function.argumentTypes) {
            TrieNode trieNode = current.children.get(argument);
            if (trieNode == null) {
                trieNode = new TrieNode();
                current.children.put(argument, trieNode);
            }
            current = trieNode;
        }
        current.functions.add(function);
    }

    public List<Function> findMatches(List<String> argumentTypes) {
        final List<Function> matches = new ArrayList<>();
        final Map<String, Integer> argCount = countArgument(argumentTypes);
        findMatches(argumentTypes, matches, argCount);
        return matches;
    }

    private void findMatches(final List<String> argumentTypes,
                             final List<Function> matches,
                             final Map<String, Integer> argCount) {
        TrieNode current = root;
        for (final String argument : argumentTypes) {
            final TrieNode trieNode = current.children.get(argument);
            calculateArgs(argCount, argument);
            if (trieNode != null) {
                current = trieNode;
                findMatches(current.functions, matches, argCount);
            }
        }
    }

    private void findMatches(final Set<Function> functions,
                             final List<Function> matches,
                             final Map<String, Integer> argCount) {
        if (!functions.isEmpty()) {
            for (final Function function : functions) {
                if ((function.isVariadic && argCount.size() == 1) || argCount.isEmpty()) {
                    matches.add(function);
                }
            }
        }
    }

    private void calculateArgs(final Map<String, Integer> argCount, final String argument) {
        final Integer countOrDefault = argCount.getOrDefault(argument, 0);
        if (countOrDefault == 1) {
            argCount.remove(argument);
        } else if (countOrDefault > 1) {
            argCount.put(argument, countOrDefault - 1);
        }
    }

    private Map<String, Integer> countArgument(final List<String> argumentTypes) {
        final Map<String, Integer> argCount = new HashMap<>();
        for (final String argument : argumentTypes) {
            final Integer countOrDefault = argCount.getOrDefault(argument, 0);
            argCount.put(argument, countOrDefault + 1);
        }
        return argCount;
    }
}
