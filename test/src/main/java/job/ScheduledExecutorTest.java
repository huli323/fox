package job;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorTest implements Runnable {
    private String jobName = "";

    public static void main(String[] args) {
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);
        long delayTime = 1000 * 1;
        long periodTime = 1000 * 3;
        System.out.println(System.currentTimeMillis());
//        service.scheduleAtFixedRate(new ScheduledExecutorTest("job1"), delayTime, periodTime, TimeUnit.MILLISECONDS);
        service.scheduleWithFixedDelay(new ScheduledExecutorTest("job2"), delayTime, periodTime, TimeUnit.MILLISECONDS);
    }

    public ScheduledExecutorTest(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        long d = (long) (Math.random() * 1000);
        long start = System.currentTimeMillis() % 100000;
        System.out.println(jobName + "    " +start);

        try {
            Thread.sleep(d);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
