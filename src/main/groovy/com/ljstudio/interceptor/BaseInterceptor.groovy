package com.ljstudio.interceptor;

import com.google.inject.Provider;
import com.ljstudio.utils.RequestItem;
import com.ljstudio.utils.RequestLogger;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

abstract class BaseInterceptor implements MethodInterceptor {
	private final Provider <RequestLogger> loggerProvider;
	private final Provider <RequestItem> requestItemProvider;
	
	public BaseInterceptor ( Provider < RequestItem > requestItemProvider,
							 Provider < RequestLogger > loggerProvider ) {
		this.requestItemProvider = requestItemProvider;
		this.loggerProvider = loggerProvider;		
	}
	
	@Override
	public Object invoke ( MethodInvocation invocation ) throws Throwable {
		RequestLogger logger = loggerProvider.get();
		RequestItem requestItem = requestItemProvider.get();		
		String methodName = invocation.getMethod().getDeclaringClass().getSimpleName() 
								+ "." + invocation.getMethod().getName();
		
		try {
			return invoke ( invocation, methodName, requestItem, logger );
		} catch ( Exception e ) {
			logger.error ( methodName, e );
			throw e;
		}
	
	}
	
	public abstract Object invoke ( MethodInvocation invocation, 
									String methodName,
									RequestItem requestItem, 
									RequestLogger logger ) throws Throwable;

}
