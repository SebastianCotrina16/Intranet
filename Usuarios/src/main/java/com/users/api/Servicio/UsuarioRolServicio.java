package com.users.api.Servicio;

import com.users.api.Entidad.UsuarioRol;
import com.users.api.Repositorio.UsuarioRolRepositorio;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioRolServicio {
    
    //METODOS PARA LA ASIGNACION DE ROL AL USUARIO
    private final UsuarioRolRepositorio userrolrepositorio;
        
        public void save (UsuarioRol userrol){
            userrolrepositorio.save(userrol);
        }
        
        public List<UsuarioRol> findAll(){
            return userrolrepositorio.findAll();
        }
        
        public Optional<UsuarioRol> findById(Long idur){
            return userrolrepositorio.findById(idur);
        }
        
        public void deleteById(Long idur){            
            userrolrepositorio.deleteById(idur);
        }
}
