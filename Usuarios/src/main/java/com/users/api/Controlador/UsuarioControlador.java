package com.users.api.Controlador;

import com.users.api.Entidad.Usuarios;
import com.users.api.Entidad.rabbitmessage;
import com.users.api.Repositorio.UsuarioRepositorio;
import com.users.api.Servicio.EmailServicio;
import com.users.api.Servicio.UsuarioServicio;
import com.users.api.mensaje.RabbitMessageProducer;
import jakarta.mail.MessagingException;
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
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioControlador {
    
    //CONTROLADOR PARA USUARIOS
    private final RabbitMessageProducer rabbitMessageProducer;  
    
    
    @Autowired
    private UsuarioRepositorio usuariosRepository;
    
    @Autowired
    private EmailServicio emailService;
    
    private final UsuarioServicio usuarioservicio;
    
    @Autowired
    public UsuarioControlador(UsuarioServicio usuarioservicio, RabbitMessageProducer rabbitMessageProducer) {
        this.usuarioservicio = usuarioservicio;
        this.rabbitMessageProducer = rabbitMessageProducer;
    }
    
    @GetMapping
    public List<Usuarios> findAll(){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("GET");
        message.setMessage("Se ha listado los usuarios");
        rabbitMessageProducer.sendMessage(message);
        return usuarioservicio.findAll();
    }
    
    @GetMapping("/{id}")
    public Usuarios findById(@PathVariable String id){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("GET");
        message.setMessage("Se ha realizado busqueda del usuario");
        rabbitMessageProducer.sendMessage(message);
        Optional<Usuarios> usuario = usuarioservicio.findById(id);
        return usuario.orElse(null);
        
    }
    
    @PostMapping
    public void save(@RequestBody Usuarios u) throws MessagingException{
        usuarioservicio.save(u);
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("POST");
        message.setMessage("Se ha guardado un nuevo usuario");
        rabbitMessageProducer.sendMessage(message);
        
        // Envío del correo electrónico al usuario
        String correo = u.getCorreo();
        String dni = u.getDni();
        String contrasenia = u.getContrasenia();

        String subject = "Registro exitoso";
        String body = "<html><body>" +
        "<h2>¡Bienvenido nuevo usuario!</h2>" +
        "<p>Nos complace informarte que tu registro ha sido exitoso. A continuación, encontrarás tus datos de acceso:</p>" +
        "<ul>" +
        "<li><strong>Codigo:</strong><br>" + dni + "</li>" +
        "<li><strong>Contraseña:</strong><br>" + contrasenia + "</li>" +
        "</ul>" +
        "<p>Si tienes alguna pregunta o necesitas asistencia adicional, no dudes en contactarnos. ¡Bienvenido y que disfrutes de nuestros servicios!</p>" +
        "<p>Saludos cordiales" +
        "</body></html>";
        emailService.enviarCorreo(correo, subject, body);     

    }
    
    @PutMapping("/{id}")
    public void update(@RequestBody Usuarios u){
        // Enviar mensaje a RabbitMQ
        rabbitmessage message = new rabbitmessage();
        message.setTimestamp(LocalDateTime.now());
        message.setLevel("PUT");
        message.setMessage("Se ha realizado los cambios del usuario");
        rabbitMessageProducer.sendMessage(message);
        usuarioservicio.save(u);

    }       
}
