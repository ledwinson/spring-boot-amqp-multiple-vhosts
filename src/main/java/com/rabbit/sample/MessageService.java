package com.rabbit.sample;

import org.springframework.amqp.rabbit.connection.SimpleResourceHolder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import java.util.stream.IntStream;

public class MessageService implements CommandLineRunner {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        IntStream.range(1, 11).forEach(this::sendToVHost);
    }

    private void sendToVHost(int count) {
        try{
            String vhostName = count % 2 == 0 ? "firstvhost" : "secondvhost";
            String body = "Message to " + vhostName + " count = " + count;
            System.out.println("Sending " + body);
            SimpleResourceHolder.bind(rabbitTemplate.getConnectionFactory(), vhostName);
            rabbitTemplate.convertAndSend(GlobalConfiguration.EXCHANGE_NAME,
                    GlobalConfiguration.ROUTING_KEY, body);
        } catch(Exception e) {
            throw new RuntimeException(e);
        } finally {
            SimpleResourceHolder.unbind(rabbitTemplate.getConnectionFactory());
        }
    }
}
