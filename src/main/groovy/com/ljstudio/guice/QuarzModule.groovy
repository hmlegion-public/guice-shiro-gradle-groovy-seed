package com.ljstudio.guice

import com.google.inject.AbstractModule
import com.google.inject.Scopes
import org.quartz.SchedulerFactory
import org.quartz.impl.StdSchedulerFactory

/**
 * Created by lijin on 15-9-5.
 */
class QuarzModule extends AbstractModule{
    @Override
    protected void configure() {
        this.bind(SchedulerFactory.class).to(StdSchedulerFactory.class).in(Scopes.SINGLETON);
//        下面的bind无效,why？ 挪到了AppServletModule
//        this.bind(MyProcessor.class).to(MyProcessorImpl.class).in(Scopes.SINGLETON);
    }
}
