// Copyright 2014 Tool Inc.

package com.tool.emailbot.common.domain.validation;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;

/**
 * Validates that a given telephone is valid. The validation uses the regular expression:
 * <b>^\d{2,20}$</b>.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Documented
@Pattern(regexp = "^\\d{5,20}$")
@ReportAsSingleViolation
@Constraint(validatedBy = {})
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER})
@Retention(RUNTIME)
public @interface Telephone {

    String message() default "{com.tool.emailbot.common.domain.validation.Telephone.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
