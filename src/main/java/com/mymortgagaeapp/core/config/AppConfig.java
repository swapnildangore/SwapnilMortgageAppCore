package com.mymortgagaeapp.core.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.ws.transport.http.HttpComponentsMessageSender;
import org.springframework.ws.transport.http.HttpComponentsMessageSender.RemoveSoapHeadersInterceptor;

import com.mymortgagaeapp.core.service.MortgageService;
import com.mymortgagaeapp.core.service.rest.impl.MortgageServiceRestImpl;
import com.mymortgagaeapp.core.service.soap.impl.MortgageServiceSoapImpl;
import com.mymortgagaeapp.core.service.soap.impl.SoapBackendConnector;

/**
 * 
 * @author Swapnil Dangore
 *
 */
@Configuration
public class AppConfig {

	@Value("${MyMortgageApp.backend.serviceType}")
	String serviceType;
	
	@Value("${keystore.file}")
	String file;
	
	@Value("${keystore.pass}")
	String password;
	
	@Value("${MyMortgageApp.soap.backend.url}")
	String soapBackendUrl;
	
	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
	
	@Bean
	public MortgageService getMortgageService() {
		MortgageService service = null; 
		if("REST".equalsIgnoreCase(serviceType)) {
			service = new MortgageServiceRestImpl();
		}else {
			service = new MortgageServiceSoapImpl();
		}
		return service;
	}
	
	@Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("com.mymortgagaeapp.core.service.soap.impl.generated");
        return marshaller;
    }
 
    @Bean
    public SoapBackendConnector soapBackendConnector(Jaxb2Marshaller marshaller) {
    	SoapBackendConnector client = new SoapBackendConnector();
        client.setDefaultUri(soapBackendUrl);
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        HttpClient httpSecureClient = HttpClients.custom()
        		.setSSLSocketFactory(getSSLConnectionSocketFactory())
        		.addInterceptorFirst(new RemoveSoapHeadersInterceptor())
        		.build();
        
        client.setMessageSender(new HttpComponentsMessageSender(httpSecureClient));
        return client;
    }
    
   /*private HttpClient httpSecureClient() {
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
		return HttpClients.custom().setSSLSocketFactory(sslsf).addInterceptorFirst(new RemoveSoapHeadersInterceptor()).build();
    }*/
	
	@Bean
	public RestTemplate secureRestTemplate() {		
		HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(getSSLConnectionSocketFactory()).build();
		ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
		return new RestTemplate(requestFactory);
	}
	
	private SSLConnectionSocketFactory getSSLConnectionSocketFactory() {
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
		return new SSLConnectionSocketFactory(sslcontext, new String[] { "TLSv1.2" }, null,
				NoopHostnameVerifier.INSTANCE);
	}
}
