package com.example.java;

import org.junit.Test;

public class LinkTest {
    /**
     * 10->5->7-9
     * <p>
     * 9->7->5->10
     */
    @Test
    public void main() {
        Node first = new Node(10);
        Node node1 = new Node(5);
        first.next = node1;
        Node node2 = new Node(7);
        node1.next = node2;
        Node node3 = new Node(9);
        node2.next = node3;
       reverse(first);
        System.out.println("main: ------reverse=" + next);
        while (next != null) {
            System.out.println("value=" + next.value);
            next = next.next;
        }


    }

    Node next = null;
    Node pre = null;

    private void reverse(Node node) {
        if (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
            reverse(node);
        }

    }

    public class Node {
        private Node next;
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }
}
