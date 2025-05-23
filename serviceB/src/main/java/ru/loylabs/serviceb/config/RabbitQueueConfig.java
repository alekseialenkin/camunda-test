package ru.loylabs.serviceb.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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
    public DirectExchange exchange() {
        return new DirectExchange("exchange");
    }

    @Bean
    public Binding orderBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("order");
    }

}
