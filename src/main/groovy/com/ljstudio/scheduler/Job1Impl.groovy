package com.ljstudio.scheduler

import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException

/**
 * Created by lijin on 15-9-5.
 */
class Job1Impl implements Job1{
    @Override
    void execute(JobExecutionContext context) throws JobExecutionException {
        //获取injector（注意不能用注入的方法获取injector）
        JobDetail1 jobDetail = (JobDetail1)context.getJobDetail();
        if (jobDetail.getInjector() != null)
            System.out.println("可以获取到injector");
        System.out.println(context.getJobDetail().getName() + "执行中...");
    }
}
