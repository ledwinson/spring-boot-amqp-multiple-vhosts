package com.rabbit.sample.multivhosts;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
@Service
public class SecondVhostConfiguration implements VhostConfiguration {

    @Override
    public String getVHost() {
        return "secondvhost";
    }

    @Override
    public ConnectionFactory connectionFactory() {
        return secondConnectionFactory();
    }

    @Bean
    public ConnectionFactory secondConnectionFactory() {
        var connFactory = new CachingConnectionFactory("localhost");
        connFactory.setVirtualHost(getVHost());
        connFactory.setUsername("guest");
        connFactory.setPassword("guest");
        return connFactory;
    }
}
