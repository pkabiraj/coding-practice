package org.psk.practice.ds;

/**
 * The Class ReverseSingleList is to reverse a single linked list and print it.
 */
public class ReverseSingleList {

    public static void main(String[] args) {
        SingleLinkList root = createList();

        System.out.print("Original : ");
        DSUtil.printList(root);

        SingleLinkList reversedRoot = reverseSingleList(createList());
        SingleLinkList reversedRoot1 = kReverseSingleList(createList(), 3);
        SingleLinkList reversedRoot2 = kAltReverseSingleList(createList(), 3, true);
        SingleLinkList reversedRoot3 = kAltReverseList(createList(), 3);

        System.out.print("Reversed : ");
        DSUtil.printList(reversedRoot);
        DSUtil.printList(reversedRoot1);
        DSUtil.printList(reversedRoot2);
        DSUtil.printList(reversedRoot3);
    }

    private static SingleLinkList createList() {
        SingleLinkList current = new SingleLinkList(1);
        SingleLinkList root, next = null;

        root = current;
        for (int i = 2; i <= 10; i++) {
            next = new SingleLinkList(i);
            current.setNext(next);
            current = next;
        }
        return root;
    }

    private static SingleLinkList reverseSingleList(SingleLinkList root) {
        SingleLinkList current = root;
        SingleLinkList next;
        SingleLinkList previous = null;
        while (current != null) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
        }
        return previous;
    }

    private static SingleLinkList kReverseSingleList(SingleLinkList root, int k) {
        if (root == null) {
            return null;
        }

        SingleLinkList current = root;
        SingleLinkList next = null, previous = null;
        int count = 0;
        while (current != null && count < k) {
            next = current.getNext();
            current.setNext(previous);
            previous = current;
            current = next;
            count++;
        }

        if (next != null) {
            root.setNext(kReverseSingleList(next, k));
        }

        return previous;
    }

    private static SingleLinkList kAltReverseSingleList(SingleLinkList root, int k, boolean doReverse) {
        if (root == null) {
            return null;
        }

        int count = 1;
        SingleLinkList previous = null;
        SingleLinkList current = root;
        SingleLinkList next;

        /*
         * The loop serves two purposes 1) If b is true, then it reverses the k
         * nodes 2) If b is false, then it moves the current pointer
         */
        while (current != null && count <= k) {
            next = current.getNext();

            /* Reverse the nodes only if b is true */
            if (doReverse) {
                current.setNext(previous);
            }

            previous = current;
            current = next;
            count++;
        }

        /*
         * 3) If b is true, then node is the kth node. So attach rest of the
         * list after node. 4) After attaching, return the new head
         */
        if (doReverse) {
            root.setNext(kAltReverseSingleList(current, k, !doReverse));
            return previous;
        }

        /*
         * If b is not true, then attach rest of the list after prev. So attach
         * rest of the list after prev
         */
        else {
            previous.setNext(kAltReverseSingleList(current, k, !doReverse));
            return root;
        }
    }

    private static SingleLinkList kAltReverseList(SingleLinkList root, int k) {
        SingleLinkList current = root;
        SingleLinkList next, prev = null;
        int count = 0;

        /* 1) reverse first k nodes of the linked list */
        while (current != null && count < k) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
            count++;
        }

        /*
         * 2) Now head points to the kth node. So change next of head to (k+1)th
         * node
         */
        if (root != null) {
            root.setNext(current);
        }

        /*
         * 3) We do not want to reverse next k nodes. So move the current
         * pointer to skip next k nodes
         */
        count = 0;
        while (count < k - 1 && current != null) {
            current = current.getNext();
            count++;
        }

        /*
         * 4) Recursively call for the list starting from current->next. And
         * make rest of the list as next of first node
         */
        if (current != null) {
            current.setNext(kAltReverseList(current.getNext(), k));
        }

        /* 5) prev is new head of the input list */
        return prev;
    }
}