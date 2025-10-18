package com.juliomesquita.cdc.shared.utils;

import com.juliomesquita.cdc.shared.repositories.SearchOperation;

import java.util.HashMap;
import java.util.Map;

public record MapParam(Map<String, Object> params, Map<String, SearchOperation> operations) {
    public static MapParam create() {
        return new MapParam(new HashMap<>(), new HashMap<>());
    }
}
