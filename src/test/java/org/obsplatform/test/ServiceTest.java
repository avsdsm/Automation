package org.obsplatform.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
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


	@Test
	public void testGetServiceElements() {

		ArrayList<ServiceDomain> serviceList = ServiceHelper.getAllServices(requestSpec, responseSpec);
		Assert.assertNotNull(serviceList);
		System.out.println(serviceList);
		
	}

	@Test
	public void testCreateService() {

		Integer service = ServiceHelper.createService(requestSpec,responseSpec);
		Assert.assertNotNull(service);

    }
}
