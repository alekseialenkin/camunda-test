package ru.loylabs.serviceb.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
@ToString
public class Order extends BaseEntity {
    private Long number;
    private UUID clientId;
    private LocalDate date = LocalDate.now();
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Item> items;
    private BigDecimal total;

    public Order(UUID id, Long number, UUID clientId, LocalDate date, List<Item> items, BigDecimal total) {
        super(id);
        this.number = number;
        this.clientId = clientId;
        this.date = date;
        this.items = items;
        this.total = total;
    }
}
