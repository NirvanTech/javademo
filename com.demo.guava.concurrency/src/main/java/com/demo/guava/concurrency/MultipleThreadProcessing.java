/**
 * 
 */
package com.demo.guava.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * @author parupati
 *
 */
public class MultipleThreadProcessing {

	/**
	 * @param args
	 */
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ListeningExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		List<ValueObject> list = new ArrayList<ValueObject>();
		List<ListenableFuture> futures = new  ArrayList<ListenableFuture>();
		for (int i = 0; i < 1000; i++) {
			list.add(new ValueObject());
		}
		long start = System.currentTimeMillis();
		for (ValueObject valueObject : list) {
			ListenableFuture<String> strFuture = service.submit(new UpdateString());
			Futures.addCallback(strFuture, new StringFutureCallback(valueObject));
			futures.add(strFuture);
			ListenableFuture<Integer> intFuture = service.submit(new UpdateInteger());
			Futures.addCallback(intFuture, new IntegerFutureCallback(valueObject));
			futures.add(intFuture);
			ListenableFuture<Long> longFuture = service.submit(new UpdateLong());
			Futures.addCallback(longFuture, new LongFutureCallback(valueObject));
			futures.add(longFuture);
		}
		
		while(!futures.isEmpty()){
			Iterator<ListenableFuture> fIterator = futures.iterator();
			while (fIterator.hasNext()) {
				ListenableFuture listenableFuture = (ListenableFuture) fIterator
						.next();
				if(listenableFuture.isDone())fIterator.remove();
			}
		}
		
		System.out.println(list);
		System.out.println("Total time "+(System.currentTimeMillis()-start)+ " ms");
		service.shutdown();
		
	}

}
