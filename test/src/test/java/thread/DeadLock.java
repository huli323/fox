package thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadLock {
    public static void main(String[] args) {
        Chopstick[] chopsticks = new Chopstick[5];
        int size = 5;
        for (int i = 0; i < size; i++) {
            chopsticks[i] = new Chopstick(i + 1);
        }

        /*1.死锁版*/
        ExecutorService exc = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++) {
            exc.execute(new Philosopher(chopsticks[i], chopsticks[(i + 1) % size], i + 1));
        }

        /*2.非死锁版*/
//        ExecutorService exc = Executors.newCachedThreadPool();
//        for (int i = 0; i < size - 1; i++) {
//            exc.execute(new Philosopher(chopsticks[i], chopsticks[i+1], i + 1));
//        }
//        // 调换最后一位哲学家拿筷子的顺序 防止死锁
//        exc.execute(new Philosopher(chopsticks[0], chopsticks[size - 1], size));
    }
}

class Chopstick {
    private int id;
    private boolean taken = false;

    public Chopstick(int id) {
        this.id = id;
    }

    public synchronized void take() throws InterruptedException {
        while (taken) {
            wait();
        }
        taken = true;
        System.out.println(Thread.currentThread().getName() + " takes chopstick " + id);
    }

    public synchronized void drop() {
        taken = false;
        System.out.println(Thread.currentThread().getName() + " drops chopstick " + id);
        notifyAll();
    }
}

class Philosopher implements Runnable {
    private Random random = new Random();
    private Chopstick left;
    private Chopstick right;
    private int id;

    public Philosopher(Chopstick left, Chopstick right, int id) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Thread-" + id);
        while (!Thread.interrupted()) {
            try {
                System.out.println("philosopher " + id + " is thinking!");
//                 TimeUnit.SECONDS.sleep(random.nextInt(5));

                System.out.println("philosopher " + id + " is hungry!");
                left.take();
                right.take();

                System.out.println("philosopher " + id + " begin to eat!");
                TimeUnit.SECONDS.sleep(random.nextInt(5));

                left.drop();
                right.drop();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}