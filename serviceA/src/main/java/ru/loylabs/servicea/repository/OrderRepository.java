package ru.loylabs.servicea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.loylabs.servicea.model.Order;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.id = :orderId")
    Optional<Order> findByIdWithItems(@Param("orderId") UUID orderId);

    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.number = :orderNumber")
    Optional<Order> findByOrderNumberWithItems(@Param("orderNumber") Long orderNumber);

    @Query("SELECT o FROM Order o JOIN FETCH o.items WHERE o.clientId = :clientId")
    List<Order> findAllByClientIdWithItems(@Param("clientId") UUID clientId);

    @Query("SELECT o FROM Order o  JOIN FETCH o.items WHERE o.date = :date")
    List<Order> findAllByDateWithItems(@Param("date") LocalDate date);
}
