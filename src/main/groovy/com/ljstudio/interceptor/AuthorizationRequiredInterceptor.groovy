package com.ljstudio.interceptor;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.ljstudio.dao.UserDao;
import com.ljstudio.domain.User;
import com.ljstudio.exception.QSException;
import com.ljstudio.utils.Global;
import com.ljstudio.utils.RequestItem;
import com.ljstudio.utils.RequestLogger;
import org.aopalliance.intercept.MethodInvocation;

import javax.servlet.http.HttpServletRequest;

public class AuthorizationRequiredInterceptor extends BaseInterceptor {
	private final Provider < HttpServletRequest > servletRequestProvider;
	private final Provider <UserDao> userDaoProvider;

	@Inject
	public AuthorizationRequiredInterceptor ( Provider <RequestItem> requestItemProvider,
			 								  Provider <RequestLogger> loggerProvider,
			 								  Provider < HttpServletRequest > servletRequestProvider,
			 								  Provider < UserDao > userDaoProvider ) {	
		super ( requestItemProvider, loggerProvider);
		
		this.servletRequestProvider = servletRequestProvider;
		this.userDaoProvider = userDaoProvider;
	}
	
	@Override
	public Object invoke ( MethodInvocation invocation, 
						   String methodName,
						   RequestItem requestItem, 
						   RequestLogger logger ) throws Throwable {
		
		logger.info ( "@AuthorizationRequired::" + methodName );
		requestItem.setUser ( getUserFromRequest ( servletRequestProvider.get() ) );
		logger.info ( "[userId]::" + requestItem.getUser().getUserId() );
		
		return invocation.proceed();
	}
	
	private User getUserFromRequest ( HttpServletRequest request ) throws QSException {
		String userId = request.getParameter ( "userId" );

		if ( userId == null) {
			throw new QSException ( QSException.ErrorCode.UnAuthorized,
									Global.createInvalidParametersMessage (
											"userId") );
		}

		Long lUserId= Long.decode(userId);
		User user = userDaoProvider.get().find ( lUserId );

		if ( user == null) {
			throw new QSException ( QSException.ErrorCode.UnAuthorized,
									Global.createInvalidParametersMessage(
											"userId") );
		} 
		
		return user;
	}


}
