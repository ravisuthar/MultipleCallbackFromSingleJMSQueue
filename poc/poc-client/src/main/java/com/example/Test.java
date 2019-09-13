package com.example;

import com.mkyong.ws.ServerInfo;
import com.mkyong.ws.ServerInfoService;

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
//			ARMService arm=new ARMService(new URL("http://host:7001/poc-war/cstService?wsdl"), new QName("http://com.dc.ccas.webservices.arminterfaceservice/", "ARMService"));
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
//			ARMService arm=new ARMService(new URL("http://host:7001/poc-war/cstService?wsdl"), new QName("http://com.cd.ca.webservices.arminterfaceservice/", "ARMServiceSoapHTTPPort"));
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
