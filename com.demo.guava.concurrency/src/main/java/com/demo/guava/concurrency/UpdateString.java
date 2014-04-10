/**
 * 
 */
package com.demo.guava.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author parupati
 *
 */
public class UpdateString implements Callable<String> {

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public String call() throws Exception {
		return "String "+new Random().nextLong();
	}

}
