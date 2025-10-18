package com.juliomesquita.cdc.order.application.crud.service;

import com.juliomesquita.cdc.book.domain.repositories.dtos.BookPriceInfo;
import com.juliomesquita.cdc.book.infrastructure.book.facade.BookModuleFacade;
import com.juliomesquita.cdc.coupon.application.crud.dtos.CouponResponse;
import com.juliomesquita.cdc.coupon.infrastructure.facade.CouponModuleFacade;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderCreateRequest;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderResponse;
import com.juliomesquita.cdc.order.application.crud.dtos.OrderUpdateRequest;
import com.juliomesquita.cdc.order.application.crud.dtos.shared.OrderItemDto;
import com.juliomesquita.cdc.order.application.crud.mapper.OrderMapper;
import com.juliomesquita.cdc.order.domain.entities.Order;
import com.juliomesquita.cdc.order.domain.repositories.OrderRepository;
import com.juliomesquita.cdc.order.domain.vo.BookId;
import com.juliomesquita.cdc.order.domain.vo.CouponId;
import com.juliomesquita.cdc.order.domain.vo.UserInfos;
import com.juliomesquita.cdc.shared.exceptions.ResourceNotFoundException;
import com.juliomesquita.cdc.shared.services.GenericService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static java.util.stream.Collectors.toMap;

@Service
public class OrderService extends GenericService<Order, OrderCreateRequest, OrderUpdateRequest, OrderResponse, OrderRepository, OrderMapper> {
    private final BookModuleFacade bookModuleFacade;
    private final CouponModuleFacade couponModuleFacade;

    public OrderService(final OrderRepository repository, final OrderMapper mapper, final BookModuleFacade bookModuleFacade, final CouponModuleFacade couponModuleFacade) {
        super(repository, mapper);
        this.bookModuleFacade = Objects.requireNonNull(bookModuleFacade, "BookModuleFacade must not be null");
        this.couponModuleFacade = Objects.requireNonNull(couponModuleFacade, "CouponModuleFacade must not be null");
    }

    @Transactional("transactionManager")
    @Override
    public OrderResponse update(final UUID id, final OrderUpdateRequest request) {
        final Order order = this.repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(String.format("Order with id: %s not found", id)));

        final UserInfos userInfos = new UserInfos(request.general().toDomain(), request.address().toDomain(), request.contact().toDomain());

        final List<UUID> bookIds = this.extractBookIds(request.items());
        this.validateBooks(bookIds);

        final Map<BookId, Integer> mapBookId = this.covertListToMap(request.items());
        final BigDecimal totalPrice = this.calculateTotalPrice(bookIds, mapBookId);

        order
            .updateGeneral(userInfos)
            .updateItems(mapBookId, totalPrice);

        if (request.couponCode() != null) {
            final CouponResponse couponResponse = this.couponModuleFacade.getCouponByCode(request.couponCode());
            final BigDecimal discountedPrice = this.calculateDiscountedPrice(totalPrice, couponResponse.discountPercentage());
            order.applyCoupon(new CouponId(couponResponse.id()), discountedPrice);
        }

        this.repository.save(order);

        return this.mapper.toResponse(order);
    }

    private void validateBooks(List<UUID> listBookIds) {
        final long qntFound = this.bookModuleFacade.countExistingBooks(listBookIds);
        if (qntFound != listBookIds.size()) {
            throw new ResourceNotFoundException("Some of these books were not found.");
        }
    }

    private BigDecimal calculateTotalPrice(final List<UUID> bookIds, final Map<BookId, Integer> mapBookId) {
        final List<BookPriceInfo> bookPrices = this.bookModuleFacade.findBookPrices(bookIds);

        BigDecimal totalPrice = BigDecimal.ZERO;
        bookPrices.forEach(bookPrice -> {
            Integer quantity = mapBookId.get(new BookId(bookPrice.id()));
            BigDecimal itemTotal = bookPrice.price().multiply(BigDecimal.valueOf(quantity));
            totalPrice.add(itemTotal);
        });

        return totalPrice;
    }

    private List<UUID> extractBookIds(List<OrderItemDto> items) {
        return items.stream().map(OrderItemDto::bookId).toList();
    }

    private Map<BookId, Integer> covertListToMap(final List<OrderItemDto> items) {
        return items.stream()
            .collect(toMap(item -> new BookId(item.bookId()), OrderItemDto::quantity));
    }

    private BigDecimal calculateDiscountedPrice(final BigDecimal totalPrice, final Double discountPercentage) {
        BigDecimal discountAmount = totalPrice
            .multiply(BigDecimal.valueOf(discountPercentage))
            .divide(BigDecimal.valueOf(100), RoundingMode.CEILING);

        return totalPrice.subtract(discountAmount);
    }
}
