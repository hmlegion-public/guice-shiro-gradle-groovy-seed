package com.ljstudio.services

import com.ljstudio.dao.UserDao
import com.ljstudio.domain.User

import javax.inject.Inject
import javax.inject.Provider

class UserServiceImpl implements UserService {

	private Provider<UserDao> userDao

	@Inject
	public UserServiceImpl(Provider<UserDao> userDao) {
		this.userDao = userDao
	}

	@Override
	public User fetchUserByUsername(String username) {
		return userDao.get().fetchUserByUsername(username)
	}

	@Override
	public void persistUser(User user) {
		userDao.get().persistUser(user)
	}

	@Override
	public List<User> fetchAllUsers() {
		return userDao.get().fetchAllUsers()
	}
}
