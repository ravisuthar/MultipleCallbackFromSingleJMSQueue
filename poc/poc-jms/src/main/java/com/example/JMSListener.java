package com.example;

import java.net.URL;

import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Service;

import com.amdocs.oss.aff.contract.dynamicprocessmanager.DynamicProcessManager;
import com.amdocs.oss.aff.contract.dynamicprocessmanager.DynamicProcessManagerSvc;
import com.amdocs.oss.aff.schema.updateactivitystatus.ActivityStatusUpdate;
import com.amdocs.oss.aff.schema.updateactivitystatus.ObjectFactory;
import com.amdocs.oss.aff.schema.updateactivitystatus.UpdateActivityStatusRequest;
import com.cmtna.arm.microflows.ArmMicroFlowRequest;
import com.cmtna.arm.microflows.ArmMicroFlowRequestType;

@Service("jmsListener")
public class JMSListener implements SessionAwareMessageListener<Message> {

	@Autowired
	@Qualifier("oxmMessageConverter")
	private MessageConverter msgConverter;

	private static final String SOM_1_URL = "http://server:27400/aff/DynamicProcessManagerSvc?wsdl";
	private static final String SOM_2_URL = "http://server:21100/aff/DynamicProcessManagerSvc?wsdl";
	private static final String SOM_3_URL = "http://server:26100/aff/DynamicProcessManagerSvc?wsdl";

	@Override
	public void onMessage(Message msg, Session session) throws JMSException {
		Object requestMessage = null;
		if (msg instanceof TextMessage) {
			String text = ((TextMessage) msg).getText();
			requestMessage = msgConverter.fromMessage(msg);
		} else if (msg instanceof BytesMessage) {
			requestMessage = this.msgConverter.fromMessage(msg);
		}
		if (requestMessage instanceof ArmMicroFlowRequest) {
			ArmMicroFlowRequest inputRequest = (ArmMicroFlowRequest) requestMessage;
			ArmMicroFlowRequestType requestBody = inputRequest.getRequestBody();
			String hostName = requestBody.getHostName();
			String port = requestBody.getPort();

			
			if(SOM_1_URL.contains(hostName)){
				try {
					DynamicProcessManagerSvc svc1 = new DynamicProcessManagerSvc(new URL(SOM_1_URL));
					DynamicProcessManager som1 = svc1.getDynamicProcessManagerPort();
					som1.updateActivityStatus(this.build());
					System.out.println("callback to som1 : " + SOM_1_URL);
	
				} catch (Exception e) {
					System.out.println("exception occured...");
				}
			
			}else if(SOM_2_URL.contains(hostName)){

			try {
				DynamicProcessManagerSvc svc2 = new DynamicProcessManagerSvc(new URL(SOM_2_URL));
				DynamicProcessManager som2 = svc2.getDynamicProcessManagerPort();
				som2.updateActivityStatus(this.build());
				System.out.println("callback to som2: " + SOM_2_URL);

			} catch (Exception e) {
				System.out.println("exception occured...");
			}
			
			}else if(SOM_3_URL.contains(hostName)){
			
			try {
				DynamicProcessManagerSvc svc3 = new DynamicProcessManagerSvc(new URL(SOM_2_URL));
				DynamicProcessManager som3 = svc3.getDynamicProcessManagerPort();
				som3.updateActivityStatus(this.build());
				System.out.println("callback to som3: "+ SOM_3_URL);

			} catch (Exception e) {
				System.out.println("exception occured...");
			}
			
			}
			

		}
	}

	public UpdateActivityStatusRequest build() {
		ObjectFactory objectFactory = new ObjectFactory();
		UpdateActivityStatusRequest updateActivityStatusRequest = objectFactory.createUpdateActivityStatusRequest();
		ActivityStatusUpdate activityStatusUpdate = objectFactory.createActivityStatusUpdate();

		activityStatusUpdate.setPlanID("11");
		activityStatusUpdate.setActivityID("22");
		activityStatusUpdate.setStatus("Completed");
		updateActivityStatusRequest.setActivityStatusUpdate(activityStatusUpdate);
		return updateActivityStatusRequest;
	}

}
