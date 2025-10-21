package com.juliomesquita.cdc.shared.repositories;

import java.util.List;
import java.util.stream.Stream;

public enum SearchOperation {
    CONTAINS("cn"),
    DOES_NOT_CONTAIN("nc"),
    EQUAL("eq"),
    NOT_EQUAL("ne"),
    BEGINS_WITH("bw"),
    DOES_NOT_BEGIN_WITH("bn"),
    ENDS_WITH("ew"),
    DOES_NOT_END_WITH("en"),
    NULL("nu"),
    NOT_NULL("nn"),
    GREATER_THAN("gt"),
    GREATER_THAN_EQUAL("ge"),
    LESS_THAN("lt"),
    LESS_THAN_EQUAL("le"),
    ANY("any"),
    ALL("all");

    private final String operation;

    SearchOperation(String operation) {
        this.operation = operation;
    }

    public String getOperation() {
        return operation;
    }

    public static final String[] SIMPLE_OPERATION_SET = {
        "cn", "nc", "eq", "ne", "bw", "bn", "ew", "en", "nu", "nn", "gt", "ge", "lt", "le"
    };

    public static SearchOperation getDataOption(final String dataOption){
        return switch (dataOption) {
            case "all" -> ALL;
            case "any" -> ANY;
            default -> null;
        };
    }

    public static SearchOperation getSimpleOperation(final String input){
        for (SearchOperation op : values()) {
            if (op.getOperation().equalsIgnoreCase(input)) {
                return op;
            }
        }
        return null;
    }

    public static List<String> getStringOperations() {
        return Stream.of(CONTAINS, DOES_NOT_CONTAIN, EQUAL, NOT_EQUAL, BEGINS_WITH, DOES_NOT_BEGIN_WITH, ENDS_WITH, DOES_NOT_END_WITH, NULL, NOT_NULL)
            .map(SearchOperation::getOperation)
            .toList();
    }

    public static List<String> getNumericOperations() {
        return Stream.of(EQUAL, NOT_EQUAL, GREATER_THAN, GREATER_THAN_EQUAL, LESS_THAN, LESS_THAN_EQUAL, NULL, NOT_NULL)
            .map(SearchOperation::getOperation)
            .toList();
    }

    public static List<String> getDateOperations() {
        return getNumericOperations();
    }

    public static List<String> getBooleanOperations() {
        return Stream.of(EQUAL, NOT_EQUAL, NULL, NOT_NULL)
            .map(SearchOperation::getOperation)
            .toList();
    }
}