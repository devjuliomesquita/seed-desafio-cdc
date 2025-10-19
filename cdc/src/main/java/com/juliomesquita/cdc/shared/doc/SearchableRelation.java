package com.juliomesquita.cdc.shared.doc;

import java.util.List;

public record SearchableRelation(
    String relationName,
    List<SearchableField> searchableFields
) {}
