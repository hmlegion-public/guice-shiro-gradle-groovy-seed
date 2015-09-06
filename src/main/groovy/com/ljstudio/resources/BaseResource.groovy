package com.ljstudio.resources;

import com.google.inject.servlet.RequestScoped;
import com.ljstudio.utils.RequestItem;
import com.ljstudio.utils.RequestLogger;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RequestScoped
@Produces ( MediaType.APPLICATION_JSON )
public abstract class BaseResource {
	protected RequestItem requestItem;
	protected RequestLogger logger;

	public BaseResource(RequestItem requestItem,
						RequestLogger logger) {
		this.requestItem = requestItem;
		this.logger = logger;
	}
}
