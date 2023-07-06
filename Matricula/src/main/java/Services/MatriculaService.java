/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Entities.Matricula;
import jakarta.ws.rs.NotFoundException;
import java.util.List;

/**
 *
 * @author LEGION
 */
public interface MatriculaService {
    
    Matricula getMatriculaById(int idMatricula) throws NotFoundException;

    List<Matricula> getAllMatriculas();

    Matricula updateMatricula(int idMatricula, Matricula matricula) throws NotFoundException;

    Matricula saveMatricula(Matricula matricula);

    void deleteMatricula(int idMatricula) throws NotFoundException;
    
}
