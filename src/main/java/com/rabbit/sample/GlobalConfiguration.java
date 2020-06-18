package com.rabbit.sample;

import com.rabbit.sample.multivhosts.VhostConfiguration;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.SimpleRoutingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class GlobalConfiguration {

    public static final String EXCHANGE_NAME = "mytest.exchange";
    public static final String ROUTING_KEY = "mytest.routing.key";

    @Autowired
    private List<VhostConfiguration> vhostConfigurations;

    @Bean
    @Primary
    public SimpleRoutingConnectionFactory routingConnectionFactory() {
        final var rcf = new SimpleRoutingConnectionFactory();
        final Map<Object, ConnectionFactory> routeMap = vhostConfigurations.stream()
                .collect(Collectors.toMap(VhostConfiguration::getVHost, v -> v.connectionFactory()));
        rcf.setTargetConnectionFactories(routeMap);
        return rcf;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(routingConnectionFactory());
    }

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue mytestQueue() {
        return QueueBuilder
                .durable("mytest.routing.queue")
                .lazy()
                .deadLetterExchange("mytest.deadletter.exchange")
                .deadLetterRoutingKey("mytest.deadletter.routing.key")
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(mytestQueue())
                .to(directExchange())
                .with(ROUTING_KEY);
    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange("mytest.deadletter.exchange");
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder
                .durable("mytest.deadletter.routing.queue")
                .build();
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue())
                .to(deadLetterExchange())
                .with("mytest.deadletter.routing.key");
    }
}
