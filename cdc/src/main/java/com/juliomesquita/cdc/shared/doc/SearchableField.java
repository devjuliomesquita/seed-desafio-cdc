package com.juliomesquita.cdc.shared.doc;

import java.util.List;

public record SearchableField(
    String fieldName,
    String description,
    List<String> availableOperators
) {}
