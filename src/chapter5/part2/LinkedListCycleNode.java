/**
 * eBay.com Inc.
 * Copyright (c) 1995-2020 All Rights Reserved.
 */
package chapter5.part2;

/**
 * Search for linked list cycle entry node.
 *
 * Q: 如何找到链表的入环点？
 *
 * 假设链表有环，p1指针一次走一步、p2指针一次走两步，最终总会相遇在p点，
 * 如果从链表头head到入环点c的距离是s1，入环点c到相遇点p的距离是s2，相遇点p回到入环点点c的距离是s3，
 * 那么相遇的时候p1走过的路程是s1+s2，p2走过的路程是s1+s2+s3，
 * 而p2走的路程永远是p1的两倍，因此有等式：2*(s1+s2) = s1+s2+s3+s2 => 2s1+2s2 = s1+2s2+s3 => s1 = s3
 * 也就是说明head到入环点c的距离就是相遇点p回到入环点c的距离。
 *
 * 当p1=p2的时候，标记一次节点，而后从head开始走指针p3统计步数，p1继续移动，
 * 当p1=p3的时候，走了几步，链表head距离入环点c距离就是几。
 * 
 * @author Tony, Zhao
 * @version $Id: LinkedListCycleNode.java, v 0.1 2020-09-03 4:56 PM Tony, Zhao Exp $$
 */
public class LinkedListCycleNode {

    public static int distanceFromHeader2CycleEntry(Node head) {
        Node p1, p2, p3;
        p1 = p2 = p3 = head;
        // 头结点不空、下一个节点还有节点
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next.next;
            if (p1 == p2) {
                // from head node count 1
                int count = 1;
                while (p3 != p1) {
                    p3 = p3.next;
                    p1 = p1.next;
                    count++;
                }
                return count;
            }
        }

        // while end means no cycle and return 0
        return 0;
    }

    /**
     * 链表节点
     */
    private static class Node {
        int  data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) throws Exception {
        Node node1 = new Node(5);
        Node node2 = new Node(3);
        Node node3 = new Node(7);
        Node node4 = new Node(2);
        Node node5 = new Node(6);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node3;

        System.out
            .println("Cycle list entry from head length=" + distanceFromHeader2CycleEntry(node1));
    }

}