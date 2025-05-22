package ru.loylabs.servicea.dto;

import java.math.BigDecimal;

public record CreateItemRequest(String name, BigDecimal price, Long quantity) {
}
