package com.ljstudio.dao

import com.ljstudio.domain.Role
import org.apache.onami.persist.EntityManagerProvider
import org.apache.onami.persist.Transactional

import javax.inject.Inject

class RoleDaoImpl implements RoleDao {

	private EntityManagerProvider em

	@Inject
	public UserDaoImpl(EntityManagerProvider em) {
		this.em = em
	}


	@Override
	@Transactional
	public void persistRole(Role role) {
		em.get().persist(role)
	}
}
