package org.obsplatform.test.common.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.obsplatform.test.common.Utils;

import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ServiceHelper {
	
	 private static final String SERVICE_URL = "/obsplatform/api/v1/servicemasters";

	@SuppressWarnings("unchecked")
	public static ArrayList<ServiceDomain> getAllServices(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
		
		   final String GET_ALL_DISCOUNTS_URL = SERVICE_URL + "?" + Utils.TENANT_IDENTIFIER;
	        System.out.println("------------------------ RETRIEVING ALL SERVICE -------------------------");
	        final List<ServiceDomain> response = Utils.performServerGet(requestSpec, responseSpec, GET_ALL_DISCOUNTS_URL, "");
	        final String jsonData = new Gson().toJson(new ArrayList<ServiceDomain>(response));//remove spaces, convert java object to JSON format and returned as JSON formatted string
	        return new Gson().fromJson(jsonData, new ArrayList<ServiceDomain>().getClass());
	}
	
	
	 public static Integer createService(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
		   Integer  resourceId=null;
		 
	        System.out.println("---------------------------------CREATING A SERVICE---------------------------------------------");
	        resourceId = Utils.performServerPost(requestSpec, responseSpec, SERVICE_URL+"?"+ Utils.TENANT_IDENTIFIER, getTestServiceAsJSON(),"resourceId");
	        
	        return resourceId;
	 }


	  public static String getTestServiceAsJSON() {
		  
	        final HashMap<String, String> map = new HashMap<>();
	       
	        map.put("serviceCode", Utils.randomStringGenerator("SE",5));
	        map.put("serviceDescription", "Services from testcases");
	        map.put("status","ACTIVE");
	        map.put("serviceType","IPTV");
	       
	        System.out.println("map : " + map);
	        return new Gson().toJson(map);
	  }

}
