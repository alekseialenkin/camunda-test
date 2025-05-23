package ru.loylabs.serviceb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.loylabs.serviceb.model.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
