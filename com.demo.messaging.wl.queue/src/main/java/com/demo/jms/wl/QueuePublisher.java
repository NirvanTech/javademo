/**
 * 
 */
package com.demo.jms.wl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Hashtable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import practice.streams.FileScanner;
import practice.streams.ScannerListner;


/**
 * @author parupati
 * 
 */
public class QueuePublisher implements ScannerListner {

	public final static String JNDI_FACTORY = "weblogic.jndi.WLInitialContextFactory";
	public final static String JMS_FACTORY = "weblogic/jms/XAConnectionFactory";
	public final static String QUEUE = "jms/CMS_Inbound";
	public final static String prurl = "t3://localhost:7001";

	private QueueConnectionFactory qconFactory;
	private QueueConnection qcon;
	private QueueSession qsession;
	private QueueSender qsender;
	private Queue queue;
	private TextMessage msg;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static InitialContext getInitialContext(String url)
			throws NamingException {
		Hashtable env = new Hashtable();
		env.put(Context.INITIAL_CONTEXT_FACTORY, JNDI_FACTORY);
		env.put(Context.PROVIDER_URL, url);
		return new InitialContext(env);
	}

	public void init(Context ctx, String queueName) throws NamingException,
			JMSException {
		qconFactory = (QueueConnectionFactory) ctx.lookup(JMS_FACTORY);
		qcon = qconFactory.createQueueConnection();
		qsession = qcon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup(queueName);
		qsender = qsession.createSender(queue);
		qcon.start();
	}
	public void send(String message) throws JMSException {
	    msg = qsession.createTextMessage(message);
	    msg.setJMSMessageID("Example1");
	    qsender.send(msg);
	 }
	 public void close() throws JMSException {
		    qsender.close();
		    qsession.close();
		    qcon.close();
		 }
	 
	public static void main(String[] args) {
		QueuePublisher publisher =  new QueuePublisher();
		try {
			publisher.init(getInitialContext(prurl), QUEUE);
			publisher.fileReadAndSend("F:\\test-cases\\10000Messages.txt", publisher);
			publisher.close();
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void fileReadAndSend(final String path, final ScannerListner listner){
				ExecutorService service = Executors.newFixedThreadPool(1);
			Future future =	service.submit(new Runnable() {
					
					public void run() {
						try {
							FileScanner.readFileChunks(path,listner);
						} catch (FileNotFoundException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}						
					}
				});
			while(true){
				if(future.isDone()){
					service.shutdown();
					break;
					
				}
				
					
			}
			
	}
	
	@SuppressWarnings("unused")
	private static void readAndSend(QueuePublisher qs)
		    throws IOException, JMSException
		 {
		    BufferedReader msgStream = new BufferedReader(new InputStreamReader(System.in));
		    String line=null;
		    boolean quitNow = false;
		    do {
		     System.out.print("Enter message (\"quit\" to quit): \n");
		     line = msgStream.readLine();
		     if (line != null && line.trim().length() != 0) {
		       qs.send(line);
		       System.out.println("JMS Message Sent: "+line+"\n");
		       quitNow = line.equalsIgnoreCase("quit");
		     }
		    } while (! quitNow);

		 }

	public void onLineEnd(String line)  {
		try {
			send(line);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
