package com.pahlsoft.messaging;

public class MessageController {
	public static void main(String[] args) {
		String myDestination = args[0];
		String myQueueName = args[1];
		String myMessage = args[2];
		ActiveMQMessage amq = new ActiveMQMessage();
		amq.sendMessage(myDestination,myQueueName,myMessage);
	}
}
