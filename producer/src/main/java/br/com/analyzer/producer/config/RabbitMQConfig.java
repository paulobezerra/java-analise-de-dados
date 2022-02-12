package br.com.analyzer.producer.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    @Value("${spring.rabbitmq.queue}")
    private String queue;

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Queue appQueueGeneric() {
        return new Queue(queue);
    }

    @Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with(routingkey);
    }
}
