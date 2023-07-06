/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.users.api.Controlador;

import com.users.api.Entidad.UsuarioRol;
import com.users.api.Entidad.rabbitmessage;
import com.users.api.Servicio.UsuarioRolServicio;
import com.users.api.mensaje.RabbitMessageProducer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuariorol")
@RequiredArgsConstructor
public class UsuarioRolControlador {
    
    //CONTROLADOR PARA USUARIOROL
    
    private final RabbitMessageProducer rabbitMessageProducer; 
    private final UsuarioRolServicio userrolservicio;
    
    @Autowired
    public UsuarioRolControlador(UsuarioRolServicio userrolservicio, RabbitMessageProducer rabbitMessageProducer) {
        this.userrolservicio = userrolservicio;
        this.rabbitMessageProducer = rabbitMessageProducer;
    }
    
    @GetMapping
    public List<UsuarioRol> findAll(){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("GET");
        message.setMessage("Se ha listado los usuarios con rol");
        rabbitMessageProducer.sendMessage(message);
        return userrolservicio.findAll();
    }
    
    @GetMapping("/{idur}")
    public UsuarioRol findById(@PathVariable Long idur){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("GET");
        message.setMessage("Se ha realizado la busqueda del usuario con rol");
        rabbitMessageProducer.sendMessage(message);
         Optional<UsuarioRol> usuariorol = userrolservicio.findById(idur);
        return usuariorol.orElse(null);
    }
    
    @PostMapping
    public void save(@RequestBody UsuarioRol ur){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("POST");
        message.setMessage("Se ha asignado un rol al usuario");
        rabbitMessageProducer.sendMessage(message);
        userrolservicio.save(ur);
    }
    
    @PutMapping("/{idur}")
    public void update(@RequestBody UsuarioRol ur){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("PUT");
        message.setMessage("Se ha realizado el cambio de rol al usuario");
        rabbitMessageProducer.sendMessage(message);
        userrolservicio.save(ur);
    }    
       
}
