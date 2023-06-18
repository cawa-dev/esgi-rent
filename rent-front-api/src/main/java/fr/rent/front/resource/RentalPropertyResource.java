package fr.rent.front.resource;

import fr.rent.front.dto.RentalPropertyResponseDto;
import fr.rent.front.service.RentalPropertyService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;

@Path("/rental-properties")
public class RentalPropertyResource {

    private final RentalPropertyService rentalPropertyService;

    @Inject
    public RentalPropertyResource(RentalPropertyService rentalPropertyService) {
        this.rentalPropertyService = rentalPropertyService;
    }

    @GET
    public List<RentalPropertyResponseDto> getRentalProperties() {
        return rentalPropertyService.getRentalProperties();
    }

    @GET
    @Path("/{id}")
    public RentalPropertyResponseDto getRentalProperty(@PathParam("id") String id) {
        return rentalPropertyService.getRentalProperty(id);
    }
}
