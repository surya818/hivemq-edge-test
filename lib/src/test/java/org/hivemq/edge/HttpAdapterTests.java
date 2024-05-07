/*
 * This source file was generated by the Gradle 'init' task
 */
package org.hivemq.edge;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.hivemq.edge.framework.http.MQTTClientService;
import org.hivemq.edge.framework.models.*;
import org.hivemq.edge.framework.services.AdapterService;
import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

import java.util.Base64;
import java.util.List;


class HttpAdapterTests extends BaseTest {

	private static final Logger log = LogManager.getLogger(HttpAdapterTests.class);
	private static String adapterId;
	private static AdapterService adapterService = new AdapterService();
	private final String stoppedAdapterStatus = "STOPPED";
	private final String runningAdapterStatus = "STARTED";
	private final int subscribeTimeDuration = 5;

	@BeforeAll static void setUp(){
		adapterService = new AdapterService();
		AdapterService adapterService = new AdapterService();

	}

		/*
		This test creates an adapter verifies success state.
		Post the adapter creation, the test also verifies messages published
		 */

	  @Test void CreateHttpAdapter_HappyPath() {

		  adapterId = createAndVerifyAdapter();
		  MQTTClientService.init(brokerUrl,"test");
		  List<MqttMessage> httpAdapterMessages = MQTTClientService.SubscribeAndReceiveMessages(topic,subscribeTimeDuration);
		  assertTrue(httpAdapterMessages.size() > 3,  "Number of httpAdapterMessages received: "+httpAdapterMessages.size());
		  log.info("Number of httpAdapterMessages received: "+httpAdapterMessages.size());

	    }

		/*
		This test
		1) Creates an http adapter polling against the URL http://worldtimeapi.org/api/timezone/Europe/Stockholm
		2) Creates an MQTTClient and capture all the published messages
		3) And in each published message , extract payload,
		   Base64 decode it to get the embedded polling URL response
		   and verify the correct ness of that response


		 */
	@Test void HttpAdapter_MessageTranslation() {

		//Create Adapter and capture published messages
		adapterId = createAndVerifyAdapter();
		MQTTClientService.init(brokerUrl,"test");
		List<MqttMessage> messages = MQTTClientService.SubscribeAndReceiveMessages("test",subscribeTimeDuration);
		assertTrue(messages.size() > 3,  "Number of messages received: "+messages.size());
		log.info("Number of messages received: "+messages.size());

		//Translate mqtt payload, Base64 decode the message to json and serialize the json to pojo

		messages.forEach(item ->{
			String decodedMqttPayload = translateMqttMessage(item);
			Todo todoItem = jsonObject.fromJson(decodedMqttPayload, Todo.class);
			assertEquals(1, todoItem.getId() );
			assertFalse(todoItem.isCompleted());
		});

	}

	private String translateMqttMessage(MqttMessage message) {
		String mqttPayload = new String(message.getPayload());
		MqttMessagePayload messagePayload = jsonObject.fromJson(mqttPayload, MqttMessagePayload.class);
		String payloadValue = messagePayload.getValue();
		payloadValue = payloadValue.split(",")[1];

		//Base64 decode it to get the original http response from polling endpoint
        return new String(Base64.getDecoder().decode(payloadValue));
	}

	/*
    This test creates an adapter and stops it.
    Verifies that  1) Adapter reaches STOPPED state
                    2) Stopped Adapter does not send messages


*/
		@Test void StopAdapter_And_Verify_NoMessagesPublished(){

			adapterId = createAndVerifyAdapter();

			//Stop Service
			String desiredState = HttpAdapterAction.STOP.getValue();
			ModifyAdapterStateRequest request = new ModifyAdapterStateRequest();
			request.setCommand(desiredState);
			adapterService.ModifyAdapterState(adapterId, request);

			//Get the adapter and Verify Stopped state
			Adapter adapter = adapterService.GetActiveAdapter(adapterId);
			assertEquals(stoppedAdapterStatus, adapter.getStatus().getRuntime());

			//Verify No messages are sent by a stopped adapter
			List<MqttMessage> messages = MQTTClientService.SubscribeAndReceiveMessages(topic,subscribeTimeDuration);
            assertEquals(0, messages.size());


		}

	@Test void StopAndStartAdapter_Verify_MessagesArePublishedAgain(){
		adapterId = createAndVerifyAdapter();

		//Stop Service
		String desiredState = HttpAdapterAction.STOP.getValue();
		ModifyAdapterStateRequest request = new ModifyAdapterStateRequest();
		request.setCommand(desiredState);
		adapterService.ModifyAdapterState(adapterId, request);

		//Get the adapter and Verify Stopped state
		Adapter adapter = adapterService.GetActiveAdapter(adapterId);
		assertEquals(stoppedAdapterStatus, adapter.getStatus().getRuntime());

		//Verify No messages are sent by a stopped adapter
		List<MqttMessage> messages = MQTTClientService.SubscribeAndReceiveMessages(topic,subscribeTimeDuration);
		assertEquals(0, messages.size());


		//Start Service
		desiredState = HttpAdapterAction.START.getValue();
		request = new ModifyAdapterStateRequest();
		request.setCommand(desiredState);
		adapterService.ModifyAdapterState(adapterId, request);

		//Get the adapter and Verify Running state
		adapter = adapterService.GetActiveAdapter(adapterId);
		assertEquals(runningAdapterStatus, adapter.getStatus().getRuntime());

		//Verify message publishing is restarted
		messages = MQTTClientService.SubscribeAndReceiveMessages(topic,subscribeTimeDuration);
		assertTrue(messages.size() >=  3);

	}

	/**
	 * This test aims to connect to a invalid polling endpoint
	 * and verify the error state of Adapter
	 * The test also verifies stopping of messages published
	 */
	@Test void CreateHttpAdapter_InvalidUrl() {

		String adapterId = generateAdapterId();
		Adapter request = createHttpAdapterRequest(adapterId);
		AdapterConfig config = request.getConfig();
		//set an invalid url with no connection
		config.setUrl("http://localhost:9000");
		request.setConfig(config);

		//Create Http adapter and validate the response and the new adapter in list of service
		adapterService.setBearerToken(bearerToken);
		adapterService.CreateHttpAdapter(request);
		Adapter adapter;
		// Poll for Dead adapter status
		int counter = 0;
		while (true) {
			counter++;
			adapter = adapterService.GetActiveAdapter(adapterId);
			if (adapter.getStatus().getConnection().equals("ERROR") || adapter.getStatus().getRuntime().equals("STOPPED")) {
				break;
			}
			if (counter == 10) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
		//Verify the adapter Connection  moves to ERROR
		assertEquals(adapter.getStatus().getConnection(), "ERROR");
		MQTTClientService.init(brokerUrl, topic);
		List<MqttMessage> httpAdapterMessages = MQTTClientService.SubscribeAndReceiveMessages(topic, subscribeTimeDuration);
		//Verify no messages are published
		assertTrue(httpAdapterMessages.isEmpty(), "Number of httpAdapterMessages received: " + httpAdapterMessages.size());
		log.info("Number of httpAdapterMessages received: " + httpAdapterMessages.size());

	}


		private String createAndVerifyAdapter() {
		//Create Http adapter request

		String adapterId = generateAdapterId();
		Adapter request = createHttpAdapterRequest(adapterId);

		//Create Http adapter and validate the response and the new adapter in list of service
		adapterService.setBearerToken(bearerToken);
		adapterService.CreateHttpAdapter(request);
		Adapters adapters = adapterService.GetActiveAdapters();

		//Validate the adapter's state
			verifySuccessAdapterState(adapters, adapterId);
			return adapterId;
	}

	private void verifySuccessAdapterState(Adapters adapters, String adapterId) {
		Adapter adapter = adapters.getItems().stream().filter(item -> item.getId().equals(adapterId)).findFirst().get();
		assertEquals(adapter.getId(), adapterId);
		assertEquals("STARTED", adapter.getStatus().getRuntime());
		assertEquals("STATELESS", adapter.getStatus().getConnection());
	}

	private void verifyAdapterState(Adapter adapter, String status) {
		assertEquals(adapter.getId(), adapterId);
		assertEquals(status, adapter.getStatus().getRuntime());
		assertEquals("STATELESS", adapter.getStatus().getConnection());
	}

	@AfterEach
	public void cleanUp(){
		adapterService = new AdapterService();
		adapterService.setBearerToken(bearerToken);
		adapterService.GetActiveAdapters().getItems().forEach(adapter -> adapterService.DeleteActiveAdapter(adapter.getId()));

	}


}
