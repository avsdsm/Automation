package org.obsplatform.test.common.contractperiod;

import java.util.HashMap;
import java.util.Random;

import org.obsplatform.test.common.Utils;

import com.google.gson.Gson;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ContractPeriodHelper {

	private static final String CREATE_CONTRACT_PERIOD_URL = "/obsplatform/api/v1/subscriptions?tenantIdentifier=default";

	public static Integer createContractPeriod(final RequestSpecification requestSpec,final ResponseSpecification responseSpec) {
		Integer contract = null;
		System.out.println("---------------------------------CREATING A CONTRACT PERIOD---------------------------------------------");
		contract = Utils.performServerPost(requestSpec, responseSpec,CREATE_CONTRACT_PERIOD_URL, getContractPeriodBodyAsJSON(),"resourceId");
		return contract;

	}

	public static String getContractPeriodBodyAsJSON() {
		HashMap<String, String> map = new HashMap<String, String>();
			map.put("subscriptionType", subscriptionType.getRandom().toString()+"(s)");
			map.put("subscriptionPeriod", Utils.randomNameGenerator("Sub_", 3));
			map.put("units", Utils.randomNumberGenerator(0, 15));
			 System.out.println("map : " + map);
		return new Gson().toJson(map);
	}

	public enum subscriptionType {
		Month,Week, Day;

		public static subscriptionType getRandom() {
			final Random random = new Random();
			return values()[random.nextInt(values().length)];
		}
	}
}