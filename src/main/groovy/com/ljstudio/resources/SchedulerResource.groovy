package com.ljstudio.resources

import com.google.inject.Injector
import com.ljstudio.guice.AppGuiceServletContextListener
import com.ljstudio.scheduler.MyProcessor

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Created by lijin on 15-9-5.
 */
@Path("schedule")
class SchedulerResource {
    private MyProcessor myProcessor

    @Inject
    SchedulerResource(MyProcessor myProcessor) {
        this.myProcessor = myProcessor
    }

    @GET
    @Path("start")
    @Produces(MediaType.APPLICATION_JSON)
    public Response start() {
        myProcessor.start()
        return Response.ok("start ok").build()
    }

    @GET
    @Path("stop")
    @Produces(MediaType.APPLICATION_JSON)
    public Response stop() {
        myProcessor.shutdown()
        return Response.ok("stop ok").build()
    }

}
