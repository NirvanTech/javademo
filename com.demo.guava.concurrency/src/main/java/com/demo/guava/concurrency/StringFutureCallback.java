/**
 * 
 */
package com.demo.guava.concurrency;

import com.google.common.util.concurrent.FutureCallback;

/**
 * @author parupati
 *
 */
public class StringFutureCallback implements FutureCallback<String> {

	/* (non-Javadoc)
	 * @see com.google.common.util.concurrent.FutureCallback#onFailure(java.lang.Throwable)
	 */
	private ValueObject object;
	
	
	public StringFutureCallback(ValueObject object) {
		this.object = object;
	}

	public void onFailure(Throwable arg0) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.google.common.util.concurrent.FutureCallback#onSuccess(java.lang.Object)
	 */
	public void onSuccess(String arg0) {
		object.setString(arg0);
	}

}
