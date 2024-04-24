package com.demo;

import java.util.Date;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;


/**
 * @author Nanda
 *
 */
public class consumer2 {
    // URL of the JMS server
    //private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
	
	private static String url = "tcp://localhost:61616";

    // Name of the topic from which we will receive messages from = " testt"

    public static void main(String[] args) throws JMSException {
        // Getting JMS connection from the server

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        System.out.println("Started consumer 2!");
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        //Topic topic = session.createTopic("testt?consumer.retroactive=true");
        Topic topic = new ActiveMQTopic("testt");

        MessageConsumer consumer = session.createConsumer(topic);

        MessageListener listner = new MessageListener() {
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("2nd Consumer Received message" + new Date (System.currentTimeMillis()) + "::" 
                                + textMessage.getText().toUpperCase() + "'");
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };

        consumer.setMessageListener(listner);
        //connection.close();

    }
}    