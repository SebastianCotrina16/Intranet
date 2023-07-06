package com.users.api.Repositorio;

import com.users.api.Entidad.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//REPOSITORIO PARA LOS ROLES
@Repository
public interface RolRepositorio extends MongoRepository<Rol, String>{
    
}
