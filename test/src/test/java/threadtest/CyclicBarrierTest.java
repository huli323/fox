package threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierTest {
    public static void main(String[] args) {
        new Race(5);
    }
}

class Horse implements Runnable {
    private static int counter = 1;
    private int id = counter++;
    private static Random random = new Random(47);
    private int stride = 0;
    private CyclicBarrier barrier;

    public Horse(CyclicBarrier barrier) {
        this.barrier = barrier;
    }

    public int getStride(){
        return stride;
    }

    public String track(){
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < stride; i++) {
            builder.append("-");
        }
        builder.append(id);

        return builder.toString();
    }

    @Override
    public void run() {
        while(!Thread.interrupted()){
            synchronized (this) {
                stride += random.nextInt(5);
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Horse{" +
                "id=" + id +
                '}';
    }
}

class Race {
    static final int FINESH_LINE = 50;
    private List<Horse> horses = new ArrayList<>();
    private ExecutorService exc = Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public Race(int nhorses) {
        barrier = new CyclicBarrier(nhorses, () -> {
           StringBuilder builder = new StringBuilder();
           Horse winner = null;
           boolean flag = false;
            for (int i = 0; i < FINESH_LINE; i++) {
                builder.append("*");
            }
            System.out.println(builder.toString());
            for (Horse horse :
                    horses) {
                System.out.println(horse.track());
                if(horse.getStride() >= FINESH_LINE){
                    flag = true;
                    winner = horse;
                }
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(flag) {
                System.out.println(winner + " is the winner");
                exc.shutdownNow();
            }
        });
        for (int i = 0; i < nhorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exc.execute(horse);
        }
    }
}
