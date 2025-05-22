package ru.loylabs.servicea.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {
    @Bean
    public Queue queue() {
        return new Queue("order", false);
    }

    @Bean
    public Exchange exchange() {
        return new DirectExchange("exchange");
    }
}
