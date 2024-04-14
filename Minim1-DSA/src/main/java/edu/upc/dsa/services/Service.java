package edu.upc.dsa.services;


import edu.upc.dsa.Manager;
import edu.upc.dsa.ManagerImpl;
import edu.upc.dsa.models.Dron;
import edu.upc.dsa.models.Piloto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Date;

@Api(value = "/dron", description = "Endpoint to Drones Service")
@Path("/dron")
public class Service {
    private Manager manager;

    public Service() {
        this.manager = ManagerImpl.getInstance();
        if (manager.size() == 0){
            Dron d1 = this.manager.añadirDron("1", "Dron 1", "Fabrica 1", "Modelo 1");
            d1.setHorasVuelo(20);
            Dron d2 = this.manager.añadirDron("2", "Dron 2", "Fabrica 2", "Modelo 2");
            d2.setHorasVuelo(40);
            Dron d3 = this.manager.añadirDron("3", "Dron 3", "Fabrica 3", "Modelo 3");
            d3.setHorasVuelo(60);
            Dron d4 = this.manager.añadirDron("4", "Dron 4", "Fabrica 4", "Modelo 4");
            d4.setHorasVuelo(80);

            Piloto p1 = this.manager.añadirPiloto("1", "Nombre 1", "Apellidos 1");
            p1.setHorasVuelo(80);
            Piloto p2 = this.manager.añadirPiloto("2", "Nombre 2", "Apellidos 2");
            p2.setHorasVuelo(60);
            Piloto p3 = this.manager.añadirPiloto("3", "Nombre 3", "Apellidos 3");
            p3.setHorasVuelo(40);
            Piloto p4 = this.manager.añadirPiloto("4", "Nombre 4", "Apellidos 4");
            p4.setHorasVuelo(20);
        }
    }
    @POST
    @ApiOperation(value = "Añade un dron", notes = "Añade un nuevo dron")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Dron añadido"),
            @ApiResponse(code = 500, message = "Error de validación")
    })
    @Path("/drons/{dronID}/{nombre}/{fabricante}/{modelo}")
    public Response createDron(@PathParam("dronID") String dronID, @PathParam("nombre") String nombre, @PathParam("fabricante") String fabricante, @PathParam("modelo") String modelo) {
        Dron newDron = this.manager.añadirDron(dronID, nombre, fabricante, modelo);
        return Response.status(201).entity(newDron).build();
    }
    @POST
    @ApiOperation(value = "Añade un piloto", notes = "Añade un nuevo piloto")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Piloto añadido"),
            @ApiResponse(code = 500, message = "Error de validación")
    })
    @Path("/pilots/{pilotoID}/{nombre}/{apellidos}")
    public Response createPiloto(@PathParam("pilotoID") String pilotoID, @PathParam("nombre") String nombre, @PathParam("apellidos") String apellidos) {
        Piloto newPiloto = this.manager.añadirPiloto(pilotoID, nombre, apellidos);
        return Response.status(201).entity(newPiloto).build();
    }
    @GET
    @ApiOperation(value = "Obtener lista de drones descendentemente por horas de vuelo", notes = "Listado de drones ordenado descendentemente por horas de vuelo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exito"),
            @ApiResponse(code = 404, message = "Lista no encontrada")
    })
    @Path("/drons/ordered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaDrones() {
        List<Dron> drones = this.manager.listarDronesPorHorasDeVuelo();
        GenericEntity<List<Dron>> entity = new GenericEntity<List<Dron>>(drones) {};
        return Response.status(201).entity(entity).build();
    }
    @GET
    @ApiOperation(value = "Obtener lista de pilotos descendentemente por horas de vuelo", notes = "Listado de pilotos ordenado descendentemente por horas de vuelo")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exito"),
            @ApiResponse(code = 404, message = "Lista no encontrada")
    })
    @Path("/pilots/ordered")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getListaPilotos() {
        List<Piloto> pilotos = this.manager.listarPilotosPorHorasDeVuelo();
        GenericEntity<List<Piloto>> entity = new GenericEntity<List<Piloto>>(pilotos) {};
        return Response.status(201).entity(entity).build();
    }
}