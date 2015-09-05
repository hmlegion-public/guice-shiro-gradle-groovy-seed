package com.ljstudio.dao

import com.ljstudio.domain.User

interface UserDao {
	User fetchUserByUsername(String username)
	void persistUser(User user)
	List<User> fetchAllUsers()
}
