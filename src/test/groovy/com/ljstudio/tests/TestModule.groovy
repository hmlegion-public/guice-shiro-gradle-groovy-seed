package com.ljstudio.tests

import com.google.inject.AbstractModule
import com.ljstudio.dao.RoleDao
import com.ljstudio.dao.UserDao
import com.ljstudio.dao.impl.RoleDaoImpl
import com.ljstudio.dao.impl.UserDaoImpl

class TestModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(UserDao).to(UserDaoImpl)
		bind(RoleDao).to(RoleDaoImpl)
	}

}
