package org.obsplatform.test.common;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Random;

import org.joda.time.LocalDate;

import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ClientHelper {

    private static final String CLIENT_URL = "/obsplatform/api/v1/clients";

    public static Integer createClient(final RequestSpecification requestSpec, final ResponseSpecification responseSpec) {
        System.out.println("---------------------------------CREATING A CLIENT---------------------------------------------");
        return Utils.performServerPost(requestSpec, responseSpec, CLIENT_URL+"?"+ Utils.TENANT_IDENTIFIER, getTestClientAsJSON(), "clientId");
    }

    public static String getTestClientAsJSON() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("officeId", "1");
        map.put("clientCategory",clientCategory.getRandomCategory().value);
        map.put("firstname", randomNameGenerator("Test_", 5));
        map.put("lastname", randomNameGenerator("_", 4));
        map.put("phone", Utils.randomPhoneNumberGenerator(10));
        map.put("email", "rakesh123@gmail.com");
        map.put("addressNo", "h-no:1/21-2015, jersey");
        map.put("city", "New Jersy123");
        map.put("state", "Auburn");
        map.put("country", "United States");
        //map.put("externalId", randomIDGenerator("ID_", 7));
        map.put("dateFormat", "dd MMMM yyyy");
        map.put("locale", "en");
        map.put("active", "true");
        map.put("activationDate", Utils.convertDateToURLFormat(new LocalDate(), "dd MMMM yyyy"));
        map.put("flag", Boolean.FALSE.toString());
        System.out.println("map : " + map);
        return new Gson().toJson(map);
    }

    public static void verifyClientCreatedOnServer(final RequestSpecification requestSpec, final ResponseSpecification responseSpec,final Integer generatedClientID) {
        System.out.println("------------------------------CHECK CLIENT DETAILS------------------------------------\n");
        String CLIENT_URL = ClientHelper.CLIENT_URL +"/"+ generatedClientID + "?tenantIdentifier=default";
        Integer responseClientID = Utils.performServerGet(requestSpec, responseSpec, CLIENT_URL, "id");
        assertEquals("ERROR IN CREATING THE CLIENT",generatedClientID, responseClientID);
    }

    public static String randomNameGenerator(final String prefix, final int lenOfRandomSuffix) {
        return Utils.randomStringGenerator(prefix, lenOfRandomSuffix);
    }

  /*  private static String randomIDGenerator(final String prefix, final int lenOfRandomSuffix) {
        return Utils.randomStringGenerator(prefix, lenOfRandomSuffix, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
    }
    */
    
    public enum clientCategory
    {
    	Normal("20"),
    	Staff("21"),
    	VIP("22");
    	private String value;
		private clientCategory(String value){
    	 this.value = value;
    	}
    	
    public static clientCategory getRandomCategory(){
    	Random random=new Random();
    	return values()[random.nextInt(values().length)];
    }
    	
    }
}