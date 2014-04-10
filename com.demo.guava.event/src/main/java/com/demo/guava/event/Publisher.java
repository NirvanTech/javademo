/**
 * 
 */
package com.demo.guava.event;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.common.eventbus.EventBus;

/**
 * @author parupati
 *
 */
public class Publisher implements Runnable {
	@Autowired
	private EventBus channel;
	
	public EventBus getChannel() {
		return channel;
	}
	
	
	public void setChannel(EventBus channel) {
		this.channel = channel;
	}
	
	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("publishing "+i);
			channel.post("Meaage "+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	@PostConstruct
	public void init(){
		channel.register(this);
		Thread t = new Thread(this);
		t.start();
	}
	
	public Publisher() {
	}
	
	

	
}
