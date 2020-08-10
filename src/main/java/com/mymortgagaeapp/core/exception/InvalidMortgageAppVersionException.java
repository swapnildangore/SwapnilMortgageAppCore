package com.mymortgagaeapp.core.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author Swapnil Dangore
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidMortgageAppVersionException extends Exception{

	private static final long serialVersionUID = 1L;

	public InvalidMortgageAppVersionException(String message){
    	super(message);
    }
}
