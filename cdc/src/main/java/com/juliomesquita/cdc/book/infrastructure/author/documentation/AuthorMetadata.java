package com.juliomesquita.cdc.book.infrastructure.author.documentation;

import com.juliomesquita.cdc.shared.doc.Searchable;
import com.juliomesquita.cdc.shared.doc.SearchableField;
import com.juliomesquita.cdc.shared.doc.SearchableRelation;

import java.util.List;

import static com.juliomesquita.cdc.shared.repositories.SearchOperation.getStringOperations;

public final class AuthorMetadata implements Searchable {
    private AuthorMetadata() {
    }

    public static List<SearchableField> searchableFields(){
        return new AuthorMetadata().getSearchableFields();
    }

    public static List<SearchableRelation> searchableRelations(){
        return new AuthorMetadata().getSearchableRelations();
    }

    @Override
    public List<SearchableField> getSearchableFields() {
        return List.of(
            new SearchableField("name", "Author name", getStringOperations()),
            new SearchableField("email", "Author email", getStringOperations()),
            new SearchableField("description", "Author description", getStringOperations())
        );
    }

    @Override
     public List<SearchableRelation> getSearchableRelations() {
        return List.of();
    }
}
