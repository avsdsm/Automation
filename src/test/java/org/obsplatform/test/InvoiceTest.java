package org.obsplatform.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.obsplatform.test.common.Utils;
import org.obsplatform.test.common.Invoice.InvoiceHelper;


import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class InvoiceTest {

private ResponseSpecification responseSpec;
private RequestSpecification requestSpec;

@Before
public void setUp() {
	Utils.initializeRESTAssured();
	this.requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).build();
	this.requestSpec.header("Authorization","Basic "+ Utils.loginIntoServerAndGetBase64EncodedAuthenticationKey());
	this.requestSpec.header("X-Obs-Platform-TenantId", "default");
	this.responseSpec = new ResponseSpecBuilder().expectStatusCode(200).build();	
	
}

@Ignore
@Test
public void testInvoice(){
	Integer invoiceId=InvoiceHelper.createInvoice(requestSpec, responseSpec);
	Assert.assertNotNull(invoiceId);
	
	
}


}
