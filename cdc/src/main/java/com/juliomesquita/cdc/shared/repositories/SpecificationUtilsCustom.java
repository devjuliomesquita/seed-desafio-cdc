package com.juliomesquita.cdc.shared.repositories;

import com.juliomesquita.cdc.shared.utils.Filter;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public final class SpecificationUtilsCustom {
    private SpecificationUtilsCustom() {
    }

    public static <T> Specification<T> build(final SearchQuery query) {
        //Devolver uma specification vazia se não houver filtros
        List<Filter> filters = query.filters();
        if (filters == null || filters.isEmpty()) {
            return emptySpecification();
        }

        //itear sobre os filtros e criar specifications individuais
        List<Specification<T>> specs = new ArrayList<>();
        for (Filter filter : filters) {
            specs.add(createSpecification(filter));
        }

        Specification<T> result = specs.getFirst();
        for (int i = 1; i < specs.size(); i++) {
            result = result.and(specs.get(i));
        }

        return result;
    }

    private static <T> Specification<T> createSpecification(final Filter filter) {
        return (root, query, cb) -> {
            try {
                final SearchOperation operator = SearchOperation.getSimpleOperation(filter.operator());
                if (operator == null) {
                    return cb.conjunction(); // Or throw an exception for invalid operator
                }

                Path<Object> path;
                if (StringUtils.hasText(filter.relation())) {
                    // Handle joins

                    Join<?, ?> join = root.getJoins().stream()
                        .filter(j -> j.getAttribute().getName().equals(filter.relation()))
                        .findFirst()
                        .orElseGet(() -> root.join(filter.relation(), JoinType.LEFT));
                    path = join.get(filter.field());
                } else {
                    path = root.get(filter.field());
                }

                Class<?> fieldType = path.getJavaType();
                Object typedValue = convertToFieldType(filter.value(), fieldType);

                if (typedValue == null && operator != SearchOperation.NULL && operator != SearchOperation.NOT_NULL) {
                    return cb.conjunction();
                }

                return switch (operator) {
                    case GREATER_THAN -> createGreaterThanSpec(path, typedValue, cb);
                    case GREATER_THAN_EQUAL -> createGreaterThanEqualSpec(path, typedValue, cb);
                    case LESS_THAN -> createLessThanSpec(path, typedValue, cb);
                    case LESS_THAN_EQUAL -> createLessThanEqualSpec(path, typedValue, cb);
                    case NOT_EQUAL -> createNotEqualSpec(path, typedValue, cb);
                    case EQUAL -> createEqualSpec(path, typedValue, cb);
                    case CONTAINS -> createContainsSpec(path, typedValue, cb);
                    case DOES_NOT_CONTAIN -> createDoesNotContainSpec(path, typedValue, cb);
                    case BEGINS_WITH -> createBeginsWithSpec(path, typedValue, cb);
                    case ENDS_WITH -> createEndsWithSpec(path, typedValue, cb);
                    case NULL -> createIsNullSpec(path, cb);
                    case NOT_NULL -> createIsNotNullSpec(path, cb);
                    default -> cb.conjunction();
                };
            } catch (IllegalArgumentException e) {
                // This can happen if the field or relation does not exist.
                return cb.conjunction();
            }
        };
    }

    private static Predicate createGreaterThanSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.greaterThan(path.as(Comparable.class), (Comparable) value);
    }

    private static Predicate createGreaterThanEqualSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
    }

    private static Predicate createLessThanSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.lessThan(path.as(Comparable.class), (Comparable) value);
    }

    private static Predicate createLessThanEqualSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) value);
    }

    private static Predicate createEqualSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.equal(path, value);
    }

    private static Predicate createNotEqualSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.notEqual(path, value);
    }

    private static Predicate createContainsSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
    }

    private static Predicate createDoesNotContainSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.notLike(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase() + "%");
    }

    private static Predicate createBeginsWithSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.like(cb.lower(path.as(String.class)), value.toString().toLowerCase() + "%");
    }

    private static Predicate createEndsWithSpec(Path<Object> path, Object value, CriteriaBuilder cb) {
        return cb.like(cb.lower(path.as(String.class)), "%" + value.toString().toLowerCase());
    }

    private static Predicate createIsNullSpec(Path<Object> path, CriteriaBuilder cb) {
        return cb.isNull(path);
    }

    private static Predicate createIsNotNullSpec(Path<Object> path, CriteriaBuilder cb) {
        return cb.isNotNull(path);
    }


    private static Object convertToFieldType(Object value, Class<?> fieldType) {
        if (value == null) return null;
        String stringValue = value.toString();
        try {
            if (fieldType.isAssignableFrom(BigDecimal.class)) {
                return new BigDecimal(stringValue);
            } else if (fieldType.isAssignableFrom(Long.class)) {
                return Long.parseLong(stringValue);
            } else if (fieldType.isAssignableFrom(Integer.class)) {
                return Integer.parseInt(stringValue);
            } else if (fieldType.isAssignableFrom(LocalDate.class)) {
                return LocalDate.parse(stringValue);
            } else if (fieldType.isEnum()) {
                return Enum.valueOf((Class<Enum>) fieldType, stringValue.toUpperCase());
            }
        } catch (Exception e) {
            return null;
        }
        return stringValue;
    }

    private static <T> Specification<T> emptySpecification() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();
    }
}