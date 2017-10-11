package com.example;

import com.mkyong.ws.ServerInfo;
import com.mkyong.ws.ServerInfoService;

//import java.net.MalformedURLException;
//import java.net.URL;
//
//import javax.xml.namespace.QName;
//
//import arminterfaceservice.webservices.comcast.amdocs.com.ARMService;
//import arminterfaceservice.webservices.comcast.amdocs.com.ARMServicePortType;
//
//import com.amdocs.comcast.webservices.locatesite.LocateSiteRequestType;

public class Test {

	
	public static void main(String[] args) {
		
		
		ServerInfoService mk=new ServerInfoService();
		ServerInfo service = mk.getARMServicePortTypeImplPort();
		String value = service.getIpAddress();
		System.out.println("called arm service....."+ value);
		
	}
	
//	public static void main(String[] args) {
//		
//		try {
//			ARMService arm=new ARMService(new URL("http://10.19.9.92:7001/poc-war/comcastService?wsdl"), new QName("http://com.amdocs.comcast.webservices.arminterfaceservice/", "ARMService"));
//			ARMServicePortType service = arm.getARMServiceSoapHTTPPort();
//			LocateSiteRequestType locateSiteRequest = new LocateSiteRequestType();
//			locateSiteRequest.setSiteId("asfd");
//			locateSiteRequest.setSiteName("asdfbasdf");
//			service.locateSite(locateSiteRequest);
//			System.out.println("called arm service.....");
//		} catch (Exception e) {
//			System.out.println("unable to call client....");
//		}
//		
//		
//		try {
//			ARMService arm=new ARMService(new URL("http://10.19.9.92:7001/poc-war/comcastService?wsdl"), new QName("http://com.amdocs.comcast.webservices.arminterfaceservice/", "ARMServiceSoapHTTPPort"));
//			ARMServicePortType service = arm.getARMServiceSoapHTTPPort();
//			LocateSiteRequestType locateSiteRequest = new LocateSiteRequestType();
//			locateSiteRequest.setSiteId("asfd");
//			locateSiteRequest.setSiteName("asdfbasdf");
//			service.locateSite(locateSiteRequest);
//			System.out.println("called arm service.....");
//		} catch (Exception e) {
//			System.out.println("unable to call client....");
//		}
//		
//	}
}
