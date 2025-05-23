package ru.loylabs.serviceb.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loylabs.serviceb.model.Order;

@Service
@RequiredArgsConstructor
public class OrderService {
    @Transactional
    public void save(Order order) {
        System.out.println("saved order: " + order);
    }
}
