package ru.loylabs.servicea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.loylabs.servicea.dto.CreateOrderRequest;
import ru.loylabs.servicea.dto.GetOrderResponse;
import ru.loylabs.servicea.model.Order;

import java.math.BigDecimal;
import java.util.List;

@Mapper(uses = ItemMapper.class)
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "total", source = "request", qualifiedByName = "getTotal")
    Order toEntity(CreateOrderRequest request);

    @Named("getTotal")
    default BigDecimal getTotal(CreateOrderRequest request) {
        return request.items().stream()
                .map(item -> item.price().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    @Mapping(target = "orderId", source = "id")
    GetOrderResponse toGetOrderResponseList(Order byOrderId);

    List<GetOrderResponse> toGetOrderResponseList(List<Order> byClientId);
}
