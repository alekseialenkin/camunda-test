package ru.loylabs.servicea.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record GetOrderResponse(UUID orderId, Long number, UUID clientId, LocalDate date, List<GetItemResponse> items,
                               BigDecimal total) {
}
