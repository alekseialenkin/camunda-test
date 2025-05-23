package ru.loylabs.serviceb.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
