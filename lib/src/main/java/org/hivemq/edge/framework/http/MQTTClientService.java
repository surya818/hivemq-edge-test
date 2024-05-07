package org.hivemq.edge.framework.http;

import org.eclipse.paho.client.mqttv3.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hivemq.edge.framework.models.HttpAdapterMessage;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class MQTTClientService {
	private static MqttClient mqttClient;
	private static final Logger logger = LogManager.getLogger(MQTTClientService.class);
	private String brokerUrl;
	private String clientId;
	private String topic;
	public MQTTClientService() {
		this.brokerUrl = "tcp://localhost:1883";
		this.clientId = UUID.randomUUID().toString();
		this.topic = "test";
	}
	public MQTTClientService(String brokerUrl, String clientId, String topic) {
		
		this.brokerUrl = brokerUrl;
		this.clientId = clientId;
		this.topic = topic;
		
	}

	public static void init(String brokerUrl, String topic){
		String clientId = UUID.randomUUID().toString();
		List<HttpAdapterMessage> capturedMessages = new ArrayList<HttpAdapterMessage>();
		MqttClient client = null;
		try {
			client = new MqttClient("tcp://localhost:1883",clientId);

		} catch (MqttException e) {
			throw new RuntimeException(e);
		}
		mqttClient = client;
	}
	
	public static List<MqttMessage> SubscribeAndReceiveMessages(String topic, int waitTime) {

		String clientId = UUID.randomUUID().toString();
		List<MqttMessage> capturedMessages = new ArrayList<MqttMessage>();
        MqttClient client = null;
        try {
            client = new MqttClient("tcp://localhost:1883",clientId);

        } catch (MqttException e) {
            throw new RuntimeException(e);
        }
		mqttClient = client;



        mqttClient.setCallback(new MqttCallback() {
			/*
			On Message arrived we capture teh message into an array
			 */
			public void messageArrived(String topic, MqttMessage message) throws Exception {
                logger.info("topic: {}", topic);
				logger.info("qos: " + message.getQos());
				logger.info("message content: " + new String(message.getPayload()));
				capturedMessages.add(message);
			}

			public void connectionLost(Throwable cause) {
				logger.info("connectionLost: " + cause.getMessage());
			}


			public void deliveryComplete(IMqttDeliveryToken token) {
				logger.info("deliveryComplete: " + token.isComplete());
			}
		});
		try {
			CountDownLatch receivedSignal = new CountDownLatch(10);
			mqttClient.connect();
			mqttClient.subscribe(topic);
			receivedSignal.await(waitTime, TimeUnit.SECONDS);
		} catch (MqttException | InterruptedException e) {
			e.printStackTrace();
		}
        return capturedMessages;
    }
	

}
