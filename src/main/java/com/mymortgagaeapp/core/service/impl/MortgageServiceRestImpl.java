/**
 * 
 */
package com.mymortgagaeapp.core.service.impl;

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

	@Value("${MyMortgageApp.backend.url}")
	String baseUrl;

	@Value("${keystore.file}")
	String file;
	@Value("${keystore.pass}")
	String password;

	@Override
	public List<MortgageApplication> getAllApplications() {
		
		RestTemplate restTemplate = getSecureRestTemplate();
		ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/mortgageApplications", List.class);
		
		return (List<MortgageApplication>) response.getBody();
	}

	private RestTemplate getSecureRestTemplate() {
		KeyStore trustStore = null;
		SSLContext sslcontext = null;
		try (FileInputStream instream = new FileInputStream(ResourceUtils.getFile(file))) {
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());

			trustStore.load(instream, password.toCharArray());

			sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
		} catch (CertificateException | KeyManagementException | NoSuchAlgorithmException | KeyStoreException
				| IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1.2" }, null,
				NoopHostnameVerifier.INSTANCE);
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(requestFactory);
	}

	@Override
	public MortgageApplication createApplication(MortgageApplication app) {
		RestTemplate restTemplate = getSecureRestTemplate();
		ResponseEntity<MortgageApplication> response = restTemplate.postForEntity(baseUrl + "/mortgageApplications", app, MortgageApplication.class);
		return response.getBody();
	}

	@Override
	public MortgageApplication updateApplication(MortgageApplication app,String id) {
		
		RestTemplate restTemplate = getSecureRestTemplate();
		// build the request
	    HttpEntity<MortgageApplication> entity = new HttpEntity<>(app);

	    // send PUT request to update post with `id` 10
	    ResponseEntity<MortgageApplication> response = restTemplate.exchange(baseUrl + "/mortgageApplications/"+id, HttpMethod.PUT, entity, MortgageApplication.class);
	    
	    return response.getBody();
	}

	@Override
	public MortgageApplication getApplication(String id) {
		/*
		 * Map<String,String> params = new HashMap<>(); params.put("mortgageid", id);
		 */
		RestTemplate restTemplate = getSecureRestTemplate();
		ResponseEntity<MortgageApplication> response = restTemplate.getForEntity(baseUrl + "/mortgageApplications/"+id, MortgageApplication.class);
		
		return response.getBody();
	}
}
