package job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * Cron表达式：
 *      1    2    3    4     5    6    7
 *      秒   分   时   日期  月  星期   年（可选）
 *      星号(*)：可用在所有字段中，表示对应时间域的每一个时刻；
 *      问号（?）：该字符只在日期和星期字段中使用，它通常指定为“无意义的值”，相当于点位符；
 *          注：当我们配置日期和星期时，我们应该注意只能指定二者中的一个，另一个必须为？
 *      减号(-)：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12；
 *      逗号(,)：表达一个列表值，如在星期字段中使用“MON,WED,FRI”，则表示星期一，星期三和星期五；
 *      斜杠(/)：x/y表达一个等步长序列，x为起始值，y为增量步长值。如在分钟字段中使用0/15，则表示为0,15,30和45秒；
 *      L：该字符只在日期和星期字段中使用，代表“Last”的意思。L在日期字段中，表示这个月份的最后一天；如果L用在星期中，则表示星期六，等同于7。
 *          但是，如果L出现在星期字段里，而且在前面有一个数值X，则表示“这个月的最后X天”，例如，6L表示该月的最后星期五；
 *      W：该字符只能出现在日期字段里，是对前导日期的修饰，表示离该日期最近的工作日。
 */
public class QuartzTest implements Job {
    public static void main(String[] args) {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();

            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("trigger1", "group")
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/7 * * ? * *"))
                    .startNow()
                    .build();

            JobDetail jobDetail = JobBuilder.newJob(QuartzTest.class)
                    .withIdentity("trigger1", "group")
                    .usingJobData("type", "quartz")
                    .build();
            jobDetail.getJobDataMap().put("value", 2);

            scheduler.scheduleJob(jobDetail, trigger);

            scheduler.start();

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        JobDataMap dataMap = context.getMergedJobDataMap();
        context.getJobDetail().getJobDataMap();
        System.out.println(dataMap.get("type") + "-" + dataMap.get("value") + "-" + new Date());
    }
}
