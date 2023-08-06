package com.mak.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;

@Configuration
public class MessagingConfig {

    public static String QUEUE;
    public static String EXCHANGE;
    public static String ROUTING_KEY;

    @Value("${app.queue}")
    private void setQueue(String name) {
        MessagingConfig.QUEUE = name;
    }

    @Value("${app.exchange}")
    private void setExchange(String name) {
        MessagingConfig.EXCHANGE = name;
    }

    @Value("${app.routingKey}")
    private void setRoutingKey(String name) {
        MessagingConfig.ROUTING_KEY = name;
    }

    @Bean
    public Queue queue() {
        return new Queue(QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }

}
