package algorithm.other;

/**
 * 查找二叉搜索树中第k小的数
 */
public class KthSmallestOfBST {
    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node5.right = node6;
        node5.left = node3;
        node3.right = node4;
        node3.left = node2;
        node2.left = node1;

        Target solution = solution(node5, 6);
        System.out.println(solution.value);
    }

    static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    static class Target{
        public boolean find;
        public int value;
        public int count;

        public Target(boolean find, int count) {
            this.find = find;
            this.count = count;
        }

        public Target(boolean find, int count, int value) {
            this.find = find;
            this.value = value;
            this.count = count;
        }
    }

    static Target solution(Node node, int k){
        if(node == null){
            return new Target(false, 0);
        }

        Target left = solution(node.left, k);
        if(left.find)
            return new Target(true, left.count, left.value);

        // 节点总数为k-1，则父节点就是目标节点
        if(left.count == k - 1)
            return new Target(true, left.count + 1, node.value);

        Target right = solution(node.right, k - left.count - 1);
        if(right.find)
            return new Target(true, left.count + right.count, right.value);

        // 没找到则返回节点的总个数
        return new Target(false, left.count + right.count + 1);
    }
}
