package ru.loylabs.servicea.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitQueueConfig {
    @Bean
    public Queue orderQueue() {
        return new Queue("order", false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange("exchange");
    }

    @Bean
    public Binding orderBinding(@Qualifier("orderQueue") Queue orderQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(orderQueue)
                .to(exchange)
                .with("order");
    }

    @Bean
    public Queue getQueue() {
        return new Queue("order-get", false);
    }

    @Bean
    public Binding orderGetBinding(@Qualifier("getQueue") Queue getQueue, DirectExchange exchange) {
        return BindingBuilder
                .bind(getQueue)
                .to(exchange)
                .with("order-get");
    }
}
