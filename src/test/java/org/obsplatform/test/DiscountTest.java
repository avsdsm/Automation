package org.obsplatform.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.obsplatform.test.common.Utils;
import org.obsplatform.test.common.discount.DiscountDomain;
import org.obsplatform.test.common.discount.DiscountHelper;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class DiscountTest {

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
	public void testDiscountElements() {
		
		List<DiscountDomain> discountList = DiscountHelper.getAllDiscounts(requestSpec, responseSpec);
		Assert.assertNotNull(discountList);
		DiscountDomain discount = DiscountHelper.getDiscountById(requestSpec,responseSpec,discountList.get(0).getId());
		Assert.assertNotNull(discount);
	}
	
	@Ignore
	@Test
	public void testCreateDiscount() {

		Integer discount = DiscountHelper.createDiscount(requestSpec,responseSpec);
		Assert.assertNotNull(discount);
		DiscountDomain discountData = DiscountHelper.getDiscountById(requestSpec,responseSpec ,Long.valueOf(discount.longValue()));
		Assert.assertNotNull(discountData);
		Assert.assertTrue(discountData.getId().equals(Long.valueOf(discount.longValue())));

	}


}
