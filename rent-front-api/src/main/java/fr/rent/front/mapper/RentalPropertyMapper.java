package fr.rent.front.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.dto.response.RentalPropertyResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;

@ApplicationScoped
public class RentalPropertyMapper {

    private final ObjectMapper objectMapper;

    @Inject
    public RentalPropertyMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public List<RentalPropertyResponseDto> mapToListResponse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException(jsonProcessingException);
        }
    }

    public RentalPropertyResponseDto mapToResponse(String responseBody) {
        try {
            return objectMapper.readValue(responseBody, new TypeReference<>() {
            });
        } catch (JsonProcessingException jsonProcessingException) {
            throw new RuntimeException(jsonProcessingException);
        }
    }

    public String mapToBody(RentalPropertyRequestDto rentalPropertyRequestDto) {
        try {
            return objectMapper.writeValueAsString(rentalPropertyRequestDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
