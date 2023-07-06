/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entidad.deuda;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

/**
 *
 * @author LEGION
 */
@ApplicationScoped
public class deudaRepository implements PanacheMongoRepository<deuda>{
    
    
     public deuda findByIdmatricula(int idmatricula){
    return find("idmatricula", idmatricula).firstResult();
    }
    
    public List<deuda> findOrderedIdmatricula(){
    return listAll(Sort.by("idmatricula"));
   
    }
    
}
