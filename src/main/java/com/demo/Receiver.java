package com.demo;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Message;

import org.apache.activemq.*;

public class Receiver {
	private ConnectionFactory factory = null;
	private Connection connection = null;
	private Session session = null;
	private Destination destination = null;
	private MessageConsumer consumer = null;

	public Receiver() {

	}

	public void receiveMessage() {
		try {
			for (int i = 1; i <= 5; i++) {
				factory = new ActiveMQConnectionFactory("tcp://localhost:61616");
				connection = factory.createConnection();
				connection.start();
				System.out.println("Hi!");
				session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
				destination = session.createQueue("DEMOQUEUE");

				consumer = session.createConsumer(destination);
				System.out.println("Connected to QUEUE"); // Thread  is blocked here
				Message message = consumer.receive();
				System.out.println("Waiting for the message!");

				if (message instanceof TextMessage) {
					TextMessage text = (TextMessage) message;
					System.out.println("Message is : " + text.getText());
				}
				session.close();
				connection.close();
			}

		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Receiver receiver = new Receiver();
		receiver.receiveMessage();
	}
}