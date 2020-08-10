/**
 * 
 */
package com.mymortgagaeapp.core.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

/**
 * @author Swapnil Dangore
 */
@Component
public class OfferDateValidator implements ConstraintValidator<OfferDate, String> {

	@Override
	public boolean isValid(String strOfferDate, ConstraintValidatorContext constraintValidatorContext) {
		LocalDate sixMonthsAfter = LocalDate.now().plusMonths(6);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// convert String to LocalDate
		LocalDate offerDate = LocalDate.parse(strOfferDate, formatter);
		
		return offerDate.isAfter(sixMonthsAfter);
	}
}
