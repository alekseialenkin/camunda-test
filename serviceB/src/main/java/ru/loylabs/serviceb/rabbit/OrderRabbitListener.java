package ru.loylabs.serviceb.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.loylabs.serviceb.JsonUtil;
import ru.loylabs.serviceb.model.Order;
import ru.loylabs.serviceb.camunda.CamundaService;

@Component
public class OrderRabbitListener {
    private final JsonUtil jsonUtil;
    private final CamundaService camundaService;

    public OrderRabbitListener(JsonUtil jsonUtil, CamundaService camundaService) {
        this.jsonUtil = jsonUtil;
        this.camundaService = camundaService;
    }

    @RabbitListener(queues = "order")
    public void receive(String in) {
        Order order = jsonUtil.deserialize(in, Order.class);
        camundaService.sendOrderToCamunda(order);
    }
}
