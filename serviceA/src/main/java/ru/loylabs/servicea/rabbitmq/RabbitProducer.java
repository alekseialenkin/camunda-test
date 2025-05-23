package ru.loylabs.servicea.rabbitmq;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.loylabs.servicea.JsonUtil;
import ru.loylabs.servicea.model.Order;
import ru.loylabs.servicea.model.Request;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class RabbitProducer {
    private final RabbitTemplate rabbitTemplate;
    private final JsonUtil jsonUtil;

    public void sendCreatedOrder(Order order) {
        rabbitTemplate.convertAndSend("exchange", "order", jsonUtil.serialize(order));
    }

    public Request sendGetOrder(UUID orderId) {
        return jsonUtil.deserialize(new String(rabbitTemplate.sendAndReceive("exchange", "order-get", new Message(orderId.toString().getBytes())).getBody()), Request.class);
    }
}
