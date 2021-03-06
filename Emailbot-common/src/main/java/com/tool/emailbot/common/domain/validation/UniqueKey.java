// Copyright 2014 Tool Inc.

package com.tool.emailbot.common.domain.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import com.tool.emailbot.common.domain.validation.impl.UniqueKeyValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Validates that the given value is a unique value in the persistent storage.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
@Documented
@Constraint(validatedBy = {UniqueKeyValidator.class})
@Target(TYPE)
@Retention(RUNTIME)
public @interface UniqueKey {

    String message() default "{com.tool.emailbot.common.domain.validation.UniqueKey.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String[] columnNames();
}
