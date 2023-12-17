package org.example;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class Producer {
    public static void main(String[] args){
        try {
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");
            Connection cnct = connectionFactory.createConnection();
            cnct.start();
            // when use true he will stay a commit to start sending
            //if I use false he will keep sending withoout commit
            //1 when the client connect on the queue and get the msg he will return automatically
            Session session= cnct.createSession(true,Session.AUTO_ACKNOWLEDGE);
            Destination destination=session.createTopic("myTopic.topic");
            MessageProducer producer= session.createProducer(destination);
            TextMessage msg= session.createTextMessage("HELLO WORLD");
            producer.send(msg);
            //khaterni aamlt el commit taa el autoknowledge
            session.commit();
            cnct.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
