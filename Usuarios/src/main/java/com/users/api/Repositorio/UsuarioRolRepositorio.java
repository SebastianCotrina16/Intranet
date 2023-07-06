/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.users.api.Repositorio;

import com.users.api.Entidad.UsuarioRol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//REPOSITORIO PARA USUARIOROL
@Repository
public interface UsuarioRolRepositorio extends MongoRepository<UsuarioRol, Long>{
    
}
