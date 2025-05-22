package ru.loylabs.servicea.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.loylabs.servicea.JsonUtil;
import ru.loylabs.servicea.dto.CreateOrderRequest;
import ru.loylabs.servicea.exceptions.OrderNotFoundException;
import ru.loylabs.servicea.mapper.OrderMapper;
import ru.loylabs.servicea.model.Order;
import ru.loylabs.servicea.rabbitmq.RabbitProducer;
import ru.loylabs.servicea.repository.OrderRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Transactional
    public Order create(CreateOrderRequest request) {
        return orderRepository.save(orderMapper.toEntity(request));
    }

    public Order findByOrderId(UUID orderId) {
        return orderRepository.findByIdWithItems(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order with id " + orderId + " not found"));
    }

    public Order findByOrderNumber(Long orderNumber) {
        return orderRepository.findByOrderNumberWithItems(orderNumber)
                .orElseThrow(() -> new OrderNotFoundException("Order with number " + orderNumber + " not found"));
    }

    public List<Order> findByClientId(UUID clientId) {
        return orderRepository.findAllByClientIdWithItems(clientId);
    }

    public List<Order> findByDate(LocalDate date) {
        return orderRepository.findAllByDateWithItems(date);
    }
}
