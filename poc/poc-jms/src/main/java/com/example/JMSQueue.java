package com.example;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.cmtna.arm.microflows.ArmMicroFlowRequest;
import com.cmtna.arm.microflows.ArmMicroFlowRequestType;
import com.cmtna.arm.microflows.ObjectFactory;

@Service("jmsQueue")
public class JMSQueue {

	
	/** The jms template. */
	@Autowired
	private JmsTemplate jmsTemplate;
	
	/** The convertor. */
	@Autowired
	private MessageConverter convertor;

	/**
	 * Convert and send.
	 *
	 * @param requestId the request id
	 * @param flowType the flow type
	 */
	public void convertAndSend(String requestId, String flowType){
		final ArmMicroFlowRequest armMicroFlowRequest = create(requestId, flowType);
		
		jmsTemplate.send(new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				Message message = convertor.toMessage(armMicroFlowRequest, session);
				if(null != armMicroFlowRequest && null != armMicroFlowRequest.getRequestBody()){
				}
				return message;
			}
		});
		
		System.out.println("message sent...");
	}
	
	/**
	 * Creates the.
	 *
	 * @param requestId the request id
	 * @param flowType the flow type
	 * @return the arm micro flow request
	 */
	private ArmMicroFlowRequest create(String hostName, String port) {
		ObjectFactory objectFactory = new ObjectFactory();
		ArmMicroFlowRequest armMicroFlowRequest = objectFactory.createArmMicroFlowRequest();
		ArmMicroFlowRequestType requestBody = objectFactory.createArmMicroFlowRequestType();
		requestBody.setHostName(hostName);
		requestBody.setPort(port);
		armMicroFlowRequest.setRequestBody(requestBody);
		return armMicroFlowRequest;
	}
	
}
