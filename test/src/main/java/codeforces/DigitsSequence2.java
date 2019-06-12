package codeforces;

import java.util.Scanner;

/**
  Hard Edition

    Let's write all the positive integer numbers one after another from 1 without any delimiters (i.e. as a single string).
 It will be the infinite sequence starting with 123456789101112131415161718192021222324252627282930313233343536...
 Your task is to print the k-th digit of this sequence.
 */
public class DigitsSequence2 {
    public static void main(String[] args) {
        while(true) {
            Scanner scanner = new Scanner(System.in);
            long index = scanner.nextLong();
            solution1(index);
        }
    }

    static void solution1(Long index){
        int i = 1;  // digit number of integer
        long len = 9;    // the max length
        long max = 9;    // the max integer
        while(len < index){
            long tmp = 9 * (long) Math.pow(10, i);
            i++;
            len += i * tmp;
            max += tmp;
        }
        long diff = len - index; // the digit number between index and len
        long laterCount = diff / i;  // the number after index
        int remainder = (int) (diff % i);
        long current = max - laterCount; // the number of the index
        int k = i - 1 - remainder;
        System.out.println("i is " + i);
        System.out.println("len is " + len);
        System.out.println("max is " + max);
        System.out.println("remainder is " + remainder);
        System.out.println("current number is " + current);
        System.out.println(String.valueOf(current).charAt(k));
    }
}
