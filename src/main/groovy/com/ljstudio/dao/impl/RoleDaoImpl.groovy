package com.ljstudio.dao.impl

import com.ljstudio.dao.RoleDao
import com.ljstudio.domain.Role
import com.ljstudio.utils.RequestLogger
import org.apache.onami.persist.EntityManagerProvider

import javax.inject.Inject

class RoleDaoImpl extends GenericDaoImpl < Role, Long > implements RoleDao {

	@Inject
	RoleDaoImpl(EntityManagerProvider emp, RequestLogger logger) {
		super(emp, Role.class, logger)
	}
}
