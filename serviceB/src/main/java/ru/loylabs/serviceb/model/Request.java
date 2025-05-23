package ru.loylabs.serviceb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Request extends BaseEntity {
    private Status status = Status.IN_PROGRESS;
    private String errorReason;
    @OneToOne
    private Order order;

    public Request(UUID id, Status status, String errorReason, Order order) {
        super(id);
        this.status = status;
        this.errorReason = errorReason;
        this.order = order;
    }
}
