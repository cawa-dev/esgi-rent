package fr.rent.front.service;

import fr.rent.front.api.RentalPropertyApiClient;
import fr.rent.front.dto.RentalPropertyResponseDto;
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
            return responseMapper.mapToResponse(responseBody);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return List.of();
    }
}
