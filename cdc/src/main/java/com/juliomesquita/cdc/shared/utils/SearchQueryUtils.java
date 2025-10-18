package com.juliomesquita.cdc.shared.utils;

import com.juliomesquita.cdc.shared.repositories.SearchOperation;
import org.springframework.data.domain.Sort;

import java.util.*;

public final class SearchQueryUtils {
    private SearchQueryUtils() {
    }

    public static Sort buildSort(final SearchQuery query) {
        if (!Objects.nonNull(query.sort())) {
            return Sort.by("id").ascending();
        }
        final Sort sort = Sort.by(query.sort());
        if (query.direction().equalsIgnoreCase("asc") || Objects.nonNull(query.direction())) {
            return sort.ascending();
        }
        return sort.descending();
    }

    public static MapParam buildParams(final SearchQuery query) {
        if (query.terms() == null || query.terms().isEmpty()) {
            return MapParam.create();
        }
        final Map<String, Object> params = new HashMap<>();
        final Map<String, SearchOperation> operations = new HashMap<>();

        final String[] filters = query.terms().trim().split(",");

        Arrays.stream(filters).forEach(filter -> {
            final String[] keyValue = filter.trim().split("=");
            if (keyValue.length == 1) {
                params.put(keyValue[0], "");
            } else {
                List<Param> paramList = splitValues(keyValue[0], keyValue[1]);
                paramList.forEach(param -> {
                    params.put(param.key(), param.value());
                    operations.put(param.key(), param.operator());
                });
            }
        });

        return new MapParam(params, operations);
    }

    private static List<Param> splitValues(final String key, final String value) {
        final String[] parts = value.split("!OR!");
        final var result = new ArrayList<Param>();

        for (int i = 0; i < parts.length; i++) {
            String val = parts[i];
            SearchOperation operator = SearchOperation.CONTAINS;

            if (val.contains("!")) {
                String[] valOp = val.split("!");
                val = valOp[0];
                SearchOperation parsedOperator = SearchOperation.getSimpleOperation(valOp[1].toLowerCase());
                if (parsedOperator != null) {
                    operator = parsedOperator;
                }
            }

            String paramKey = parts.length > 1 ? key + i : key;
            result.add(new Param(paramKey, val, operator));
        }

        return result;
    }

    public static StringBuilder buildQuery(final MapParam mapParam) {
        if (mapParam.params().isEmpty()) {
            return new StringBuilder();
        }

        final var entries = new ArrayList<>(mapParam.params().entrySet());

        final StringBuilder whereClause = new StringBuilder();
        for (int i = 0; i < entries.size(); i++) {
            final String key = entries.get(i).getKey();
            final String keyWithoutIndex = key.replaceAll("\\d+$", "");
            final boolean notLastElement = i + 1 < entries.size();
            final String previousKey = i > 0 ? entries.get(i - 1).getKey().replaceAll("\\d+$", "") : null;
            final String nextKey = notLastElement ? entries.get(i + 1).getKey().replaceAll("\\d+$", "") : null;
            final SearchOperation operation = mapParam.operations().get(key);

            if (notLastElement) {
                if ((previousKey == null && nextKey.equalsIgnoreCase(keyWithoutIndex)) ||
                    (previousKey != null && !previousKey.equalsIgnoreCase(keyWithoutIndex) && nextKey.equalsIgnoreCase(keyWithoutIndex))) {
                    whereClause.append(" ( ");
                }
            }

            switch (operation) {
                case CONTAINS -> whereClause.append(String.format("LOWER(%s) like LOWER(CONCAT('%%',:%s,'%%'))", keyWithoutIndex, key));
                case EQUAL -> whereClause.append(String.format("%s = :%s", keyWithoutIndex, key));
                case NOT_EQUAL -> whereClause.append(String.format("%s <> :%s", keyWithoutIndex, key));
                case GREATER_THAN -> whereClause.append(String.format("%s > :%s", keyWithoutIndex, key));
                case GREATER_THAN_EQUAL -> whereClause.append(String.format("%s >= :%s", keyWithoutIndex, key));
                case LESS_THAN -> whereClause.append(String.format("%s < :%s", keyWithoutIndex, key));
                case LESS_THAN_EQUAL -> whereClause.append(String.format("%s <= :%s", keyWithoutIndex, key));
                default -> {
                }
            }


            if (notLastElement) {
                if (nextKey.equalsIgnoreCase(keyWithoutIndex)) {
                    whereClause.append(" OR ");
                } else {
                    if (previousKey != null && previousKey.equalsIgnoreCase(keyWithoutIndex)) {
                        whereClause.append(" ) ");
                    }
                    whereClause.append(" AND ");
                }
            } else {
                if (previousKey != null && previousKey.equalsIgnoreCase(keyWithoutIndex)) {
                    whereClause.append(" ) ");
                }
            }
        }

        return whereClause;
    }
}