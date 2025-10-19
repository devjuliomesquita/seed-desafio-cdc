package com.juliomesquita.cdc.coupon.infrastructure.documentation;

import com.juliomesquita.cdc.shared.doc.Searchable;
import com.juliomesquita.cdc.shared.doc.SearchableField;
import com.juliomesquita.cdc.shared.doc.SearchableRelation;

import java.util.List;

import static com.juliomesquita.cdc.shared.repositories.SearchOperation.*;

public final class CouponMetadata implements Searchable {
    private CouponMetadata() {
    }

    public static List<SearchableField> searchableFields(){
        return new CouponMetadata().getSearchableFields();
    }

    public static List<SearchableRelation> searchableRelations(){
        return new CouponMetadata().getSearchableRelations();
    }

    @Override
    public List<SearchableField> getSearchableFields() {
        return List.of(
            new SearchableField("code", "Coupon code", getStringOperations()),
            new SearchableField("discountPercentage", "Coupon discount percentage", getNumericOperations()),
            new SearchableField("validFrom", "Coupon expiration date", getDateOperations())
        );
    }

    @Override
     public List<SearchableRelation> getSearchableRelations() {
        return List.of();
    }
}
