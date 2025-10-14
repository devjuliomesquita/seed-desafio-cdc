package com.juliomesquita.cdc.shared.repositories;

import com.juliomesquita.cdc.shared.utils.MapParam;
import com.juliomesquita.cdc.shared.utils.SearchQuery;
import com.juliomesquita.cdc.shared.utils.SearchQueryUtils;
import jakarta.persistence.criteria.Path;
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
            return Specification.where((Specification<T>) null);
        }

        Map<String, List<Map.Entry<String, Object>>> groupedByBaseKey = 
            mapParam.params().entrySet().stream()
                .collect(Collectors.groupingBy(entry -> entry.getKey().replaceAll("\\d+$", "")));

        Specification<T> overallSpec = Specification.where((Specification<T>) null);

        for (Map.Entry<String, List<Map.Entry<String, Object>>> group : groupedByBaseKey.entrySet()) {
            String baseKey = group.getKey();
            List<Map.Entry<String, Object>> params = group.getValue();

            Specification<T> groupSpec = null;
            for (Map.Entry<String, Object> param : params) {
                String operator = mapParam.operations().get(param.getKey());
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

    private static <T> Specification<T> createSpecification(String attribute, String operator, Object value) {
        return (root, query, cb) -> {
            try {
                Path<Object> path = root.get(attribute);
                Class<?> fieldType = path.getJavaType();
                Object typedValue = convertToFieldType(value, fieldType);

                if (typedValue == null) return cb.conjunction();

                return switch (operator) {
                    case ">" -> cb.greaterThan(path.as(Comparable.class), (Comparable) typedValue);
                    case ">=" -> cb.greaterThanOrEqualTo(path.as(Comparable.class), (Comparable) typedValue);
                    case "<" -> cb.lessThan(path.as(Comparable.class), (Comparable) typedValue);
                    case "<=" -> cb.lessThanOrEqualTo(path.as(Comparable.class), (Comparable) typedValue);
                    case "!=" -> cb.notEqual(path, typedValue);
                    case "=" -> cb.equal(path, typedValue);
                    case "cn" -> cb.like(cb.lower(path.as(String.class)), "%" + typedValue.toString().toLowerCase() + "%");
                    default -> cb.conjunction();
                };
            } catch (IllegalArgumentException e) {
                return cb.conjunction();
            }
        };
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
}