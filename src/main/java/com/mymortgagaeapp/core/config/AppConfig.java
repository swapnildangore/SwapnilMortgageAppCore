package com.mymortgagaeapp.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mymortgagaeapp.core.service.MortgageService;
import com.mymortgagaeapp.core.service.impl.MortgageServiceRestImpl;

/**
 * 
 * @author Swapnil Dangore
 *
 */
@Configuration
public class AppConfig {

	@Value("${MyMortgageApp.backend.serviceType}")
	String serviceType;
	
	@Bean
	public MortgageService getMortgageService() {
		MortgageService service = null; 
		if("REST".equalsIgnoreCase(serviceType)) {
			service = new MortgageServiceRestImpl();
		}
		return service;
	}
}
