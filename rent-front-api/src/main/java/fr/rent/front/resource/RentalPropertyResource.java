package fr.rent.front.resource;

import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.service.RentalPropertyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/rental-properties")
public class RentalPropertyResource {

    private final RentalPropertyService rentalPropertyService;

    @Inject
    public RentalPropertyResource(RentalPropertyService rentalPropertyService) {
        this.rentalPropertyService = rentalPropertyService;
    }

    @GET
    public Response getRentalProperties() {
        return Response.ok()
                .entity(rentalPropertyService.getRentalProperties())
                .build();
    }

    @Path("/{id}")
    @GET
    public Response getRentalProperty(@PathParam("id") String id) {
        return Response.ok()
                .entity(rentalPropertyService.getRentalProperty(id))
                .build();
    }

    @POST
    public Response createRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto) {
        rentalPropertyService.createRentalProperty(rentalPropertyRequestDto);
        return Response.status(201)
                .build();
    }

    @Path("/{id}")
    @PUT
    public Response updateRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto, @PathParam("id") String id) {
        rentalPropertyService.updateRentalProperty(id, rentalPropertyRequestDto);
        return Response.status(200)
                .build();
    }
}
