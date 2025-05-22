package ru.loylabs.servicea.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity {
    private Long number;
    private UUID clientId;
    private LocalDate date = LocalDate.now();
    @OneToMany(cascade = CascadeType.ALL)
    private List<Item> items;
    private BigDecimal total;
}
