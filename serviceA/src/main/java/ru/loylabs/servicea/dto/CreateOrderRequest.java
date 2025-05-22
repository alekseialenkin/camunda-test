package ru.loylabs.servicea.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(Long number, UUID clientId, LocalDate date, List<CreateItemRequest> items) {
}
