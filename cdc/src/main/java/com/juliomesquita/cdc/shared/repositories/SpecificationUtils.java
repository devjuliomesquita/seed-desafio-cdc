package com.juliomesquita.cdc.shared.repositories;

import com.juliomesquita.cdc.shared.utils.MapParam;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import com.juliomesquita.cdc.shared.utils.SearchQueryUtils;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.PredicateSpecification;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class SpecificationUtils {

    public static <T> Specification<T> build(SearchQuery query) {
        MapParam mapParam = SearchQueryUtils.buildParams(query);
        if (mapParam.params().isEmpty()) {
            return emptySpecification();
        }

        Map<String, List<Map.Entry<String, Object>>> groupedByBaseKey = 
                mapParam.params().entrySet().stream()
                        .collect(Collectors.groupingBy(entry -> entry.getKey().replaceAll("\\d+$", "")));

        Specification<T> overallSpec = emptySpecification();

        for (Map.Entry<String, List<Map.Entry<String, Object>>> group : groupedByBaseKey.entrySet()) {
            String baseKey = group.getKey();
            List<Map.Entry<String, Object>> params = group.getValue();

            Specification<T> groupSpec = null;
            for (Map.Entry<String, Object> param : params) {
                SearchOperation operator = mapParam.operations().get(param.getKey());
                Specification<T> currentSpec = createSpecification(baseKey, operator, param.getValue());

                if (groupSpec == null) {
                    groupSpec = currentSpec;
                } else {
                    groupSpec = groupSpec.or(currentSpec);
                }
            }
            
            if (groupSpec != null) {
                overallSpec = overallSpec.and(groupSpec);
            }
        }

        return overallSpec;
    }

    private static <T> Specification<T> createSpecification(String attribute, SearchOperation operator, Object value) {
        return (root, query, cb) -> {
            try {
                Path<Object> path = root.get(attribute);
                Class<?> fieldType = path.getJavaType();
                Object typedValue = convertToFieldType(value, fieldType);

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
