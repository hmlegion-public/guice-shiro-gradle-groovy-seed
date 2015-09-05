package com.ljstudio.resources

import com.ljstudio.domain.HelloWorldMessage
import com.ljstudio.services.MessageCreator

import javax.inject.Inject
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * Created by lijin on 15-9-5.
 */
@Path("helloworld")
class HelloWorld {
    private MessageCreator messageCreator;

    @Inject
    public HelloWorld(MessageCreator messageCreator) {
        this.messageCreator = messageCreator;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public HelloWorldMessage getHello() {
        return messageCreator.generateMessage();
    }
}
