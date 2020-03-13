package org.psk.practice.ds;

public final class DSUtil {

    private DSUtil() {
        // Utility class. Should not allow to create objects of this class.
    }

    /**
     * Prints the list.
     *
     * @param root the root
     */
    public static void printList(SingleLinkList root) {
        SingleLinkList temp = root;
        while (temp != null) {
            System.out.print(temp.getValue() + " -> ");
            temp = temp.getNext();
        }
        System.out.println();
    }
}