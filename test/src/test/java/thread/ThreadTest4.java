package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 生产者消费者
 */
public class ThreadTest4 {
    public static void main(String[] args) {
        new Food();
    }
}

class Food {
    private volatile int count = 0;

    Producer producer = new Producer(this);

    Consumer consumer = new Consumer(this);

    ExecutorService exc = Executors.newCachedThreadPool();

    public Food() {
        exc.execute(producer);
        exc.execute(consumer);
    }

    public synchronized int getCount() {
        return count;
    }

    public synchronized void produce() {
        count++;
    }

    public synchronized void consume() {
        count--;
    }
}

class Producer implements Runnable {
    private Food food;

    public Producer(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                while (food.getCount() > 3) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (food) {
                food.produce();
                System.out.println("make food, " + food.getCount());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (food.consumer) {
                food.consumer.notifyAll();
            }
        }
    }
}

class Consumer implements Runnable {
    private Food food;

    public Consumer(Food food) {
        this.food = food;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            synchronized (this) {
                while (food.getCount() <= 0) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            synchronized (food) {
                food.consume();
                System.out.println("consume food, " + food.getCount());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (food.getCount() <= 1) {
                synchronized (food.producer) {
                    food.producer.notifyAll();
                }
            }
        }
    }
}