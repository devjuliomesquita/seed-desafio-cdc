package com.juliomesquita.cdc.order.domain.entities;

import com.juliomesquita.cdc.order.domain.enums.OrderStatus;
import com.juliomesquita.cdc.order.domain.vo.BookId;
import com.juliomesquita.cdc.order.domain.vo.CouponId;
import com.juliomesquita.cdc.order.domain.vo.UserInfos;
import com.juliomesquita.cdc.shared.entities.BaseEntityWithGeneratedId;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
public class Order extends BaseEntityWithGeneratedId {
    @Embedded
    @AttributeOverrides({
        // General Info
        @AttributeOverride(name = "infosGeneral.name", column = @Column(name = "user_name", nullable = false)),
        @AttributeOverride(name = "infosGeneral.lastName", column = @Column(name = "user_last_name", nullable = false)),
        @AttributeOverride(name = "infosGeneral.documentNumber", column = @Column(name = "user_document_number", nullable = false)),
        @AttributeOverride(name = "infosGeneral.documentType", column = @Column(name = "user_document_type", nullable = false)),

        // Address
        @AttributeOverride(name = "address.cep", column = @Column(name = "user_address_cep", nullable = false)),
        @AttributeOverride(name = "address.street", column = @Column(name = "user_address_street", nullable = false)),
        @AttributeOverride(name = "address.number", column = @Column(name = "user_address_number", nullable = false)),
        @AttributeOverride(name = "address.complement", column = @Column(name = "user_address_complement", nullable = false)),
        @AttributeOverride(name = "address.neighborhood", column = @Column(name = "user_address_neighborhood", nullable = false)),
        @AttributeOverride(name = "address.city", column = @Column(name = "user_address_city", nullable = false)),
        @AttributeOverride(name = "address.region", column = @Column(name = "user_address_region")),
        @AttributeOverride(name = "address.uf", column = @Column(name = "user_address_uf")),
        @AttributeOverride(name = "address.state", column = @Column(name = "user_address_state")),
        @AttributeOverride(name = "address.country", column = @Column(name = "user_address_country", nullable = false)),

        // Contact
        @AttributeOverride(name = "contact.email", column = @Column(name = "user_contact_email", nullable = false)),
        @AttributeOverride(name = "contact.phoneNumber", column = @Column(name = "user_contact_phone_number", nullable = false))
    })
    private UserInfos userInfos;

    @ElementCollection
    @CollectionTable(name = "order_items", joinColumns = @JoinColumn(name = "order_id"))
    @Column(name = "quantity")
    private Map<BookId, Integer> items = new HashMap<>();

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;

    @Embedded
    @AttributeOverride(name = "id", column = @Column(name = "coupon_id"))
    private CouponId couponId;

    @Column(name = "discount_price")
    private BigDecimal discountPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status;

    public static Order createOrder(final UserInfos userInfos ) {
        return new Order(userInfos, new HashMap<>(), BigDecimal.ZERO, null, BigDecimal.ZERO, OrderStatus.CREATED);
    }

    public Order updateGeneral(final UserInfos userInfos ) {
        this.userInfos = userInfos;
        return this;
    }

    public Order updateItems(final Map<BookId, Integer> items, final BigDecimal totalPrice) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.status = OrderStatus.FINALIZED;
        return this;
    }

    public Order applyCoupon(final CouponId couponId, final BigDecimal discountPrice) {
        this.couponId = couponId;
        this.discountPrice = discountPrice;
        return this;
    }

    public Order finalizeOrder() {
        this.status = OrderStatus.FINALIZED;
        return this;
    }

    protected Order() {
    }

    private Order(UserInfos userInfos, Map<BookId, Integer> items, BigDecimal totalPrice, CouponId couponId,
                  BigDecimal discountPrice, OrderStatus status) {
        this.userInfos = userInfos;
        this.items = items;
        this.totalPrice = totalPrice;
        this.couponId = couponId;
        this.discountPrice = discountPrice;
        this.status = status;
    }

    public UserInfos getUserInfos() {
        return userInfos;
    }

    public Map<BookId, Integer> getItems() {
        return items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public CouponId getCouponId() {
        return couponId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }
}
