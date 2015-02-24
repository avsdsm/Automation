package org.obsplatform.test.common.chargecode;

import java.util.HashMap;

import org.joda.time.LocalDate;
import org.obsplatform.test.common.Utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ChargeCodeHelper {
	
	 private static final String SERVICE_URL = "/obsplatform/api/v1/chargecode";
	 private static final String TAX_URL = "/obsplatform/api/v1/taxmap";

	 public static Integer createChargeCode(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
		   Integer  resourceId=null;
		 
	        System.out.println("---------------------------------CREATING A CHARGECODES---------------------------------------------");
	        resourceId = Utils.performServerPost(requestSpec, responseSpec, SERVICE_URL+"?"+ Utils.TENANT_IDENTIFIER, getTestServiceAsJSON(),"resourceId");
	        
	        return resourceId;
	 }
	 
	 public static Integer createTax(final RequestSpecification requestSpec, final ResponseSpecification responseSpec, final String chargecode) {
		   Integer  resourceId=null;
		 
	        System.out.println("---------------------------------CREATING A TAX---------------------------------------------");
	        resourceId = Utils.performServerPost(requestSpec, responseSpec, TAX_URL+"/"+chargecode+"?"+ Utils.TENANT_IDENTIFIER, getTestTaxmapAsJSON(chargecode),"resourceId");
	        
	        return resourceId;
	 }
	 
	 @SuppressWarnings("serial")
	public static ChargeCodeData getChargeCode(final RequestSpecification requestSpec,final ResponseSpecification responseSpec, final Integer chargeId) {
	       
		   final String url = SERVICE_URL + "/" + chargeId + "?" + Utils.TENANT_IDENTIFIER;
		   System.out.println("------------------------ RETRIEVING CHARGECODE DATA -------------------------");
		   final String jsonData = new Gson().toJson(Utils.performServerGet(requestSpec, responseSpec, url, ""));
		   return new Gson().fromJson(jsonData, new TypeToken<ChargeCodeData>() {}.getType());
	 }
	 
	 

	  public static String getTestServiceAsJSON() {
		  
	        final HashMap<String, String> map = new HashMap<>();
	       
	        map.put("chargeCode", Utils.randomStringGenerator("CH",5));
	        map.put("chargeDescription", "ChargeCodes from testcases");
	        map.put("locale","en");
	        map.put("taxInclusive","false");
	        map.put("chargeDuration",Utils.randomNumberGenerator(1,100));
	        map.put("billFrequencyCode","Monthly");
	        map.put("durationType","Month(s)");
	        map.put("chargeType","RC");
	       
	        System.out.println("map : " + map);
	        return new Gson().toJson(map);
	  }
	  
	  public static String getTestTaxmapAsJSON(String chargecode) {
		  
	        final HashMap<String, String> map = new HashMap<>();
	       
	        map.put("chargeCode", chargecode);
	        map.put("dateFormat", "dd MMMM yyyy");
	        map.put("startDate", Utils.convertDateToURLFormat(new LocalDate(), "dd MMMM yyyy"));
	        map.put("taxCode", Utils.randomStringGenerator("TA",5));
	        map.put("locale","en");
	        map.put("rate",Utils.randomNumberGenerator(1,100));
	        map.put("taxType","Flat");
	        map.put("taxRegion", "1");
	       
	        System.out.println("map : " + map);
	        return new Gson().toJson(map);
	  }

}
