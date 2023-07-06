package com.users.api.Entidad;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value = "Rol")
public class Rol {
    @Id
    private String idrol;
    private String nomrol;
}
