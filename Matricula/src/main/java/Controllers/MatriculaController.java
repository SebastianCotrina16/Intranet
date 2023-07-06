/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controllers;
import Entities.Beca;
import Entities.Estado;
import Entities.Matricula;
import Entities.ProntoPago;
import Entities.Rabbitmessage;
import Entities.Semestre;
import Entities.Usuario;
import Services.MatriculaService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Past;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.text.SimpleDateFormat;
/**
 *
 * @author LEGION
 */

@Path("/v1/matriculas")
@Produces(MediaType.APPLICATION_JSON)
public class MatriculaController {
    
    
    private final MatriculaService matriculaService;

    @Inject
    public MatriculaController(MatriculaService matriculaService) {
        this.matriculaService = matriculaService;
    }

    @Inject
    @Channel("logs")
    Emitter<String> emitter;
    
    
    private void enviarMensajeRabbitMQ(String mensaje) {
    Rabbitmessage rabbitmessage = new Rabbitmessage();
    rabbitmessage.setTimestamp(new Date());
    rabbitmessage.setLevel("INFO");
    rabbitmessage.setMessage(mensaje);

    ObjectMapper mapper = new ObjectMapper();
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
    mapper.setDateFormat(dateFormat);

    String json;
    try {
        json = mapper.writeValueAsString(rabbitmessage);
    } catch (JsonProcessingException e) {
        json = "";
    }

    emitter.send(json);
}
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Matricula> getMatriculas() {
        enviarMensajeRabbitMQ("Se han listado todas las matrículas");
    return matriculaService.getAllMatriculas();
    }

     @GET
@Path("/{id}")
public Matricula getMatricula(@PathParam("id") int idMatricula) throws NotFoundException {
    try {
        enviarMensajeRabbitMQ("Se ha listado las matriculas en base al Id.");
        return matriculaService.getMatriculaById(idMatricula);
    } catch (NotFoundException e) {
        return null; 
    }
}

 @POST
public Matricula createMatricula(@Valid MatriculaDto matriculaDto) {
    Matricula matricula = null;
    try {
        matricula = matriculaService.saveMatricula(matriculaDto.toMatricula());
        
        int matriculaId = matricula.getIdMatricula();
        BigDecimal matriculaMonto = matricula.getTotal();
        String mensaje = "Se ha creado una nueva matrícula con ID: " + matriculaId + " y monto: " + matriculaMonto;

        // Enviar el mensaje a RabbitMQ
        enviarMensajeRabbitMQ(mensaje);
    } catch (Exception e) {
        // Maneja el error y retorna la respuesta apropiada (por ejemplo, un Response de error)
        e.printStackTrace();
        throw new WebApplicationException("Error al crear la matrícula.", Response.Status.INTERNAL_SERVER_ERROR);
    }
    return matricula;
}

    @PUT
@Path("/{id}")
public Matricula updateMatricula(@PathParam("id") int idMatricula, @Valid MatriculaDto matriculaDto) throws NotFoundException {
    try {
        // Intenta actualizar la matrícula
        Matricula matricula = matriculaService.updateMatricula(idMatricula, matriculaDto.toMatricula());

        // Si la matrícula se actualiza correctamente, envía el mensaje a RabbitMQ
        enviarMensajeRabbitMQ("Se ha modificado una matrícula.");

        return matricula;
    } catch (NotFoundException e) {
        // Maneja el error de NotFoundException y retorna la respuesta apropiada (por ejemplo, un Response de error)
        e.printStackTrace();
        throw new WebApplicationException("Matrícula no encontrada.", Response.Status.NOT_FOUND);
    } catch (Exception e) {
        // Maneja otros errores y retorna la respuesta apropiada (por ejemplo, un Response de error)
        e.printStackTrace();
        throw new WebApplicationException("Error al actualizar la matrícula.", Response.Status.INTERNAL_SERVER_ERROR);
    }
}

    @DELETE
@Path("/{id}")
public Response deleteMatricula(@PathParam("id") int idMatricula) throws NotFoundException {
    try {
        enviarMensajeRabbitMQ("Se ha eliminado una matrícula");
        matriculaService.deleteMatricula(idMatricula);
        return Response.status(Response.Status.NO_CONTENT).build();
    } catch (NotFoundException e) {
        // Maneja el error de NotFoundException y retorna la respuesta apropiada (por ejemplo, un Response de error)
        e.printStackTrace();
        throw new WebApplicationException("Matrícula no encontrada.", Response.Status.NOT_FOUND);
    } catch (Exception e) {
        // Maneja otros errores y retorna la respuesta apropiada (por ejemplo, un Response de error)
        e.printStackTrace();
        throw new WebApplicationException("Error al eliminar la matrícula.", Response.Status.INTERNAL_SERVER_ERROR);
    }
}

    public static class MatriculaDto {
    private Usuario usuario;
    private Semestre semestre;
    private ProntoPago prontoPago;
    private Beca beca;
    @Past
    private LocalDate fecha;
    private Estado estado;
    @Past
    private LocalDate vencimiento;
    private BigDecimal total;

    public Usuario getUsuario() {
        return usuario;
    }

    public Semestre getSemestre() {
        return semestre;
    }

    public ProntoPago getProntoPago() {
        return prontoPago;
    }

    public Beca getBeca() {
        return beca;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public Estado getEstado() {
        return estado;
    }

    public LocalDate getVencimiento() {
        return vencimiento;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setSemestre(Semestre semestre) {
        this.semestre = semestre;
    }

    public void setProntoPago(ProntoPago prontoPago) {
        this.prontoPago = prontoPago;
    }

    public void setBeca(Beca beca) {
        this.beca = beca;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public void setVencimiento(LocalDate vencimiento) {
        this.vencimiento = vencimiento;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Matricula toMatricula() {
        Matricula matricula = new Matricula();
        matricula.setUsuario(usuario);
        matricula.setSemestre(semestre);
        matricula.setProntoPago(prontoPago);
        matricula.setBeca(beca);
        matricula.setFecha(fecha);
        matricula.setEstado(estado);
        matricula.setVencimiento(vencimiento);
        matricula.setTotal(total);

        return matricula;
    }
}
    
    
    
}
