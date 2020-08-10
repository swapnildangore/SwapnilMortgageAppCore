package com.mymortgagaeapp.core.service;

import java.util.List;

import com.mymortgagaeapp.core.model.MortgageApplication;

public interface MortgageService {

	List<MortgageApplication> getAllApplications();
	
	MortgageApplication getApplication(String id);
	
	MortgageApplication createApplication(MortgageApplication app);
	
	MortgageApplication updateApplication(MortgageApplication app,String id);
}
