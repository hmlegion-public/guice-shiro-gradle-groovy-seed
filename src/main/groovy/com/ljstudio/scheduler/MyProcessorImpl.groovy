package com.ljstudio.scheduler

import com.google.inject.Inject
import com.google.inject.Injector
import com.google.inject.Singleton
import org.quartz.Scheduler
import org.quartz.SchedulerFactory
import org.quartz.Trigger
import org.quartz.TriggerUtils

/**
 * Created by lijin on 15-9-5.
 */
//@Singleton // 配置无效，注释掉
class MyProcessorImpl implements MyProcessor{
    @Inject
    private Injector injector;
    @Inject
    private SchedulerFactory factory;

    private Scheduler scheduler;
    @Override
    void start() throws Exception {
        JobDetail1 jobDetail = new JobDetail1("调度1", Scheduler.DEFAULT_GROUP, Job1Impl.class);
        jobDetail.setInjector(injector);
        jobDetail.setGroup("asm");
        this.scheduler = factory.getScheduler();
        Trigger trigger = TriggerUtils.makeSecondlyTrigger("调度任务1", 3, -1);//三秒执行一次，无限次数
        this.scheduler.scheduleJob(jobDetail, trigger);
        this.scheduler.start();
    }

    @Override
    void shutdown() throws Exception {
        if (this.scheduler != null){
            this.scheduler.shutdown();
        }
    }
}
