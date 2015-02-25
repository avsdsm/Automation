package org.obsplatform.test.common.discount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.joda.time.LocalDate;
import org.obsplatform.test.common.Utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class DiscountHelper {
	
	 private static final String DISCOUNTS_URL = "/obsplatform/api/v1/discount";


	@SuppressWarnings("serial")
	public static  ArrayList<DiscountDomain> getAllDiscounts(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
		
		    final String GET_ALL_DISCOUNTS_URL = DISCOUNTS_URL + "?" + Utils.TENANT_IDENTIFIER;
	        System.out.println("------------------------ RETRIEVING ALL DISCOUNTS -------------------------");
	        final ArrayList<DiscountDomain> response = Utils.performServerGet(requestSpec, responseSpec, GET_ALL_DISCOUNTS_URL, "");
	        final String jsonData = new Gson().toJson(new ArrayList<DiscountDomain>(response));//remove spaces, convert java object to JSON format and returned as JSON formatted string   
	        return new Gson().fromJson(jsonData,new TypeToken<ArrayList<DiscountDomain>>(){}.getType());
	}
	
	@SuppressWarnings("serial")
	public static DiscountDomain getDiscountById(RequestSpecification requestSpec,ResponseSpecification responseSpec, final Long discountId) {
		
		  final String GET_DISCOUNTS_URL = DISCOUNTS_URL + "/"+ discountId + "?" + Utils.TENANT_IDENTIFIER;
		  System.out.println("------------------------ RETRIEVING  DISCOUNT -------------------------");
		  final String jsonData = new Gson().toJson(Utils.performServerGet(requestSpec, responseSpec, GET_DISCOUNTS_URL, ""));
	      return new Gson().fromJson(jsonData, new TypeToken<DiscountDomain>() {}.getType());
	}
	
	
	
	 public static Integer createDiscount(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
	        System.out.println("---------------------------------CREATING A DISCOUNT---------------------------------------------");
	       Integer resourceId = Utils.performServerPost(requestSpec, responseSpec, DISCOUNTS_URL+"?"+ Utils.TENANT_IDENTIFIER, getTestDiscountAsJSON(),"resourceId");
		   return resourceId;
	 }


	  public static String getTestDiscountAsJSON() {
		  
	        final HashMap<String, String> map = new HashMap<>();
	        map.put("discountCode", org.obsplatform.test.common.Utils.randomStringGenerator("DI",5));
	        map.put("discountRate",Utils.randomNumberGenerator(1,100));
	        map.put("discountDescription", "off");
	        map.put("discountType",Type.getRandom().toString());
	        map.put("dateFormat", "dd MMMM yyyy");
	        map.put("locale", "en");
	        map.put("startDate",Utils.convertDateToURLFormat(new LocalDate(), "dd MMMM yyyy"));
	        map.put("discountStatus","ACTIVE");
	        System.out.println("map : " + map);	
	        return new Gson().toJson(map);
	  }
	  
	  
	public enum Type {
		Percentage, Flat;

		public static Type getRandom() {
			final Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}

}
