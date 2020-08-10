/**
 * 
 */
package com.mymortgagaeapp.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mymortgagaeapp.core.exception.InvalidMortgageAppVersionException;
import com.mymortgagaeapp.core.exception.ResourceNotFoundException;
import com.mymortgagaeapp.core.model.MortgageApplication;
import com.mymortgagaeapp.core.service.MortgageService;

/**
 * @author Swapnil Dangore
 *
 */
@RestController
@RequestMapping(path="/mortgageApplications")
public class MortgageAppController {

	@Autowired
	MortgageService service;
	
	@GetMapping
	public ResponseEntity<List<MortgageApplication>> getAllApplications() {
		return ResponseEntity.ok().body(service.getAllApplications());
	}
	
	@PostMapping
	public ResponseEntity<MortgageApplication> createApplcation(@RequestBody @Validated MortgageApplication app) {
		MortgageApplication createdapp = service.createApplication(app);
		if(createdapp==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.ok().body(createdapp);
	}
	
	@PutMapping("{mortgageid}")
	public ResponseEntity<MortgageApplication> updateApplcation(@PathVariable(value = "mortgageid") String mortgageid,@RequestBody @Validated MortgageApplication app) throws ResourceNotFoundException, InvalidMortgageAppVersionException {
		MortgageApplication existingApp = service.getApplication(mortgageid);
		if(existingApp==null) {
			throw new ResourceNotFoundException("Application not found for this id :: " + mortgageid);
		}
		int existingVersion = Integer.parseInt(existingApp.getVersion());
		int versionNow = Integer.parseInt(app.getVersion());
		
		if(existingVersion>versionNow) {
			throw new InvalidMortgageAppVersionException("Version should be same as existing");
		}
		MortgageApplication createdapp = service.updateApplication(app, mortgageid);
		if(createdapp==null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(createdapp);
	}
}
