package com.ljstudio.interceptor;


import com.google.inject.Inject;
import com.google.inject.Provider;
import com.ljstudio.domain.BaseBean;
import com.ljstudio.utils.RequestItem;
import com.ljstudio.utils.RequestLogger;
import org.aopalliance.intercept.MethodInvocation;

public class BuildResponseInterceptor extends BaseInterceptor {
	
	@Inject
	public BuildResponseInterceptor ( Provider <RequestItem> requestItemProvider,
							 		  Provider <RequestLogger> loggerProvider ) {
		super ( requestItemProvider, loggerProvider);				
	}

	@Override	
	public Object invoke ( MethodInvocation invocation, 
						   String methodName,
						   RequestItem requestItem, 
						   RequestLogger logger ) throws Throwable {

		try {
			logger.info ( "@BuildResponse::" + methodName );			
			
			BaseBean methodReturn = ( BaseBean ) invocation.proceed();
			
			methodReturn.setRequestId ( requestItem.getRequestId() );			
			return methodReturn;			
		} finally {
			logger.logRequestComplete ();			
		}
	 }
}
