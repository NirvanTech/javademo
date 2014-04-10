/**
 * 
 */
package com.demo.guava.event;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author parupati
 *
 */
public class EventBusExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("eventbus.xml");
	}

}
