/**
 * 
 */
package com.mymortgagaeapp.core.service.soap.impl;

import java.util.List;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import com.mymortgagaeapp.core.service.soap.impl.generated.CreateOrUpdateApplicationRequest;
import com.mymortgagaeapp.core.service.soap.impl.generated.CreateOrUpdateApplicationResponse;
import com.mymortgagaeapp.core.service.soap.impl.generated.GetAllApplicationsRequest;
import com.mymortgagaeapp.core.service.soap.impl.generated.GetAllApplicationsResponse;
import com.mymortgagaeapp.core.service.soap.impl.generated.GetApplicationRequest;
import com.mymortgagaeapp.core.service.soap.impl.generated.GetApplicationResponse;
import com.mymortgagaeapp.core.service.soap.impl.generated.MortgageApplication;

/**
 * @author Swapnil Dangore
 */
public class SoapBackendConnector extends WebServiceGatewaySupport{

	public List<MortgageApplication> callGetAllApplications(){
		GetAllApplicationsRequest request = new GetAllApplicationsRequest();
		GetAllApplicationsResponse response =  (GetAllApplicationsResponse) getWebServiceTemplate().marshalSendAndReceive(this.getDefaultUri(),request);
		return response.getMortgageApplication();
	}
	
	public MortgageApplication createOrUpdateApplication(MortgageApplication reqApp) {
		CreateOrUpdateApplicationRequest request = new CreateOrUpdateApplicationRequest();
		request.setMortgageApplication(reqApp);
		
		CreateOrUpdateApplicationResponse response = (CreateOrUpdateApplicationResponse) getWebServiceTemplate().marshalSendAndReceive(this.getDefaultUri(),request);
	
		return response.getMortgageApplication();
	}
	
	public MortgageApplication getApplication(String mortgageId) {
		GetApplicationRequest request = new GetApplicationRequest();
		request.setMortgageId(mortgageId);
		
		GetApplicationResponse response = (GetApplicationResponse) getWebServiceTemplate().marshalSendAndReceive(this.getDefaultUri(),request);
		
		return response.getMortgageApplication();
	}
}
