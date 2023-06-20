package fr.rent.front.resource;

import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.service.RentalCarService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/rental-cars")
public class RentalCarResource {

    private final RentalCarService rentalCarService;

    @Inject
    public RentalCarResource(RentalCarService rentalCarService) {
        this.rentalCarService = rentalCarService;
    }

    @GET
    public Response getRentalCars() {
        return Response.ok()
                .entity(rentalCarService.getRentalCars())
                .build();
    }

    @Path("/{id}")
    @GET
    public Response getRentalCar(@PathParam("id") String id) {
        return Response.ok()
                .entity(rentalCarService.getRentalCar(id))
                .build();
    }

    @POST
    public Response createRentalCary(RentalCarRequestDto rentalCarRequestDto) {
        rentalCarService.createRentalCar(rentalCarRequestDto);
        return Response.status(201)
                .build();
    }

    @Path("/{id}")
    @PUT
    public Response updateRentalCar(RentalCarRequestDto rentalCarRequestDto, @PathParam("id") String id) {
        rentalCarService.updateRentalCar(id, rentalCarRequestDto);
        return Response.status(200)
                .build();
    }

    @Path("/{id}")
    @PATCH
    public Response patchRentalCar(RentalCarRequestDtoPatch rentalCarRequestDtoPatch, @PathParam("id") String id) {
        rentalCarService.patchRentalCar(id, rentalCarRequestDtoPatch);
        return Response.status(200)
                .build();
    }

    @Path("/{id}")
    @DELETE
    public Response deleteRentalCar(@PathParam("id") String id) {
        rentalCarService.deleteRentalCar(id);
        return Response.status(204)
                .build();
    }
}
