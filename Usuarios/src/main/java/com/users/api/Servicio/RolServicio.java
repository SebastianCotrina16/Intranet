package com.users.api.Servicio;

import com.users.api.Entidad.Rol;
import com.users.api.Repositorio.RolRepositorio;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolServicio {
    
    //METODOS PARA EL DOCUMENT DE ROL
    private final RolRepositorio rolrepositorio;    
    
    public void save(Rol rol){
        rolrepositorio.save(rol);        
    }
    
    public List<Rol> findAll(){
        return rolrepositorio.findAll();
    }
    
    public Optional<Rol> findById(String idrol){
        return rolrepositorio.findById(idrol);
    }
    
    public void deleteById(String idrol){
        rolrepositorio.deleteById(idrol);
    }
}
