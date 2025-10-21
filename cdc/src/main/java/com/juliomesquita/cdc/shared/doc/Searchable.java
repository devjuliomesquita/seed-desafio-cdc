package com.juliomesquita.cdc.shared.doc;

import java.util.List;

public interface Searchable {
    List<SearchableField> getSearchableFields();
    List<SearchableRelation> getSearchableRelations();
}
