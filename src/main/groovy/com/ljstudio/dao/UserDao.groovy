package com.ljstudio.dao

import com.ljstudio.domain.User

interface UserDao extends GenericDao < User, Long >{
	User fetchUserByUsername(String username)
	List<User> fetchAllUsers()
}
