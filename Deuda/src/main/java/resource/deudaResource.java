/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resource;

import entidad.deuda;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import org.bson.types.ObjectId;
import repository.deudaRepository;

/**
 *
 * @author LEGION
 */

@Path("/deudas")
public class deudaResource {
    
    @Inject
    deudaRepository repository;
    
    
    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") String id){
    deuda deuda = repository.findById(new ObjectId(id));
    
    return Response.ok(deuda).build();
    }
    
    @GET
    public Response get(){
            
     return Response.ok(repository.listAll()).build();
    }
    
    @GET
    @Path("/search/{idmatricula}")
    public Response search(@PathParam("idmatricula") int idmatricula){
     deuda deuda = repository.findByIdmatricula(idmatricula);
        return deuda != null ? Response.ok(deuda).build() : Response.status(Response.Status.NOT_FOUND).build();
    }
    
    @POST
    public Response create (deuda deuda) throws URISyntaxException{
    repository.persist(deuda);    
    
    return Response.created(new URI("/"+ deuda.id)).build();
    }
    
    
    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") String id, deuda deuda){
    deuda.id = new ObjectId(id);
    repository.update(deuda);
       
    return Response.ok(deuda).build();
    }
    
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id){
        deuda deuda = repository.findById(new ObjectId(id));
        repository.delete(deuda);
 
      
        return Response.noContent().build();
    }
    
    
    
}
