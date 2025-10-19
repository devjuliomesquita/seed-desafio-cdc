package com.juliomesquita.cdc.order.infrastructure.documentation;

import com.juliomesquita.cdc.shared.doc.Searchable;
import com.juliomesquita.cdc.shared.doc.SearchableField;
import com.juliomesquita.cdc.shared.doc.SearchableRelation;
import com.juliomesquita.cdc.shared.repositories.SearchOperation;

import java.util.List;

public final class OrderMetadata implements Searchable {

    // The controller can instantiate this class to serve the metadata.

    @Override
    public List<SearchableField> getSearchableFields() {
        return List.of(
            // Direct fields from the Order entity
            new SearchableField("totalPrice", "The total price of the order", SearchOperation.getNumericOperations()),
            new SearchableField("discountPrice", "The discount value applied to the order", SearchOperation.getNumericOperations()),
            new SearchableField("status", "The current status of the order (e.g., CREATED, FINALIZED)", SearchOperation.getStringOperations()),
            new SearchableField("couponId.id", "The ID of the applied coupon", SearchOperation.getStringOperations()),

            // Nested fields from UserInfos.InfosGeneral
            new SearchableField("userInfos.infosGeneral.name", "Customer's first name", SearchOperation.getStringOperations()),
            new SearchableField("userInfos.infosGeneral.lastName", "Customer's last name", SearchOperation.getStringOperations()),
            new SearchableField("userInfos.infosGeneral.documentNumber", "Customer's document number", SearchOperation.getStringOperations()),

            // Nested fields from UserInfos.Address
            new SearchableField("userInfos.address.cep", "Customer's postal code (CEP)", SearchOperation.getStringOperations()),
            new SearchableField("userInfos.address.city", "Customer's city", SearchOperation.getStringOperations()),
            new SearchableField("userInfos.address.state", "Customer's state", SearchOperation.getStringOperations()),
            new SearchableField("userInfos.address.country", "Customer's country", SearchOperation.getStringOperations()),

            // Nested fields from UserInfos.Contact
            new SearchableField("userInfos.contact.email", "Customer's contact email", SearchOperation.getStringOperations()),
            new SearchableField("userInfos.contact.phoneNumber", "Customer's contact phone number", SearchOperation.getStringOperations())
        );
    }

    @Override
    public List<SearchableRelation> getSearchableRelations() {
        // As discussed, filtering by the 'items' ElementCollection is not supported
        // by the generic specification utility. If this were a @OneToMany relationship
        // with an OrderItem entity, it would be listed here.
        return List.of();
    }
}