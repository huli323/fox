package threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest2 {
    public static void main(String[] args) {
        ExecutorService exc = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            exc.execute(new SerialChecker());
        }
    }
}

class SerialGenerator {
    private static volatile int num = 0;

    public static int nextNum(){
        return ++num;
    }
}

class NumSet {
    private int[] array;
    private int len;
    private int index = 0;

    public NumSet(int len){
        this.len = len;
        array = new int[len];
        for (int i = 0; i < len; i++) {
            array[i] = 0;
        }
    }

    public synchronized void add(int num){
        array[index++] = num;
        index %= len;
    }

    public synchronized boolean contains(int num){
        for (int i = 0; i < len; i++) {
            if(array[i] == num)
                return true;
        }
        return false;
    }
}
class SerialChecker implements Runnable {
    NumSet set = new NumSet(100);

    @Override
    public void run() {
        while(true){
            int num = SerialGenerator.nextNum();
            if(set.contains(num)){
                System.out.println(num + " duplicated in " + Thread.currentThread().getName());
                System.exit(0);
            }
            set.add(num);
        }
    }
}
