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

    private final RentalPropertyApiClient apiClient;
    private final RentalPropertyResponseMapper responseMapper;

    @Inject
    public RentalPropertyService(RentalPropertyApiClient apiClient, RentalPropertyResponseMapper responseMapper) {
        this.apiClient = apiClient;
        this.responseMapper = responseMapper;
    }


    public List<RentalPropertyResponseDto> getRentalProperties() {
        try {
            String responseBody = apiClient.fetchRentalProperties();
            return responseMapper.mapToResponse(responseBody);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
