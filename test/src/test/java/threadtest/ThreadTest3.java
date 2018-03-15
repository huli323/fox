package threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadTest3 {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();
        Car car = new Car();
        exc.execute(new BuffTask(car));
        exc.execute(new WaxTask(car));
        TimeUnit.SECONDS.sleep(3);
        exc.shutdownNow();
    }
}

class Car {
    private boolean waxed = false;

    /**
     * 打蜡
     */
    public synchronized void waxed() {
        waxed = true;
        System.out.println("打蜡完成");
        notifyAll();
    }

    /**
     * 等待抛光
     *
     * @throws InterruptedException
     */
    public synchronized void waitingForBuffing() throws InterruptedException {
        while (waxed) {
            wait();
        }
    }

    /**
     * 抛光（打蜡之后才能进行）
     */
    public synchronized void buffed() {
        waxed = false;
        System.out.println("抛光完成");
        notifyAll();
    }

    /**
     * 等待打蜡
     *
     * @throws InterruptedException
     */
    public synchronized void waitingForWaxing() throws InterruptedException {
        while (!waxed) {
            wait();
        }
    }

}

class WaxTask implements Runnable {
    private Car car;

    public WaxTask(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.SECONDS.sleep(1);
                car.waitingForBuffing();
                car.waxed();
            }
        } catch (InterruptedException e) {
            System.out.println("wax task ends");
        }
    }
}

class BuffTask implements Runnable {
    private Car car;

    public BuffTask(Car car) {
        this.car = car;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                car.waitingForWaxing();
                TimeUnit.SECONDS.sleep(1);
                car.buffed();
            }
        } catch (InterruptedException e) {
            System.out.println("buff task ends");
        }
    }
}