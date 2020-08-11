/**
 * 
 */
package com.mymortgagaeapp.core.service.rest.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;

import com.mymortgagaeapp.core.model.MortgageApplication;
import com.mymortgagaeapp.core.service.MortgageService;

/**
 * @author Swapnil Dangore
 *
 */
public class MortgageServiceRestImpl implements MortgageService {

	@Value("${MyMortgageApp.rest.backend.url}")
	String baseUrl;

	@Value("${keystore.file}")
	String file;
	@Value("${keystore.pass}")
	String password;

	@Autowired
	RestTemplate secureRestTemplate;
	
	@Override
	public List<MortgageApplication> getAllApplications() {
		
		ResponseEntity<List> response = secureRestTemplate.getForEntity(baseUrl + "/mortgageApplications", List.class);
		
		return (List<MortgageApplication>) response.getBody();
	}


	@Override
	public MortgageApplication createApplication(MortgageApplication app) {
		ResponseEntity<MortgageApplication> response = secureRestTemplate.postForEntity(baseUrl + "/mortgageApplications", app, MortgageApplication.class);
		return response.getBody();
	}

	@Override
	public MortgageApplication updateApplication(MortgageApplication app,String id) {
		
		// build the request
	    HttpEntity<MortgageApplication> entity = new HttpEntity<>(app);

	    // send PUT request to update post with `id` 10
	    ResponseEntity<MortgageApplication> response = secureRestTemplate.exchange(baseUrl + "/mortgageApplications/"+id, HttpMethod.PUT, entity, MortgageApplication.class);
	    
	    return response.getBody();
	}

	@Override
	public MortgageApplication getApplication(String id) {
		ResponseEntity<MortgageApplication> response = secureRestTemplate.getForEntity(baseUrl + "/mortgageApplications/"+id, MortgageApplication.class);
		
		return response.getBody();
	}
}
