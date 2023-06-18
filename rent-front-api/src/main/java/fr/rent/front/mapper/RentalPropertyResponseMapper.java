package fr.rent.front.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rent.front.dto.RentalPropertyResponseDto;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.util.List;

@ApplicationScoped
public class RentalPropertyResponseMapper {

    private final ObjectMapper objectMapper;

    @Inject
    public RentalPropertyResponseMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public List<RentalPropertyResponseDto> mapToListResponse(String responseBody) throws IOException {
        return objectMapper.readValue(responseBody, new TypeReference<>() {
        });
    }

    public RentalPropertyResponseDto mapToResponse(String responseBody) throws IOException {
        return objectMapper.readValue(responseBody, new TypeReference<>() {
        });
    }
}
