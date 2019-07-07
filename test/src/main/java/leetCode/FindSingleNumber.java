package leetCode;

/**
 * 给定一个整型数组，其中只有一个数字只出现一次，其余的出现偶数次，找出这个数字
 */
public class FindSingleNumber {
    public static void main(String[] args) {
        System.out.println(find(new int[]{1,1,2,3,3,4,4,4,4}));
    }

    /**
     * a ^ a = 0, b ^ 0 = b
     */
    static int find(int[] array){
        int res = 0;
        for (int i : array) {
            res ^= i;
        }
        return res;
    }
}
