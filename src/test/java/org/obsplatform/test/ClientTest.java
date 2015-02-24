package org.obsplatform.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.obsplatform.test.common.ClientHelper;
import org.obsplatform.test.common.Utils;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ClientTest {
	
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
	public void createClient(){
		
		Integer clientId=ClientHelper.createClient(requestSpec, responseSpec);
		Assert.assertNotNull(clientId);
		ClientHelper.verifyClientCreatedOnServer(requestSpec, responseSpec, clientId);
		
	}

	
}
