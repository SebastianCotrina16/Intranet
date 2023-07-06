/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Entities.Detalle;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

/**
 *
 * @author LEGION
 */
public interface DetalleService {
    
    Detalle getDetalleById(int idDetalle) throws NotFoundException;

    List<Detalle> getAllDetalles();

    Detalle updateDetalle(int idDetalle, Detalle detalle) throws NotFoundException;

    Detalle saveDetalle(Detalle detalle);

    void deleteDetalle(int idDetalle) throws NotFoundException;
    
    
}
