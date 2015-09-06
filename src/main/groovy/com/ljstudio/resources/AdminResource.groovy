package com.ljstudio.resources

import com.ljstudio.domain.User
import com.ljstudio.services.UserService
import com.ljstudio.utils.RequestItem
import com.ljstudio.utils.RequestLogger

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

@Path("admin")
class AdminResource extends BaseResource{
	private final UserService userService
	
	@Inject
	public AdminResource(RequestItem requestItem,
						 RequestLogger requestLogger,UserService userService) {
		super ( requestItem, requestLogger );
		this.userService = userService
	}
	
	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> fetchAllUsers() {
		return userService.fetchAllUsers();
	}
}
