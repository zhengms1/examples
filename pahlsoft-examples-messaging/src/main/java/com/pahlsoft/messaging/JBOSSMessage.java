package com.pahlsoft.messaging;

import javax.jms.*;

public class JBOSSMessage implements MessageListener,SendMessage {
    private static int ackMode;

    private boolean transacted = false;
    private MessageProducer producer;

    static {
        ackMode = Session.AUTO_ACKNOWLEDGE;
    }

    @SuppressWarnings("null")
	public void sendMessage(String factoryDestination, String clientQueueName, String myMessage) {
        ConnectionFactory connectionFactory = null ;
        Connection connection;
        try {
            connection = connectionFactory.createConnection();

            connection.start();
            Session session = connection.createSession(transacted, ackMode);
            Destination adminQueue = session.createQueue(clientQueueName);

            //Setup a message producer to send message to the queue the server is consuming from
            this.producer = session.createProducer(adminQueue);
            this.producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            //Now create the actual message you want to send
            TextMessage txtMessage = session.createTextMessage();
            txtMessage.setText(myMessage);
            txtMessage.setJMSPriority(1);


            this.producer.send(txtMessage);
            System.exit(0);
        } catch (JMSException e) {
        	e.printStackTrace();
        }
    }

    public void onMessage(Message message) {
        String messageText = null;
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                messageText = textMessage.getText();
                System.out.println("messageText = " + messageText);
                System.exit(0);
            }
        } catch (JMSException e) {
            //Handle the exception appropriately DEBUG:AJP We'll need a debug statement from global
        	e.printStackTrace();
        }
    }

}
