package algorithm.other;

/**
 * 删除链表倒数第k个节点（k是有效的）
 * 方法1：递归
 * 方法2：双指针遍历，第一个指针从第k个开始，第二个从头开始
 */
public class DeleteTheLastKthOfLinkedList {
    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        int k = 2;
        ListNode res = cal(node1, k);
        System.out.println(res);
    }

    private static ListNode cal(ListNode node1, int k) {
        int count = handle(node1, k);
        if(count == k - 1){
            node1 = node1.next;
        }
        return node1;
    }

    private static class ListNode{
        public Integer value;
        public ListNode next;

        public ListNode(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + "->" + next;
        }
    }

    private static int handle(ListNode node, int k){
        if(node == null)
            return -1;
        int count = handle(node.next, k) + 1;
        if(k == count){
            node.next = node.next.next;
        }
        return count;
    }
}
