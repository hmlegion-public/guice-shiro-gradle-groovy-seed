package com.ljstudio.guice

import com.google.inject.name.Names
import com.ljstudio.security.AppSecurityProvider
import org.apache.shiro.guice.web.ShiroWebModule

import javax.inject.Singleton
import javax.servlet.ServletContext

public class ShiroSecurityModule extends ShiroWebModule {

	public ShiroSecurityModule(ServletContext servletContext) {
		super(servletContext);
	}

	@Override
	protected void configureShiroWeb() {

		bindRealm().toProvider(AppSecurityProvider.class).in(Singleton.class);
		
		//Set Session Timeout to 30minutes 
		bindConstant().annotatedWith(Names.named("shiro.globalSessionTimeout")).to(1800000L);
		
		bindConstant().annotatedWith(Names.named("shiro.loginUrl")).to("/#/login");
		bindConstant().annotatedWith(Names.named("logout.redirectUrl")).to("/#/login");

		addFilterChain("/api/ka", ANON);
		addFilterChain("/api/login", ANON);
		addFilterChain("/api/schedule/**", ANON);

		addFilterChain("/api/logout", LOGOUT);
		
		addFilterChain("/api/helloworld", AUTHC_BASIC, config(ROLES, "ADMIN"));
		addFilterChain("/api/admin/**", AUTHC_BASIC, config(ROLES, "ADMIN"));

		addFilterChain("/**", AUTHC);

	}

}
