package com.rabbit.sample.multivhosts;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;

public interface VhostConfiguration {

    String getVHost();

    ConnectionFactory connectionFactory();
}
