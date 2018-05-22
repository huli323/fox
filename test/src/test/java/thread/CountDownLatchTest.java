package thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchTest {
    public static void main(String[] args) {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService exc = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            exc.execute(new Task(latch, i));
        }
        for (int i = 0; i < 5; i++) {
            exc.execute(new PreTask(latch, i));
        }
    }
}
class PreTask implements Runnable {
    private CountDownLatch latch;
    private Random random = new Random(47);
    private int id;

    public PreTask(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(random.nextInt(1000));
            latch.countDown();
            System.out.println("preTask-" + id + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Task implements Runnable {
    private CountDownLatch latch;
    private Random random = new Random(47);
    private int id;

    public Task(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            latch.await();
//            TimeUnit.MILLISECONDS.sleep(random.nextInt(500));
            System.out.println("Waiting-Task-" + id + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
