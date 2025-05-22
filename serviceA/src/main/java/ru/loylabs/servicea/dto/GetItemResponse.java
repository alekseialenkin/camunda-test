package ru.loylabs.servicea.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record GetItemResponse(UUID id, String name, BigDecimal price, Long quantity) {
}
