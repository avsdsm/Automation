package org.obsplatform.test.common.regions;

import java.util.ArrayList;
import java.util.HashMap;

import org.obsplatform.test.common.Utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class RegionHelper {
	
	 private static final String REGIONS_URL = "/obsplatform/api/v1/regions";

	@SuppressWarnings({ "serial" })
	public static RegionData getRegionById(RequestSpecification requestSpec,ResponseSpecification responseSpec, Long regionId) {
		
		  final String GET_REGIONS_URL= REGIONS_URL + "/"+ regionId + "?" + Utils.TENANT_IDENTIFIER;
		  System.out.println("------------------------ RETRIEVING  REGION DATA -------------------------");
		  final String jsonData = new Gson().toJson(Utils.performServerGet(requestSpec, responseSpec, GET_REGIONS_URL, ""));
	      return new Gson().fromJson(jsonData, new TypeToken<RegionData>() {}.getType());
		
	}

	@SuppressWarnings("serial")
	public static ArrayList<RegionData> getAllRegions(RequestSpecification requestSpec,ResponseSpecification responseSpec) {
		
		    final String GET_ALL_REGIONS_URL = REGIONS_URL + "?" + Utils.TENANT_IDENTIFIER;
	        System.out.println("------------------------ RETRIEVING ALL REGIONS -------------------------");
	        final ArrayList<RegionData> response = Utils.performServerGet(requestSpec, responseSpec, GET_ALL_REGIONS_URL, "");
	        final String jsonData = new Gson().toJson(new ArrayList<RegionData>(response));//remove spaces, convert java object to JSON format and returned as JSON formatted string     
	        return new Gson().fromJson(jsonData,new TypeToken<ArrayList<RegionData>>(){}.getType());
	
	}

	public static Long createRegion(RequestSpecification requestSpec,ResponseSpecification responseSpec) {
		
		   System.out.println("---------------------------------CREATING A DISCOUNT---------------------------------------------");
		  // Utils.performServerGet
	       Long resourceId = Utils.performServerPost(requestSpec, responseSpec, REGIONS_URL+"?"+ Utils.TENANT_IDENTIFIER, getTestRegionAsJSON(),"resourceId");
		   return resourceId;
	
	}

	private static String getTestRegionAsJSON() {
		
		final HashMap<String, String> map = new HashMap<>();
        map.put("regionCode", Utils.randomStringGenerator("REG-",4));
        map.put("regionName",Utils.randomStringGenerator("REG", 3));
        map.put("countryId", "");
        System.out.println("map : " + map);	
        return new Gson().toJson(map);
	}



}
