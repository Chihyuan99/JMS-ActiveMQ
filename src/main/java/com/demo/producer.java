package com.demo;

import javax.jms.*;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;


/**
 * @author Nanda
 *
 */
public class producer {

    //private static String url = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static String url = "tcp://localhost:61616";

    public static void main(String[] args) throws JMSException {

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        // JMS messages are sent and received using a Session. We will
        // create here a non-transactional session object. If you want
        // to use transactions you should set the first parameter to 'true'
        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Topic topic = new ActiveMQTopic("testt");

        MessageProducer producer = session.createProducer(topic);

        // We will send a small text message saying 'Hello'

        TextMessage message = session.createTextMessage();

        message.setText("Have a nice day!!" + System.currentTimeMillis());
        // Here we are sending the message!
        producer.send(message);
        System.out.println("Sent message '" + message.getText() + "'");

        connection.close();
    }
}