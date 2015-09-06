package com.ljstudio.resources

import com.ljstudio.interceptor.AuthorizationRequired
import com.ljstudio.interceptor.BuildResponse
import com.ljstudio.utils.RequestItem
import com.ljstudio.utils.RequestLogger

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path

/**
 * Created by lijin on 15-9-5.
 */
@Path("inter")
class InterceptorResource extends BaseResource{
    @Inject
    public InterceptorResource(RequestItem requestItem,
                      RequestLogger requestLogger) {
        super ( requestItem, requestLogger );
    }

    @GET
    @AuthorizationRequired
    @BuildResponse
    @Path("/testBuildResponse")
    public String testBuildResponse (  ) {
        return "BuildResponse";
    }

}
