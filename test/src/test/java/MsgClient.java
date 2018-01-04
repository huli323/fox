import sun.misc.Signal;
import sun.misc.SignalHandler;

import java.util.concurrent.*;

public class MsgClient {
    private static final ThreadPoolExecutor THREAD_POOL = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    private static volatile boolean isClose = false;

    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                close();
            }
        });

        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(100);
        producer(queue);
        consumer(queue);
    }

    private static void producer(final BlockingQueue queue) {
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> queue.offer(""), 0L, 200L, TimeUnit.MILLISECONDS);
    }

    private static long getPoolBacklogSize() {
        long backlog = THREAD_POOL.getTaskCount() - THREAD_POOL.getCompletedTaskCount();
        System.out.println(String.format("[%s]THREAD_POOL backlog:%s", System.currentTimeMillis(), backlog));
        return backlog;
    }

    private static void consumer(final BlockingQueue queue) throws InterruptedException {
        while (!isClose) {
            getPoolBacklogSize();
            final String msg = (String) queue.take();

            if (!THREAD_POOL.isShutdown()) {
                THREAD_POOL.execute(() -> {
                    try {
                        TimeUnit.MILLISECONDS.sleep(1000L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        }
    }

    private static void close() {
        System.out.println("received kill command, run close operation");
        isClose = true;
        THREAD_POOL.shutdown();
        while (!THREAD_POOL.isTerminated()) {
            getPoolBacklogSize();
            try {
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("subscriber closed, threads's process over");
        }
    }

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName != null && osName.indexOf("window") == -1){
            Signal sig = new Signal("USR2");
            Signal.handle(sig, new SignalHandler() {
                @Override
                public void handle(Signal signal) {
                    close();
                }
            });
        }
    }
}
