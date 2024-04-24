package com.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ReceiverThreads implements Runnable {

	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;
	
	Thread t;
	String name;
	
	public ReceiverThreads(String name) {
		this.name = name;
		t = new Thread(this, name);
		System.out.println(t);
		t.start();
	}

	public void run() {

		try {

			factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
			connection = factory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			destination = session.createQueue("DEMOQUEUE");

			consumer = session.createConsumer(destination);
			Message message = consumer.receive();

			if (message instanceof TextMessage) {
				TextMessage text = (TextMessage) message;
				System.out.println(name + "===> Message is : " + text.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public static void main(String a[]) {
		for(int i=1;i<=5;i++) {
			new ReceiverThreads("Thread " + i+10);
		}
	}

}
