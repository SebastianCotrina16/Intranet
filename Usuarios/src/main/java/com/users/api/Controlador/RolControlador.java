package com.users.api.Controlador;

import com.users.api.Entidad.Rol;
import com.users.api.Entidad.rabbitmessage;
import com.users.api.Servicio.RolServicio;
import com.users.api.mensaje.RabbitMessageProducer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/rol")
@RequiredArgsConstructor
public class RolControlador {
    
    //CONTROLADOR PARA ROL
    
    private final RabbitMessageProducer rabbitMessageProducer;
    private final RolServicio rolservicio;
    
    @Autowired
    public RolControlador(RolServicio rolservicio, RabbitMessageProducer rabbitMessageProducer) {
        this.rolservicio = rolservicio;
        this.rabbitMessageProducer = rabbitMessageProducer;
    }
    
    @PostMapping("/roles")
    public void save(@RequestBody Rol r){
        rolservicio.save(r);
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("POST");
        message.setMessage("Se ha guardado un nuevo rol");
        rabbitMessageProducer.sendMessage(message);
        
    }
    
    @GetMapping
    public List<Rol> findAll(){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("GET");
        message.setMessage("Se ha listado los roles");
        rabbitMessageProducer.sendMessage(message);  
        return rolservicio.findAll();              
    }
    
    @GetMapping("/roles/{idrol}")
    public Rol findById(@PathVariable String idrol){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("GET");
        message.setMessage("Se ha realizado la busqueda del rol");
        rabbitMessageProducer.sendMessage(message);  
        Optional<Rol> rol = rolservicio.findById(idrol);
        return rol.orElse(null);
    }
    
    @DeleteMapping("/roles/{idrol}")
    public void deleteById(@PathVariable String idrol){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("DELETE");
        message.setMessage("Se ha eliminado el rol");
        rabbitMessageProducer.sendMessage(message);  
        rolservicio.deleteById(idrol);

    }
    
    @PutMapping("/{idrol}")
    public void update(@RequestBody Rol r){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("PUT");
        message.setMessage("Se ha actualizado el rol");
        rabbitMessageProducer.sendMessage(message);  
        rolservicio.save(r);        
    }
}
