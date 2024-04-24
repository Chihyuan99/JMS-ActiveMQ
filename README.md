# JMS-ActiveMQ

Simple JMS application that places messages on a queue in ActiveMQ and consumes messages from the queue immediately after.

### Environment Setup: 
1. Start ActiveMQ 6.1.1 broker
```
bin# activemq start
```
2. ActiveMQ Admin Console is available at http://localhost:8161/

## DEMO 1: P2P Example
1. Run Sender.java, observe DEMOQUEUE in the Admin Console, 7 messages are sent as seen in the console
<img src="images/1.png" >
<img src="images/2.png" width="70%" >

2. Run Receiver.java, observe 5 out of 7 messages are dequeued by the consumer
<img src="images/3.png" >
<img src="images/4.png" width="70%" >

## DEMO 2: P2P Example with thread
See ReceiverThreads.java for details

## DEMO 3: Pub-Sub Example
1. Run consumer.java and consumer2.java, observe 2 consumers are available for topic "testt"
<img src="images/5.png" >

2. Run producer.java, observer 1 message is enqueued and both consumers have received that message
<img src="images/6.png" >
<img src="images/7.png" width="70%" >
<img src="images/8.png" width="70%" >
<img src="images/9.png" width="70%" >
