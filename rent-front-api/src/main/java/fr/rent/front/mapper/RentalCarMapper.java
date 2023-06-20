package fr.rent.front.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.dto.response.RentalCarResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RentalCarMapper {

    private final ObjectMapper objectMapper;

    @Inject
    public RentalCarMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public List<RentalCarResponseDto> mapToListResponse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException(jsonProcessingException);
        }
    }

    public RentalCarResponseDto mapToResponse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException(jsonProcessingException);
        }
    }

    public String mapToBodyRequest(RentalCarRequestDto rentalCarRequestDto) {
        try {
            return objectMapper.writeValueAsString(rentalCarRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public String mapToBodyRequestPatch(RentalCarRequestDtoPatch rentalCarRequestDtoPatch) {
        try {
            return objectMapper.writeValueAsString(rentalCarRequestDtoPatch);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
