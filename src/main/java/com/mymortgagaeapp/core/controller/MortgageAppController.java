/**
 * 
 */
package com.mymortgagaeapp.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
