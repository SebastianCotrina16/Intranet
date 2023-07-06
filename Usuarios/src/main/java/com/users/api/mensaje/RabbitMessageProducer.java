/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.users.api.mensaje;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.api.Entidad.rabbitmessage;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMessageProducer {
    
    //CONFIGURACION DEL PRODUCER PARA RABBITMQ

    private final RabbitTemplate rabbitTemplate;
    private final Queue queue;
    private final ObjectMapper objectMapper;

    @Autowired
    public RabbitMessageProducer(RabbitTemplate rabbitTemplate, Queue queue, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.queue = queue;
        this.objectMapper = objectMapper;
    }
    
    //METODO DE ENVIO DE MENSAJE
    public void sendMessage(rabbitmessage message) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(message);
            rabbitTemplate.convertAndSend(queue.getName(), jsonMessage);
        } catch (JsonProcessingException e) {
            // Manejar el error de serialización
            e.printStackTrace();
        }
    }
}