/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.users.api.Repositorio;

import com.users.api.Entidad.Usuarios;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//REPOSITORIO PARA USUARIOS
@Repository
public interface UsuarioRepositorio extends MongoRepository<Usuarios, String>{
    
}
