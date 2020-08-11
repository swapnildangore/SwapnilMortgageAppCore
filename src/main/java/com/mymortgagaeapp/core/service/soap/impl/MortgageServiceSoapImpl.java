/**
 * 
 */
package com.mymortgagaeapp.core.service.soap.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.mymortgagaeapp.core.model.MortgageApplication;
import com.mymortgagaeapp.core.service.MortgageService;

/**
 * @author Swapnil Dangore
 */
public class MortgageServiceSoapImpl implements MortgageService {

	@Autowired
    private ModelMapper modelMapper;
	
	@Autowired
	private SoapBackendConnector soapBackendConnector;
	
	@Override
	public List<MortgageApplication> getAllApplications() {
		System.out.println("getAllApplications ********");
		List<MortgageApplication> appList = soapBackendConnector
				.callGetAllApplications()
				.stream()
				.map(this::convertToDto)
				.collect(Collectors.toList());
		System.out.println("getAllApplications appList********"+appList);
		return appList;
	}

	@Override
	public MortgageApplication getApplication(String id) {
		return convertToDto(soapBackendConnector.getApplication(id));
	}

	@Override
	public MortgageApplication createApplication(MortgageApplication app) {
		return convertToDto(soapBackendConnector.createOrUpdateApplication(convertToModel(app)));
	}

	@Override
	public MortgageApplication updateApplication(MortgageApplication app, String id) {
		return convertToDto(soapBackendConnector.createOrUpdateApplication(convertToModel(app)));
	}

	private MortgageApplication convertToDto(com.mymortgagaeapp.core.service.soap.impl.generated.MortgageApplication app) {
		return modelMapper.map(app,MortgageApplication.class);
    }
	
	private com.mymortgagaeapp.core.service.soap.impl.generated.MortgageApplication convertToModel(MortgageApplication app) {
		return modelMapper.map(app,com.mymortgagaeapp.core.service.soap.impl.generated.MortgageApplication.class);
    }
}
