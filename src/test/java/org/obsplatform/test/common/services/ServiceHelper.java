package org.obsplatform.test.common.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.obsplatform.test.common.Utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ServiceHelper {
	
	 private static final String SERVICE_URL = "/obsplatform/api/v1/servicemasters";

	@SuppressWarnings({"serial", "rawtypes" })
	public static ArrayList<ServiceDomain> getAllServices(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
		
		   final String GET_ALL_SERVICES_URL = SERVICE_URL + "?" + Utils.TENANT_IDENTIFIER;
	        System.out.println("------------------------ RETRIEVING ALL SERVICE -------------------------");
	        final HashMap response = Utils.performServerGet(requestSpec, responseSpec, GET_ALL_SERVICES_URL, "");
	        System.out.println(response);
	        final String jsonData = new Gson().toJson(response.get("pageItems"));
	        return new Gson().fromJson(jsonData, new TypeToken<ArrayList<ServiceDomain>>() {}.getType());
	}
	
	
	 public static Integer createService(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
		   Integer  resourceId=null;
		 
	        System.out.println("---------------------------------CREATING A SERVICE---------------------------------------------");
	        resourceId = Utils.performServerPost(requestSpec, responseSpec, SERVICE_URL+"?"+ Utils.TENANT_IDENTIFIER, getTestServiceAsJSON(),"resourceId");
	        
	        return resourceId;
	 }


	public static String getTestServiceAsJSON() {

		final HashMap<String, String> map = new HashMap<>();

		map.put("serviceCode", Utils.randomStringGenerator("SE_", 5));
		map.put("serviceDescription", "Services from testcases");
		map.put("status", "ACTIVE");
		map.put("serviceType", serviceType.getRandom().toString());
		System.out.println("map : " + map);
		return new Gson().toJson(map);
	}

	public enum serviceType {
		IPTV, VOD, TV, BB, NONE;

		public static serviceType getRandom() {
			final Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

}
