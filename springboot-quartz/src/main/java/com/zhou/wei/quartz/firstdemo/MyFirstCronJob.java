package com.zhou.wei.quartz.firstdemo;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

/**
 * @author 飞翔的胖哥
 * @version 1.0.0
 * @description Job类，工作的具体实现，即-需要干的事
 * @since 2020/4/10 0010 23:51
 **/
@DisallowConcurrentExecution
@PersistJobDataAfterExecution
public class MyFirstCronJob implements Job {
    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //创建工作详情
        JobDetail jobDetail = context.getJobDetail();
        //获取工作的名称
        String name = jobDetail.getKey().getName();
        String group = jobDetail.getKey().getGroup() ;
        String data = jobDetail.getJobDataMap().getString("data");
        System.out.println("job执行，job名："+name+" group："+group+"data："+data +new Date());

    }

    public static void main(String[] args)throws Exception{
        //第一步:创建scheduler 调度器
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //第二步:定义一个Trigger,触发条件类
        TriggerBuilder triggerBuilder = TriggerBuilder.newTrigger();
                       //定义name 和 group
        triggerBuilder.withIdentity("trigger1","group1")
                       //立即开始
                      .startNow()
                       //每五秒执行一次，一直执行直到结束
                      .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ?"));
        Trigger trigger = triggerBuilder.build();
        //第三步:定义一个JobDetail
        JobDetail jobDetail = JobBuilder.newJob(MyFirstCronJob.class)
                .withIdentity("测试任务1", "test1")
                .usingJobData("data", "job_data_zhouwei")
                .build();
        //第四步:将任务实例和触发器加入调度器管理中
        scheduler.scheduleJob(jobDetail,trigger);
        //第五步:启动任务调度
        scheduler.start();
    }
}
