package com.ljstudio.guice

import com.google.inject.Scopes
import com.google.inject.matcher.Matchers
import com.google.inject.servlet.ServletModule
import com.ljstudio.dao.UserDao
import com.ljstudio.dao.impl.UserDaoImpl
import com.ljstudio.interceptor.AuthorizationRequired
import com.ljstudio.interceptor.AuthorizationRequiredInterceptor
import com.ljstudio.interceptor.BaseInterceptor
import com.ljstudio.interceptor.BuildResponse
import com.ljstudio.interceptor.BuildResponseInterceptor
import com.ljstudio.scheduler.MyProcessor
import com.ljstudio.scheduler.MyProcessorImpl
import com.ljstudio.services.MessageCreator
import com.ljstudio.services.impl.MessageCreatorImpl
import com.ljstudio.services.UserService
import com.ljstudio.services.impl.UserServiceImpl
import com.ljstudio.utils.RequestItem
import com.ljstudio.utils.RequestLogger
import org.apache.onami.persist.PersistenceFilter
import org.apache.shiro.guice.web.ShiroWebModule

import javax.servlet.http.HttpServletRequest

public class AppServletModule extends ServletModule {
	
    
    @Override
    protected void configureServlets() {
    	filter("/*").through(PersistenceFilter.class);
    	
    	bind(UserDao.class).to(UserDaoImpl.class);
    	bind(UserService.class).to(UserServiceImpl.class);
    	bind(MessageCreator.class).to(MessageCreatorImpl.class);

        bind(MyProcessor.class).to(MyProcessorImpl.class).in(Scopes.SINGLETON);

        //下面的拦截器没有执行,why?
        bindInterceptor ( Matchers.any(), Matchers.annotatedWith ( AuthorizationRequired.class ),
                new AuthorizationRequiredInterceptor (
                        getProvider ( RequestItem.class ),
                        getProvider ( RequestLogger.class ),
                        getProvider ( HttpServletRequest.class ),
                        getProvider ( UserDao.class ) ) );

        bindInterceptor ( Matchers.any(), Matchers.annotatedWith ( BuildResponse.class ),
                new BuildResponseInterceptor (
                        getProvider ( RequestItem.class ),
                        getProvider ( RequestLogger.class ) ) );

        ShiroWebModule.bindGuiceFilter(binder());
    }

}

