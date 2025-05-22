package ru.loylabs.servicea.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.loylabs.servicea.dto.CreateOrderResponse;
import ru.loylabs.servicea.dto.GetStatusResponse;
import ru.loylabs.servicea.model.Order;
import ru.loylabs.servicea.model.Request;

@Mapper
public interface RequestMapper {
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "errorReason", ignore = true)
    @Mapping(target = "order", source = "order")
    @Mapping(target = "id", ignore = true)
    Request toEntity(Order order);

    @Mapping(target = "requestId", source = "id")
    CreateOrderResponse toCreateOrderResponse(Request createdRequest);

    @Mapping(target = "status", source = "request", qualifiedByName = "getStatus")
    GetStatusResponse toGetStatusResponse(Request request);

    @Named("getStatus")
    default String getStatus(Request request) {
        return switch (request.getStatus()) {
            case IN_PROGRESS -> "В процессе";
            case ERROR -> "Ошибка " + request.getErrorReason();
            case COMPLETED -> "Завершено " + request.getId();
        };
    }
}
