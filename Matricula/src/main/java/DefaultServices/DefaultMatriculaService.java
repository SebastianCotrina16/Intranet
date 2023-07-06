/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DefaultServices;
import Entities.Matricula;
import Repositories.MatriculaRepository;
import Services.MatriculaService;
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
public class DefaultMatriculaService  implements MatriculaService{
    
    private final MatriculaRepository matriculaRepository;
    
    @Inject
    public DefaultMatriculaService(MatriculaRepository matriculaRepository) {
        this.matriculaRepository = matriculaRepository;
    }
    
    
    @Override
    public Matricula getMatriculaById(int idMatricula) throws NotFoundException {
        Long matriculaId = Long.valueOf(idMatricula); // Convertir el valor de int a Long
        Optional<Matricula> optionalMatricula = matriculaRepository.findByIdOptional(matriculaId);
        return optionalMatricula.orElseThrow(() -> new NotFoundException("The matricula doesn't exist"));
    }
    
    
    @Override
    public List<Matricula> getAllMatriculas() {
        return matriculaRepository.listAll();
    }
    
    
    @Transactional
    @Override
    public Matricula updateMatricula(int idMatricula, Matricula matricula) throws NotFoundException {
        Matricula existingMatricula = getMatriculaById(idMatricula);
        existingMatricula.setUsuario(matricula.getUsuario());
        existingMatricula.setSemestre(matricula.getSemestre());
        existingMatricula.setProntoPago(matricula.getProntoPago());
        existingMatricula.setBeca(matricula.getBeca());
        existingMatricula.setFecha(matricula.getFecha());
        existingMatricula.setEstado(matricula.getEstado());
        existingMatricula.setVencimiento(matricula.getVencimiento());
        existingMatricula.setTotal(matricula.getTotal());
        matriculaRepository.persist(existingMatricula);
        return existingMatricula;
    }
    
    @Transactional
    @Override
    public Matricula saveMatricula(Matricula matricula) {
        matriculaRepository.persistAndFlush(matricula);
        return matricula;
    }
    
    @Transactional
    @Override
    public void deleteMatricula(int idMatricula) throws NotFoundException {
        matriculaRepository.delete(getMatriculaById(idMatricula));
    }
    
}
