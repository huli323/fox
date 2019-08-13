package algorithm.codeforces;

import java.util.Scanner;

/**
 Digits Sequence (Easy Edition)
    Let's write all the positive integer numbers one after another from 1 without any delimiters (i.e. as a single string).
 It will be the infinite sequence starting with 123456789101112131415161718192021222324252627282930313233343536...
 Your task is to print the k-th digit of this sequence.

 Input:
    The first and only line contains integer k (1≤k≤10000) — the position to process (1-based index).
 Output:
    Print the k-th digit of the resulting infinite sequence.

 Examples.
     input:
        21
     output:
        5
 */
public class DigitsSequence {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int index = scanner.nextInt();
        solution1(index);
        solution2(index);
    }

    static void solution1(int index){
        int i = 1;  // group number
        int len = 9;    // the max length of the last number
        int max = 9;    // the max number
        while(len < index){
            int tmp = 9 * (int) Math.pow(10, i);
            i++;
            len += i * tmp;
            max += tmp;
        }
        int diff = len - index; // the digit number between index and len
        int laterCount = diff / i;  // the number after index
        int remainder = diff % i;
        int current = max - laterCount; // the number of the index
        int k = i - 1 - remainder;
        System.out.println("len is " + len);
        System.out.println("remainder is " + remainder);
        System.out.println("current number is " + current);
        System.out.println(String.valueOf(current).charAt(k));
    }

    static void solution2(int index){
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < 2778; i++) {    // 10000 position number is 2777
            sb.append(i);
        }
        System.out.println("digit string is " + sb.toString());
        System.out.println(sb.charAt(index - 1));
    }
}
