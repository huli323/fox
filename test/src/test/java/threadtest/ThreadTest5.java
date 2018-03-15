package threadtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.*;

/**
 * 同步队列 BlockingQueue：
 *  如果消费者试图从空队列中获取对象，则队列会挂起消费者
 *  当队列中有对象之后，会唤起消费者
 */
public class ThreadTest5 {
    public static void main(String[] args) throws IOException, InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();
        BlockingQueue<LiftOff> queue = new LinkedBlockingQueue<LiftOff>();
        exc.execute(new LiftOffRunner(queue));

        while(true){
            String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
            if(str.equals(null) || str.trim().equals("")){
                continue;
            } else {
                queue.put(new LiftOff(str));
            }
        }
    }
}

/**
 *  消费者任务
 */
class LiftOffRunner implements Runnable {
    private BlockingQueue<LiftOff> queue;

    public LiftOffRunner(BlockingQueue<LiftOff> queue) {
        this.queue = queue;
    }

    public void put(LiftOff liftOff){
        try {
            queue.put(liftOff);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                LiftOff liftOff = queue.take();
                liftOff.run();
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class LiftOff implements Runnable {
    private String name;

    public LiftOff(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        Thread.currentThread().setName(name);
        System.out.println(name + " run");
    }
}
