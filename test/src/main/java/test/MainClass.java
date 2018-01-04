package test;


/* -----------------------------------
 *  WARNING:
 * -----------------------------------
 *  Your code may fail to compile
 *  because it contains public class
 *  declarations.
 *  To fix this, please remove the
 *  "public" keyword from your class
 *  declarations.
 */


//  Definition for singly-linked list.

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum = l1.val + l2.val;
        int high = sum / 10;
        ListNode res = new ListNode(sum % 10);

        ListNode pre = res;
        while ((l1 = l1.next) != null | (l2 = l2.next) != null) {
            if (l1 == null) {
                if (high != 0) high = addOne(l2, 1);
                pre.next = l2;
                pre = pre.next;
                break;
            }
            if (l2 == null) {
                if (high != 0) high = addOne(l1, 1);
                pre.next = l1;
                pre = pre.next;
                break;
            }

            sum = l1.val + l2.val + high;
            high = sum / 10;

            ListNode tmp = new ListNode(sum % 10);
            pre.next = tmp;
            pre = tmp;
        }

        if (high != 0) {
            pre.next = new ListNode(1);
        }

        return res;
    }

    int addOne(ListNode node, int flag) {
        if (node == null) return flag;

        node.val += 1;
        if (node.val > 9) {
            node.val = 0;
            return addOne(node.next, 1);
        }
        return 0;
    }
}

