package com.users.api.mensaje;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    
    //CONFIGURACION DEL RABBIT
    
    @Value("${apiusuarios.queue.name}")
    private String queueName;
    
     @Bean
    public Queue rabbitQueue() {
        return new Queue(queueName);
    }
}
