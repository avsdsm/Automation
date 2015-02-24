package org.obsplatform.test;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.obsplatform.test.common.Utils;
import org.obsplatform.test.common.services.ServiceDomain;
import org.obsplatform.test.common.services.ServiceHelper;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class ServiceTest {

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

	@Ignore
	@Test
	public void testDiscountElements() {

		ArrayList<ServiceDomain> serviceList = ServiceHelper.getAllServices(requestSpec, responseSpec);
		System.out.println(serviceList);
		
	}

	@Test
	public void testCreateDiscount() {

		Integer service = ServiceHelper.createService(requestSpec,responseSpec);
		Assert.assertNotNull(service);

	}

	@After
	public void tearDown() {

		System.out.println("----------------Successfully executed--------------------");

	}

}
