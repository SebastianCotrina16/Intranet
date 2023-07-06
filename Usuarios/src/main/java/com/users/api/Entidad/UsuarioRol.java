package com.users.api.Entidad;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "UsuarioRol")
public class UsuarioRol{    
    @Id      
    private long idur;    
    private Usuarios fk_dni;    
    private Rol fk_rol;
}
