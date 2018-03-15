package threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {
    public static void main(String[] args) {
        IntGenerator generator = new EvenGenerator();
        EvenChecker.check(generator, 10);
    }
}

abstract class IntGenerator {
    private volatile boolean canceled = false;

    public abstract int next();

    public boolean isCanceled(){
        return canceled;
    }

    public void cancel(){
        canceled = true;
    }
}

class EvenGenerator extends IntGenerator {
    private volatile int currentVal = 0;
    private Object obj = new Object();
    private ReentrantLock lock = new ReentrantLock();

//    @Override
//    public int next() {
//        synchronized(obj){
//            currentVal++;
//            Thread.yield();
//            currentVal++;
//            return currentVal;
//        }
//    }

    @Override
    public int next() {
        lock.lock();
        try {
            currentVal++;
            Thread.yield();
            currentVal++;
            return currentVal;
        } finally {
            lock.unlock();  // unlock()应该放在 finally块中
        }
    }
}

class EvenChecker implements Runnable {
    private IntGenerator generator;

    public EvenChecker(IntGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void run() {
        while(!generator.isCanceled()){
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int val = generator.next();
            if(val % 2 != 0){
                System.out.println(val + " not even --- " + Thread.currentThread().getName() + "\t" + System.nanoTime());
                generator.cancel();
            } else {
                System.out.println(val + " passed --- " + Thread.currentThread().getName() + "\t" + System.nanoTime());
            }

        }
    }

    public static void check(IntGenerator generator, int threadCount){
        ExecutorService exc = Executors.newCachedThreadPool();
        for (int i = 0; i < threadCount; i++) {
            exc.execute(new EvenChecker(generator));
        }
        exc.shutdown();
    }
}
