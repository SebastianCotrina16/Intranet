package com.users.api.Entidad;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Document(value = "Usuarios")
public class Usuarios {
    
    @Id    
    private String dni;    
    private String contrasenia;    
    private boolean estado;
    private String correo;    
}
