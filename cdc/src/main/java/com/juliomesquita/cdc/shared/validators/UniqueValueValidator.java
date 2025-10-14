package com.juliomesquita.cdc.shared.validators;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.Assert;

import java.util.List;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object> {

    private String domainAttribute;
    private Class<?> klass;

    @PersistenceContext
    private EntityManager manager;

    @Override
    public void initialize(UniqueValue params) {
        domainAttribute = params.fieldName();
        klass = params.domainClass();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String jpql = "SELECT 1 FROM " + klass.getName() + " WHERE " + domainAttribute + " = :value";
        Query query = manager.createQuery(jpql);
        query.setParameter("value", value);

        List<?> list = query.getResultList();
        Assert.state(list.size() <= 1, "Mais de um " + klass.getName() + " com o atributo " + domainAttribute + " = " + value + " foi encontrado.");

        return list.isEmpty();
    }
}
