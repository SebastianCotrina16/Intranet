/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;


import Entities.Curso;
import Entities.Detalle;
import Entities.Matricula;
import Services.DetalleService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author LEGION
 */


@Path("/v1/detalles")
@Produces(MediaType.APPLICATION_JSON)
public class DetalleController {
    
    
    private final DetalleService detalleService;
    
    @Inject
    public DetalleController(DetalleService detalleService) {
        this.detalleService = detalleService;
    }
    
    @GET
    public List<Detalle> getDetalles() {
        return detalleService.getAllDetalles();
    }
    
    
    @GET
    @Path("/{id}")
    public Detalle getDetalle(@PathParam("id") int idDetalle) throws NotFoundException {
        return detalleService.getDetalleById(idDetalle);
    }
    
    @POST
    public Detalle createDetalle(@Valid DetalleDto detalleDto) {
        return detalleService.saveDetalle(detalleDto.toDetalle());
    }
    
    @PUT
    @Path("/{id}")
    public Detalle updateDetalle(@PathParam("id") int idDetalle, @Valid DetalleDto detalleDto) throws NotFoundException {
        return detalleService.updateDetalle(idDetalle, detalleDto.toDetalle());
    }
    
    @DELETE
    @Path("/{id}")
    public Response deleteDetalle(@PathParam("id") int idDetalle) throws NotFoundException {
        detalleService.deleteDetalle(idDetalle);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
    
    
    
    
    
    
    
     public static class DetalleDto {
       
        
        private int fkIdMatricula;;
        private Curso curso;
        private BigDecimal subTotal;
        
     public int getFkIdMatricula() {
        return fkIdMatricula;
    }

    public Curso getCurso() {
        return curso;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }
    public void setFkIdMatricula(int fkIdMatricula) {
        this.fkIdMatricula = fkIdMatricula;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }
        
        
        
      

        public Detalle toDetalle() {
            Detalle detalle = new Detalle();
            detalle.setFkIdMatricula(fkIdMatricula);
            detalle.setCurso(curso);
            detalle.setSubTotal(subTotal);
            
            
            
            return detalle;
        }
    }
}
