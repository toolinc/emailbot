package com.tool.emailbot.persistence.domain;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Test for the validations for the class {@code Persona}.
 *
 * @author Edgar Rico (edgar.martinez.rico@gmail.com)
 */
public class PersonaValidationTest {

    private ValidatorFactory factory;
    
    @Before
    public void before() {
        factory = Validation.buildDefaultValidatorFactory();
    }

    @After
    public void after() {
        factory.close();
    }

    @Test
    public void shouldFailedCauseNullable() {
        Validator validator = factory.getValidator();
        Persona persona = new Persona();
        Set<ConstraintViolation<Persona>> violations = validator.validate(persona);
        assertEquals(6, violations.size());
    }
}
