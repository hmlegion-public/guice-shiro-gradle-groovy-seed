package com.ljstudio.services.impl

import com.ljstudio.domain.HelloWorldMessage
import com.ljstudio.services.MessageCreator
import groovyx.net.http.RESTClient

class MessageCreatorImpl implements MessageCreator {

	@Override
	public HelloWorldMessage generateMessage() {
		def randomChuckClient = new RESTClient( 'http://api.icndb.com' )
		def response = randomChuckClient.get(path: '/jokes/random')
		String message = response.data.value.joke
		return new HelloWorldMessage(message: message);
	}


}
