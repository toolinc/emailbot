// Copyright 2014 Tool Inc.

package com.tool.emailbot.persistence.validation.impl;

import com.tool.emailbot.persistence.Entidad;
import com.tool.emailbot.persistence.validation.UniqueKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Validates a unique constraint.
 *
 * @author Jovani Rico (jovanimtzrico@gmail.com)
 */
public class UniqueKeyValidator implements ConstraintValidator<UniqueKey, Entidad> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final EntityManager entityManager;
    private String[] columnNames;

    @Inject
    public UniqueKeyValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(final UniqueKey constraintAnnotation) {
        this.columnNames = constraintAnnotation.columnNames();
    }

    @Transactional(Transactional.TxType.REQUIRES_NEW)
    @Override
    public boolean isValid(Entidad target, ConstraintValidatorContext context) {
        final Class<?> entityClass = target.getClass();
        final CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        final CriteriaQuery<Object> criteriaQuery = criteriaBuilder.createQuery();
        final Root<?> root = criteriaQuery.from(entityClass);
        List<Predicate> predicates = new ArrayList<Predicate>(columnNames.length);
        try {
            for (int i = 0; i < columnNames.length; i++) {
                String propertyName = columnNames[i];
                PropertyDescriptor desc = new PropertyDescriptor(propertyName, entityClass);
                Method readMethod = desc.getReadMethod();
                Object propertyValue = readMethod.invoke(target);
                Predicate predicate = criteriaBuilder.equal(root.get(propertyName), propertyValue);
                predicates.add(predicate);
            }
            UUID id = target.getId();
            if (id != null) {
                Predicate idNotEqualsPredicate = criteriaBuilder.notEqual(root.get("id"), id);
                predicates.add(idNotEqualsPredicate);
            }
        } catch (IntrospectionException | InvocationTargetException | IllegalAccessException exc) {
            logger.warn("Unable to proceed with the validation.", exc);
            return false;
        }
        criteriaQuery.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        TypedQuery<Object> typedQuery = entityManager.createQuery(criteriaQuery);
        List<Object> resultSet = typedQuery.getResultList();
        if (!resultSet.isEmpty()) {
            return false;
        }
        return true;
    }
}
