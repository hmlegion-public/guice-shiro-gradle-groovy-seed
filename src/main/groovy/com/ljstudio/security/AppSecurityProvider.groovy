package com.ljstudio.security

import com.ljstudio.dao.UserDao

import javax.inject.Inject
import javax.inject.Provider

class AppSecurityProvider implements Provider<AppSecurityRealm> {

	private final Provider<UserDao> userDaoProvider

	@Inject
	public AppSecurityProvider(Provider<UserDao> userDaoProvider) {
		this.userDaoProvider = userDaoProvider
	}

	@Override
	public AppSecurityRealm get() {
		return new AppSecurityRealm(userDaoProvider);
	}
}
