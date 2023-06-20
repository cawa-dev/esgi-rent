package fr.rent.front.service;

import fr.rent.front.api.RentalPropertyApiClient;
import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.rent.front.dto.response.RentalPropertyResponseDto;
import fr.rent.front.mapper.RentalPropertyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RentalPropertyService {

    @Inject
    private RentalPropertyApiClient apiClient;

    @Inject
    private RentalPropertyMapper responseMapper;

    public List<RentalPropertyResponseDto> getRentalProperties() {
        String responseBody = apiClient.fetchRentalProperties();
        return responseMapper.mapToListResponse(responseBody);
    }

    public RentalPropertyResponseDto getRentalProperty(String id) {
        String responseBody = apiClient.fetchRentalProperty(id);
        return responseMapper.mapToResponse(responseBody);
    }

    public void createRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto) {
        apiClient.postRentalProperty(rentalPropertyRequestDto);
    }

    public void updateRentalProperty(String id, RentalPropertyRequestDto rentalPropertyRequestDto) {
        apiClient.putRentalProperty(id, rentalPropertyRequestDto);
    }

    public void patchRentalProperty(String id, RentalPropertyRequestDtoPatch rentalPropertyRequestDtoPatch) {
        apiClient.patchRentalProperty(id, rentalPropertyRequestDtoPatch);
    }

    public void deleteRentalProperty(String id) {
        apiClient.deleteRentalProperty(id);
    }
}
