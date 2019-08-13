package algorithm.leetCode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Return the length of the shortest, non-empty, contiguous subarray of A with sum at least K.
 * If there is no non-empty subarray with sum at least K, return -1.
 */
public class ShortestSubarray {
    public static void main(String[] args) {
        ShortestSubarray s = new ShortestSubarray();
        int[] a = {27,20,79,87,-36,78,76,72,50,-26};
        int k = 453;

        System.out.println(s.shortestSubarray(a, k));

    }

    public int shortestSubarray(int[] a, int k) {
        if(a.length == 1 && a[0] < k) return -1;

        int[] sum = new int[a.length + 1];
        for (int i = 0; i < a.length; i++) {
            sum[i+1] = sum[i] + a[i];
        }

        int min = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque();
        for (int i = 0; i < sum.length; i++) {
            while(!deque.isEmpty() && sum[i] - sum[deque.getFirst()] >= k){
                min = Math.min(min, i - deque.pollFirst());
            }
            while(!deque.isEmpty() && sum[i] <= sum[deque.getLast()]){
                deque.pollLast();
            }
            deque.addLast(i);

        }

        return min == Integer.MAX_VALUE ? -1 : min;
    }
}
