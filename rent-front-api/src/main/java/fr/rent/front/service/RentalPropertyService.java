package fr.rent.front.service;

import fr.rent.front.api.RentalPropertyApiClient;
import fr.rent.front.dto.RentalPropertyResponseDto;
import fr.rent.front.exception.NotFoundRentalPropertyException;
import fr.rent.front.mapper.RentalPropertyResponseMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class RentalPropertyService {

    @Inject
    private RentalPropertyApiClient apiClient;

    @Inject
    private RentalPropertyResponseMapper responseMapper;

    public List<RentalPropertyResponseDto> getRentalProperties() {
        try {
            String responseBody = apiClient.fetchRentalProperties();
            return responseMapper.mapToListResponse(responseBody);
        } catch (IOException | InterruptedException exception) {
            exception.printStackTrace();
        }
        return List.of();
    }

    public RentalPropertyResponseDto getRentalProperty(String id) {
        try {
            String responseBody = apiClient.fetchRentalProperty(id);
            return responseMapper.mapToResponse(responseBody);
        } catch (IOException | InterruptedException exception) {
            throw new NotFoundRentalPropertyException("Le bien immobilier avec l'id : %s est introuvable".formatted(id));
        }
    }
}
