package com.juliomesquita.cdc.book.infrastructure.category.documentation;

import com.juliomesquita.cdc.shared.doc.Searchable;
import com.juliomesquita.cdc.shared.doc.SearchableField;
import com.juliomesquita.cdc.shared.doc.SearchableRelation;

import java.util.List;

import static com.juliomesquita.cdc.shared.repositories.SearchOperation.getStringOperations;

public final class CategoryMetadata implements Searchable {
    private CategoryMetadata() {
    }

    public static List<SearchableField> searchableFields(){
        return new CategoryMetadata().getSearchableFields();
    }

    public static List<SearchableRelation> searchableRelations(){
        return new CategoryMetadata().getSearchableRelations();
    }

    @Override
    public List<SearchableField> getSearchableFields() {
        return List.of(
            new SearchableField("name", "Category name", getStringOperations())
        );
    }

    @Override
     public List<SearchableRelation> getSearchableRelations() {
        return List.of();
    }
}
