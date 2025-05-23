package ru.loylabs.serviceb.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;
import ru.loylabs.serviceb.JsonUtil;
import ru.loylabs.serviceb.camunda.CamundaService;
import ru.loylabs.serviceb.model.Order;
import ru.loylabs.serviceb.service.RequestService;

import java.util.UUID;

@Component
public class OrderRabbitListener {
    private final JsonUtil jsonUtil;
    private final CamundaService camundaService;
    private final RequestService requestService;

    public OrderRabbitListener(JsonUtil jsonUtil, CamundaService camundaService, RequestService requestService) {
        this.jsonUtil = jsonUtil;
        this.camundaService = camundaService;
        this.requestService = requestService;
    }

    @RabbitListener(queues = "order")
    public void receive(String in) {
        Order order = jsonUtil.deserialize(in, Order.class);
        camundaService.sendOrderToCamunda(order);
    }

    @RabbitListener(queues = "order-get")
    @SendTo
    public String receiveOrder(Message message) {
        UUID uuid = UUID.fromString(new String(message.getBody(), 0, 36));
        return jsonUtil.serialize(requestService.getByOrderId(uuid));
    }
}
