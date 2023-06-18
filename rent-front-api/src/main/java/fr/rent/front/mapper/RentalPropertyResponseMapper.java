package fr.rent.front.mapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rent.front.dto.RentalPropertyResponseDto;

import java.io.IOException;
import java.util.List;

public class RentalPropertyResponseMapper {

    private final ObjectMapper objectMapper;

    public RentalPropertyResponseMapper() {
        this.objectMapper = new ObjectMapper();
    }

    public List<RentalPropertyResponseDto> mapToResponse(String responseBody) throws IOException {
        return objectMapper.readValue(responseBody, new TypeReference<>() {});
    }
}