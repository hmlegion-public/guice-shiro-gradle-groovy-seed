package com.ljstudio.services

import com.ljstudio.domain.User

interface UserService {

	User fetchUserByUsername(String username)
	void persistUser(User user)
	List<User> fetchAllUsers()
}
