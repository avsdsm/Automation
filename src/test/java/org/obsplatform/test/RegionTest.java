package org.obsplatform.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.obsplatform.test.common.Utils;
import org.obsplatform.test.common.regions.RegionData;
import org.obsplatform.test.common.regions.RegionHelper;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.builder.ResponseSpecBuilder;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.specification.RequestSpecification;
import com.jayway.restassured.specification.ResponseSpecification;

public class RegionTest {


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
	public void testRegionElements() {
		
        final Long regionId=Long.valueOf(1);
		RegionData regionDetail = RegionHelper.getRegionById(requestSpec,responseSpec,regionId);
		Assert.assertNotNull(regionDetail);
        List<RegionData>  regionsList = RegionHelper.getAllRegions(requestSpec, responseSpec);
		System.out.println(regionsList);
		

	}
	
	
	@Ignore
	@Test
	public void testCreateRegion() {

		Long region = RegionHelper.createRegion(requestSpec,responseSpec);
		Assert.assertNotNull(region);

	}


}
