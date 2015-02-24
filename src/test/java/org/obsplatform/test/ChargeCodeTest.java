package org.obsplatform.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.obsplatform.test.common.Utils;
import org.obsplatform.test.common.chargecode.ChargeCodeData;
import org.obsplatform.test.common.chargecode.ChargeCodeHelper;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ChargeCodeTest {

	private ResponseSpecification responseSpec;
	private RequestSpecification requestSpec;

	@Before
	public void setup() {
		Utils.initializeRESTAssured();
		this.requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
		this.requestSpec.header("Authorization","Basic "+ Utils.loginIntoServerAndGetBase64EncodedAuthenticationKey());
		this.requestSpec.header("X-Obs-Platform-TenantId", "default");
		this.responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();
	}

	
	@Test
	public void testCreateChargeCode() {
		
		Integer chargecode = ChargeCodeHelper.createChargeCode(requestSpec,responseSpec);
		Assert.assertNotNull(chargecode);
		
		final ChargeCodeData response = ChargeCodeHelper.getChargeCode(requestSpec, responseSpec, chargecode);
		
		Integer taxMap = ChargeCodeHelper.createTax(requestSpec,responseSpec,response.getChargeCode());
		Assert.assertNotNull(taxMap);
		System.out.println("response:----"+ response.getChargeCode());
	}

	@After
	public void tearDown() {

		System.out.println("----------------Successfully executed--------------------");

	}

}
