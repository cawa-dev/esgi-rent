package fr.rent.front.service;

import fr.rent.front.api.RentalCarApiClient;
import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.dto.response.RentalCarResponseDto;
import fr.rent.front.mapper.RentalCarMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RentalCarService {

    @Inject
    private RentalCarApiClient apiClient;

    @Inject
    private RentalCarMapper responseMapper;

    public List<RentalCarResponseDto> getRentalCars() {
        String responseBody = apiClient.fetchRentalCars();
        return responseMapper.mapToListResponse(responseBody);
    }

    public RentalCarResponseDto getRentalCar(String id) {
        String responseBody = apiClient.fetchRentalCar(id);
        return responseMapper.mapToResponse(responseBody);
    }

    public void createRentalCar(RentalCarRequestDto rentalCarRequestDto) {
        apiClient.postRentalCar(rentalCarRequestDto);
    }

    public void updateRentalCar(String id, RentalCarRequestDto rentalCarRequestDto) {
        apiClient.putRentalCar(id, rentalCarRequestDto);
    }

    public void patchRentalCar(String id, RentalCarRequestDtoPatch rentalCarRequestDtoPatch) {
        apiClient.patchRentalCar(id, rentalCarRequestDtoPatch);
    }

    public void deleteRentalCar(String id) {
        apiClient.deleteRentalCar(id);
    }
}
