package com.ljstudio.exception;

import com.google.inject.Singleton;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
@Singleton
public class QSExceptionMapper implements ExceptionMapper < QSException >
{
	@Override
	public Response toResponse ( final QSException exception )
	{
		return Response.status ( exception.getErrorCode().getHttpStatus() )
					   .entity ( new ExceptionBean ( exception ) )
					   .type ( MediaType.APPLICATION_JSON )
    		  		   .build();
	}
}

