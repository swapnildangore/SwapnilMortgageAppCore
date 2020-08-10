/**
 * 
 */
package com.mymortgagaeapp.core.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mymortgagaeapp.core.MyMortgageAppCoreApplication;
import com.mymortgagaeapp.core.exception.ErrorDetails;
import com.mymortgagaeapp.core.model.MortgageApplication;

/**
 * @author Swapnil Dangore
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyMortgageAppCoreApplication.class, webEnvironment = WebEnvironment.DEFINED_PORT)
public class MortgageAppControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	String baseUrl;

	@Test
	public void testCreateApplication() {
		MortgageApplication app = new MortgageApplication();
		app.setMortgageId("M2");
		app.setVersion("1");
		app.setOfferId("OI-2");
		app.setProductId("B2");
		app.setCreatedDate("09/02/2020");
		app.setOfferDate("09/03/2021");
		app.setOfferExpired(false);

		ResponseEntity<MortgageApplication> response = restTemplate.postForEntity(baseUrl + "/mortgageApplications",
				app, MortgageApplication.class);
		Assert.assertEquals(200, response.getStatusCode().value());
	}
	
	@Test
	public void testUpdateApplication() {
		MortgageApplication app = new MortgageApplication();
		app.setMortgageId("M1");
		app.setVersion("2");
		app.setOfferId("OI-1");
		app.setProductId("B1");
		app.setCreatedDate("09/03/2020");
		app.setOfferDate("09/03/2021");
		app.setOfferExpired(false);
		HttpEntity<MortgageApplication> entity = new HttpEntity<>(app);
		ResponseEntity<MortgageApplication> response = restTemplate.exchange(baseUrl + "/mortgageApplications/M1",HttpMethod.PUT,
				entity, MortgageApplication.class);
		Assert.assertEquals(201, response.getStatusCode().value());
	}

	@Test
	public void testGetAllApplications() {

		ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/mortgageApplications", List.class);
		
		Assert.assertEquals(200, response.getStatusCode().value());
		Assert.assertNotNull(response.getBody());
	}
	
	@Test
	public void testIfOfferDateAfterSixMonths() {
		MortgageApplication app = new MortgageApplication();
		app.setMortgageId("M1");
		app.setVersion("2");
		app.setOfferId("OI-1");
		app.setProductId("B1");
		app.setCreatedDate("09/01/2021");
		app.setOfferDate("09/08/2020");
		app.setOfferExpired(false);
		HttpEntity<MortgageApplication> entity = new HttpEntity<>(app);
		ResponseEntity<MortgageApplication> response = restTemplate.exchange(baseUrl + "/mortgageApplications/M1",HttpMethod.PUT,
				entity, MortgageApplication.class);
		Assert.assertEquals(400, response.getStatusCode().value());
		
	}
	
	@Test
	public void testIfVersionIsNotSmaller() {
		MortgageApplication app = new MortgageApplication();
		app.setMortgageId("M1");
		app.setVersion("1");
		app.setOfferId("OI-1");
		app.setProductId("B1");
		app.setCreatedDate("09/01/2021");
		app.setOfferDate("09/08/2020");
		app.setOfferExpired(false);
		HttpEntity<MortgageApplication> entity = new HttpEntity<>(app);
		ResponseEntity<MortgageApplication> response = restTemplate.exchange(baseUrl + "/mortgageApplications/M1",HttpMethod.PUT,
				entity, MortgageApplication.class);
		Assert.assertEquals(500, response.getStatusCode().value());
		
	}
	
	@Before
	public void setBaseUrl() {
		this.baseUrl = "http://localhost:8080/MyMortgageApp";
	}
}
