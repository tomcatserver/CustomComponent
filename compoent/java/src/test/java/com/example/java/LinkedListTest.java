package com.example.java;


import org.junit.Test;

public class LinkedListTest {
    int size = 0;

    /**
     * 删除链表中的节点
     * <p>
     * <p>
     * 请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
     * <p>
     * 现有一个链表 -- head = [4,5,1,9]，它可以表示为:
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 100->4->5->1->9
     */

    public ListNode removeNode(ListNode header, ListNode node) {
//        if (node1 != null && node.next == null) {
//            while (node1.next != null && node1.next.next != null) {
//                node1 = node1.next;
//            }
//            node1.next = null;
//        } else {
//            node.val = node.next.val;
//            node.next = node.next.next;
//        }

        if (header == node) {
            header = header.next;
            if (size > 0) {
                size--;
            }
        } else {
            ListNode node1 = header;
            while (true) {
                if (node1.next == node) {
                    node1.next = node.next;
                    if (size > 0) {
                        size--;
                    }
                    break;
                }
                if (node1.next != null) {
                    node1 = node1.next;
                } else {
                    System.out.println("没有找到该node");
                    break;
                }
            }
        }
        return header;

    }

    public void addNode(ListNode header, ListNode node) {
        ListNode node1 = header;
        while (node1 != null && node1.next != null) {
            node1 = node1.next;
        }
        node1.next = node;
    }

    public ListNode addNode(ListNode header, ListNode node, int selectIndex) {
        ListNode node1 = header;

        if (0 == selectIndex) {
            node.next = header;
            header = node;
        } else {
            while (true) {
                if (size == selectIndex - 1) {
                    break;
                }
                if (node1.next != null) {
                    node1 = node1.next;
                    size++;
                } else {
//                    throw new Exception("索引过大，不在链表内");
                    System.out.println("索引过大，不在链表内------");
                    break;
                }

            }
            node.next = node1.next;
            node1.next = node;
        }
        return header;
    }

    public ListNode reverse(ListNode node) {
//        ListNode pre = null;
//        ListNode next = null;
//        while (node != null) {
//            next = node.next;
//            node.next = pre;
//            pre = node;
//            node = next;
//
//        }
//        return pre;

        ListNode pre = null;
        ListNode next = null;
        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }

        while (node != null) {
            next = node.next;
            next.next = pre;
            pre = node;
            node = next;
        }


        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }


        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }


        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }


        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }


        while (node != null) {
            next = node.next;
            node.next = pre;
            pre = node;
            node = next;
        }
        return pre;
    }

    @Test
    public void main() {
        ListNode listNode4 = new ListNode(4);
        ListNode listNode5 = new ListNode(5);
        ListNode listNode1 = new ListNode(1);
        ListNode listNode9 = new ListNode(9);
        listNode4.next = listNode5;
        listNode5.next = listNode1;
        listNode1.next = listNode9;

        ListNode header = listNode4;
        while (header != null) {
            System.out.println("main:------ val=" + header.val);
            header = header.next;
        }
//        addNode(listNode4, new ListNode(10));
//        ListNode listNode = addNode(listNode4, new ListNode(100), 3);
//        ListNode listNode = removeNode(listNode1, listNode4);
        ListNode listNode = reverse(listNode4);
        ListNode header2 = listNode;
        while (header2 != null) {
            System.out.println("main:---final--- val=" + header2.val + ",size=" + size);
            header2 = header2.next;
        }
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

    }

}
