package com.ljstudio.scheduler

import com.google.inject.Injector
import org.quartz.JobDetail

/**
 * Created by lijin on 15-9-5.
 */
class JobDetail1 extends JobDetail{
    private static final long serialVersionUID = -8514865085050079759L;
    private Injector injector;

    public JobDetail1(String name, String group, Class jobClass, boolean volatility, boolean durability, boolean recover) {
        super(name, group, jobClass, volatility, durability, recover);
    }

    public JobDetail1(String name, String group, Class jobClass) {
        super(name, group, jobClass);
    }

    public void setInjector(final Injector injector){
        this.injector = injector;
    }

    public Injector getInjector(){
        return this.injector;
    }
}
