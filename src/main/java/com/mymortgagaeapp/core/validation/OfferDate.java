package com.mymortgagaeapp.core.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Constraint(validatedBy = OfferDateValidator.class)
public @interface OfferDate {

	String message() default "Offer date is less than 6 months";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}
