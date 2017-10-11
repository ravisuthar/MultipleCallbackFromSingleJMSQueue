package com.example;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import org.apache.cxf.phase.PhaseInterceptorChain;
import org.springframework.beans.factory.annotation.Autowired;

import com.mkyong.ws.ServerInfo;

public class ARMServicePortTypeImpl implements ServerInfo {

	@Resource
	WebServiceContext wsContext;

	@Context
	private HttpServletRequest request;

	@Autowired
	private JMSQueue jmsQueue;

	private HttpServletRequest getRequest() {
		MessageContext mc = wsContext.getMessageContext();
		HttpServletRequest req;
		if (mc != null) {
			req = (HttpServletRequest) mc.get(MessageContext.SERVLET_REQUEST);
		} else if (null != request) {
			req = request;
		} else {
			req = (HttpServletRequest) PhaseInterceptorChain.getCurrentMessage().get("HTTP.REQUEST");
		}
		return req;
	}

//	@Override
//	public LocateSiteResponseType locateSite(LocateSiteRequestType locateSiteRequest) {
//		// this.sendAsyncRequest(request.getRemoteHost(), String.valueOf(request.getRemotePort()));
//		HttpServletRequest request = this.getRequest();
//		this.sendAsyncRequest(request.getRemoteHost(), String.valueOf(request.getRemotePort()));
//		return new LocateSiteResponseType();
//	}

	public void sendAsyncRequest(String requestId, String flowType) {
		try {
			jmsQueue.convertAndSend(requestId, flowType);
		} catch (Exception e) {
			System.out.println("unable to sent message in queue.");
		}
	}

	@Override
	public String getIpAddress() {
		HttpServletRequest request = this.getRequest();
		this.sendAsyncRequest(request.getRemoteHost(), String.valueOf(request.getRemotePort()));
		String value= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Timestamp(System.currentTimeMillis()));
		System.out.println("value: "+value);
		return value;
	}
}
