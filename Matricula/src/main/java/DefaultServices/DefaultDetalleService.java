/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DefaultServices;
import Entities.Detalle;
import Repositories.DetalleRepository;
import Services.DetalleService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author LEGION
 */

@ApplicationScoped
public class DefaultDetalleService implements DetalleService{
    
    private final DetalleRepository detalleRepository;
    @Inject
    public DefaultDetalleService(DetalleRepository detalleRepository) {
        this.detalleRepository = detalleRepository;
    }
    
    @Override
    public Detalle getDetalleById(int idDetalle) throws NotFoundException {
        Long detalleId = Long.valueOf(idDetalle); // Convertir el valor de int a Long
        Optional<Detalle> optionalDetalle = detalleRepository.findByIdOptional(detalleId);
        return optionalDetalle.orElseThrow(() -> new NotFoundException("The detalle doesn't exist"));
    }
    
    
    @Override
    public List<Detalle> getAllDetalles() {
        return detalleRepository.listAll();
    }
    
    @Transactional
    @Override
    public Detalle updateDetalle(int idDetalle, Detalle detalle) throws NotFoundException {
        Detalle existingDetalle = getDetalleById(idDetalle);
        existingDetalle.setFkIdMatricula(detalle.getFkIdMatricula());
        existingDetalle.setCurso(detalle.getCurso());
        existingDetalle.setSubTotal(detalle.getSubTotal());
         
        detalleRepository.persist(existingDetalle);
        return existingDetalle;
    }
    
    @Transactional
    @Override
    public Detalle saveDetalle(Detalle detalle) {
        detalleRepository.persistAndFlush(detalle);
        return detalle;
    }
    
    @Transactional
    @Override
    public void deleteDetalle(int idDetalle) throws NotFoundException {
        detalleRepository.delete(getDetalleById(idDetalle));
    }
    
    
}
