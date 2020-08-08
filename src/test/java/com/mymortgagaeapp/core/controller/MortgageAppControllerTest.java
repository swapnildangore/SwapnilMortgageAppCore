/**
 * 
 */
package com.mymortgagaeapp.core.controller;

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

/**
 * @author Swapnil Dangore
 *
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyMortgageAppCoreApplication.class,webEnvironment=WebEnvironment.DEFINED_PORT)
public class MortgageAppControllerTest {

	@Autowired
	private TestRestTemplate restTemplate;

	String baseUrl;
	
	@Test
	public void testGetAllApplications() {		

		HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<String> response = restTemplate.exchange(baseUrl + "/mortgageApplications",
            HttpMethod.GET, entity, String.class);

        Assert.assertEquals(200,response.getStatusCode().value());
        Assert.assertNotNull(response.getBody());
	}	
	 
	@Before
	public void setBaseUrl() {
		this.baseUrl = "http://localhost:8080/MyMortgageApp";
	}
}
