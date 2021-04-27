package org.psk.practice.ds.trees;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

/**
 * <pre>Consider a server that a large number of clients connect to. Each client is identified by
 * a string. Each client has a "credit", which is a non-negative integer value. The server
 * needs to maintain a data structure to which clients can be added, removed, queried,
 * or updated. In addition, the server needs to be able to add a specified number of
 * credits to all clients simultaneously.
 * Design a data structure that implements the following methods:
 * • Insert: add a client with specified credit, replacing any existing entry for the
 * client.
 * • Remove: delete the specified client.
 * • Lookup: return the number of credits associated with the specified client.
 * • Add-to-all: increment the credit count for all current clients by the specified
 * amount.
 * • Max: return a client with the highest number of credits.</pre>
 */
public class ClientsCreditsInfo {

    private int offset = 0;
    private final Map<String, Integer> clientToCredit = new HashMap<>();
    private final NavigableMap<Integer, Set<String>> creditToClients = new TreeMap<>();

    public void insert(String clientID, int c) {
        remove(clientID);
        clientToCredit.put(clientID, c - offset);
        Set<String> set = creditToClients.computeIfAbsent(c - offset, k -> new HashSet<>());
        set.add(clientID);
    }

    public boolean remove(String clientID) {
        Integer clientCredit = clientToCredit.get(clientID);
        if (clientCredit != null) {
            creditToClients.get(clientCredit).remove(clientID);
            if (creditToClients.get(clientCredit).isEmpty()) {
                creditToClients.remove(clientCredit);
            }
            clientToCredit.remove(clientID);
            return true;
        }
        return false;
    }

    public int lookup(String clientID) {
        Integer clientCredit = clientToCredit.get(clientID);
        return clientCredit == null ? -1 : clientCredit + offset;
    }

    public void addAll(int C) {
        offset += C;
    }

    public String max() {
        return creditToClients.isEmpty() ? "" : creditToClients.lastEntry().getValue().iterator().next();
    }
}
