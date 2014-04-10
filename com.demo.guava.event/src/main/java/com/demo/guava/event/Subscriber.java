/**
 * 
 */
package com.demo.guava.event;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

/**
 * @author parupati
 *
 */
public class Subscriber {
	@Autowired
	private EventBus channel;

	public EventBus getChannel() {
		return channel;
	}

	public void setChannel(EventBus channel) {
		this.channel = channel;
	}
	
	@PostConstruct
	public void init(){
		channel.register(this);
	}
	
	@Subscribe
	public void recieveMessage(String message) {
			System.out.println(message);
	}
	
	

}
