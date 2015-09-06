package com.ljstudio.dao.impl

import com.google.inject.Inject
import com.ljstudio.dao.UserDao
import com.ljstudio.domain.User
import com.ljstudio.utils.RequestLogger
import org.apache.onami.persist.EntityManagerProvider
import org.apache.onami.persist.Transactional
import org.apache.shiro.authz.annotation.RequiresRoles

class UserDaoImpl extends GenericDaoImpl < User, Long > implements UserDao {
	@Inject
	UserDaoImpl(EntityManagerProvider emp, RequestLogger logger) {
		super(emp, User.class, logger)
	}

	@Override
	@Transactional
	public User fetchUserByUsername(String username) {

		List<User> result = em().createQuery("from User where username = :username")
				.setParameter('username', username).getResultList()

		return result?.size() > 0 ? result[0] : null
	}

	@Override
	@Transactional
	@RequiresRoles("ADMIN")
	public List<User> fetchAllUsers() {
		return em().createQuery("from User").getResultList();
	}

}
