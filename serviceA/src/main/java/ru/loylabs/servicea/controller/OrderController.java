package ru.loylabs.servicea.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.loylabs.servicea.dto.CreateOrderRequest;
import ru.loylabs.servicea.dto.CreateOrderResponse;
import ru.loylabs.servicea.dto.GetOrderResponse;
import ru.loylabs.servicea.dto.GetStatusResponse;
import ru.loylabs.servicea.mapper.OrderMapper;
import ru.loylabs.servicea.mapper.RequestMapper;
import ru.loylabs.servicea.model.Order;
import ru.loylabs.servicea.model.Request;
import ru.loylabs.servicea.model.Status;
import ru.loylabs.servicea.rabbitmq.RabbitProducer;
import ru.loylabs.servicea.service.OrderService;
import ru.loylabs.servicea.service.RequestService;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final RequestService requestService;
    private final RequestMapper requestMapper;
    private final OrderMapper orderMapper;
    private final RabbitProducer rabbitProducer;

    @PostMapping
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request) {
        Request createdRequest;
        try {
            Order order = orderService.create(request);
            createdRequest = requestService.create(order);
            int randomNumber = (int) (Math.random() * 5001);
            Thread.sleep(randomNumber);
            rabbitProducer.sendCreatedOrder(order);
            requestService.changeStatus(createdRequest.getId(), Status.COMPLETED);
        } catch (Exception e) {
            createdRequest = requestService.createWithException(e.getMessage());
        }
        return requestMapper.toCreateOrderResponse(createdRequest);
    }

    @GetMapping("/status/{requestId}")
    public GetStatusResponse getStatus(@PathVariable(name = "requestId") UUID requestId) {
        Request request = requestService.findByRequestId(requestId);
        return requestMapper.toGetStatusResponse(request);
    }

    @GetMapping("/{orderId}")
    public GetOrderResponse getOrder(@PathVariable(name = "orderId") UUID orderId) {
        return orderMapper.toGetOrderResponse(orderService.findByOrderId(orderId));
    }

    @GetMapping("/order/{orderNumber}")
    public GetOrderResponse getOrderByOrderNumber(@PathVariable(name = "orderNumber") Long orderNumber) {
        return orderMapper.toGetOrderResponse(orderService.findByOrderNumber(orderNumber));
    }

    @GetMapping("/client/{clientId}")
    public List<GetOrderResponse> getOrderByClientId(@PathVariable(name = "clientId") UUID clientId) {
        return orderMapper.toGetOrderResponseList(orderService.findByClientId(clientId));
    }

    @GetMapping("/date")
    public List<GetOrderResponse> getOrderByDate(@RequestParam(name = "date") LocalDate date) {
        return orderMapper.toGetOrderResponseList(orderService.findByDate(date));
    }

    @GetMapping("/camunda/{orderId}")
    public Request getCamundaOrder(@PathVariable(name = "orderId") UUID orderId) {
        return orderService.getFromServiceB(orderId);
    }
}
