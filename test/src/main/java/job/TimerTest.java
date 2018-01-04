package job;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Timer的设计核心是TaskList和TaskThread。Timer将收到的任务放到TaskList中并按最初的执行时间排序。
 * 优点：简单易用
 * 缺点：串行，即所有任务由同一个线程执行
 * */
public class TimerTest extends TimerTask {
    private String jobName = "";

    public static void main(String[] args) {
        Timer timer = new Timer();
        long delayTime1 = 1000 * 1;
        long periodTime1 = 1000 * 3;
        timer.schedule(new TimerTest("job1"), delayTime1, periodTime1);

        timer.schedule(new TimerTest("job2"), delayTime1, periodTime1);
    }

    public TimerTest(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("execute " + jobName + " --- " + System.currentTimeMillis());
    }
}
