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
public class UpdateLong implements Callable<Long> {

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public Long call() throws Exception {
		// TODO Auto-generated method stub
		return new Long(new Random().nextLong());
	}

}
