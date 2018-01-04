package job;

import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TimerTest2 extends TimerTask {
    private String jobName;

    private static final int secondOfMinute = 10;

    private static final int minuteOfHour = 29;

    public static void main(String[] args) {
        TimerTest2 test = new TimerTest2("job1");
        Calendar current = Calendar.getInstance();
        long currentMills = current.getTimeInMillis();
//        current = test.cal(current, secondOfMinute, minuteOfHour);
        current = test.cal(current, secondOfMinute);
        long delayTime = current.getTimeInMillis() - currentMills;
//        long periodTime = 1000 * 60 * 60;
        long periodTime = 1000 * 60;

        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(10);
        service.scheduleAtFixedRate(test, delayTime, periodTime, TimeUnit.MILLISECONDS);
    }

    public Calendar cal(Calendar current, int secondOfMinute, int minuteOfHour){
        int currentSecond = current.get(Calendar.SECOND);
        int currentMinute = current.get(Calendar.MINUTE);
        boolean flag = false;
        if(currentMinute > minuteOfHour){
            flag = true;
        } else if(currentMinute == minuteOfHour){
            if(currentSecond > secondOfMinute){
                flag = true;
            } else if(currentSecond == secondOfMinute){
                flag = false;
            }
        }

        if(flag){
            current.set(Calendar.HOUR, current.get(Calendar.HOUR) + 1);
        }

        current.set(Calendar.MINUTE, minuteOfHour);
        current.set(Calendar.SECOND, secondOfMinute);

        return current;
    }

    public Calendar cal(Calendar current, int secondOfMinute){
        int currentSecond = current.get(Calendar.SECOND);
        boolean flag = false;
        if(currentSecond > secondOfMinute){
            flag = true;
        } else if(currentSecond == secondOfMinute){
            flag = false;
        }

        if(flag){
            current.set(Calendar.MINUTE, current.get(Calendar.MINUTE) + 1);
        }

        current.set(Calendar.SECOND, secondOfMinute);

        return current;
    }

    @Override
    public void run() {
        System.out.println(jobName + " executed at " + new Date());
    }

    public TimerTest2(String jobName) {
        this.jobName = jobName;
    }
}
