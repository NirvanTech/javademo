/**
 * 
 */
package com.demo.core.concurrency;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * @author parupati
 *
 */
public class UpdateInteger implements Callable<Integer> {

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public Integer call() throws Exception {
		// TODO Auto-generated method stub
		return new Integer(new Random().nextInt(100));
	}

}
