package com.juliomesquita.cdc.book.infrastructure.book.documentation;

import com.juliomesquita.cdc.shared.doc.Searchable;
import com.juliomesquita.cdc.shared.doc.SearchableField;
import com.juliomesquita.cdc.shared.doc.SearchableRelation;

import java.util.List;

import static com.juliomesquita.cdc.shared.repositories.SearchOperation.*;

public final class BookMetadata implements Searchable {
    private BookMetadata() {
    }

    public static List<SearchableField> searchableFields(){
        return new BookMetadata().getSearchableFields();
    }

    public static List<SearchableRelation> searchableRelations(){
        return new BookMetadata().getSearchableRelations();
    }

    @Override
    public List<SearchableField> getSearchableFields() {
        return List.of(
            new SearchableField("title", "Book title", getStringOperations()),
            new SearchableField("abstractText", "Book resume", getStringOperations()),
            new SearchableField("price", "Book price", getNumericOperations()),
            new SearchableField("numberOfPages", "Book number of the pages", getNumericOperations()),
            new SearchableField("publicationDate", "Book publication date", getDateOperations()),
            new SearchableField("isbn", "Book isbn", getStringOperations())
        );
    }

    @Override
     public List<SearchableRelation> getSearchableRelations() {
        return List.of(
            new SearchableRelation(
                "author",
                List.of(
                    new SearchableField("name", "Author name", getStringOperations()),
                    new SearchableField("email", "Author email", getStringOperations()),
                    new SearchableField("description", "Author description", getStringOperations())
                )
            ),
            new SearchableRelation(
                "category",
                List.of(
                    new SearchableField("name", "Category name", getStringOperations())
                )
            )
        );
    }
}
