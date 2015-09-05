package com.ljstudio.guice

import com.google.inject.Scopes
import com.google.inject.servlet.ServletModule
import com.ljstudio.dao.UserDao
import com.ljstudio.dao.UserDaoImpl
import com.ljstudio.scheduler.MyProcessor
import com.ljstudio.scheduler.MyProcessorImpl
import com.ljstudio.services.MessageCreator
import com.ljstudio.services.MessageCreatorImpl
import com.ljstudio.services.UserService
import com.ljstudio.services.UserServiceImpl
import org.apache.onami.persist.PersistenceFilter
import org.apache.shiro.guice.web.ShiroWebModule

public class AppServletModule extends ServletModule {
	
    
    @Override
    protected void configureServlets() {
    	filter("/*").through(PersistenceFilter.class);
    	
    	bind(UserDao.class).to(UserDaoImpl.class);
    	bind(UserService.class).to(UserServiceImpl.class);
    	bind(MessageCreator.class).to(MessageCreatorImpl.class);

        bind(MyProcessor.class).to(MyProcessorImpl.class).in(Scopes.SINGLETON);

        ShiroWebModule.bindGuiceFilter(binder());
    }

}

