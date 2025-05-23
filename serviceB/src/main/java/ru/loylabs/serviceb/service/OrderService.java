package ru.loylabs.serviceb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loylabs.serviceb.model.Order;
import ru.loylabs.serviceb.repository.OrderRepository;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public void save(Order order) {
        orderRepository.save(order);
    }
}
