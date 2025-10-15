package com.juliomesquita.cdc.shared.utils;

import com.juliomesquita.cdc.shared.repositories.SearchOperation;

public record Param(String key, Object value, SearchOperation operator) {
}
