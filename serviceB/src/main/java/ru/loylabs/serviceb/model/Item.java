package ru.loylabs.serviceb.model;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item extends BaseEntity {
    private String name;
    private BigDecimal price;
    private Long quantity;

    public Item(UUID id, String name, BigDecimal price, Long quantity) {
        super(id);
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}
