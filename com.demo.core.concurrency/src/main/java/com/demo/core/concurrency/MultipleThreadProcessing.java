/**
 * 
 */
package com.demo.core.concurrency;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
		ExecutorService service = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(10));
		List<ValueObject> list = new ArrayList<ValueObject>();
		List<Future> futures = new  ArrayList<Future>();
		for (int i = 0; i < 1000; i++) {
			list.add(new ValueObject());
		}
		long start = System.currentTimeMillis();
		for (ValueObject valueObject : list) {
			Future<String> strFuture = service.submit(new UpdateString());
//			Futures.addCallback(strFuture, new StringFutureCallback(valueObject));
			futures.add(strFuture);
			Future<Integer> intFuture = service.submit(new UpdateInteger());
//			Futures.addCallback(intFuture, new IntegerFutureCallback(valueObject));
			futures.add(intFuture);
			Future<Long> longFuture = service.submit(new UpdateLong());
//			Futures.addCallback(longFuture, new LongFutureCallback(valueObject));
			futures.add(longFuture);
		}
		
		while(!futures.isEmpty()){
			Iterator<Future> fIterator = futures.iterator();
			while (fIterator.hasNext()) {
				Future listenableFuture = (Future) fIterator.next();
				if(listenableFuture.isDone())fIterator.remove();
			}
		}
		
		System.out.println(list);
		System.out.println("Total time "+(System.currentTimeMillis()-start)+ " ms");
		service.shutdown();
		
	}

}
