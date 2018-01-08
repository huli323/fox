package foxutil;

public class Prime {
    /**
     * 判断一个数是否是素数
     * .? 判断0和1
     * ? 非贪婪模式，尽可能少匹配，提高性能
     * */
    public static boolean isPrime(int num){
        return !new String(new byte[num]).matches(".?|(..+?)\\1+");
    }
}
