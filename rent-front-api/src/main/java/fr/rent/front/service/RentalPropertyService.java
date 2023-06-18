package fr.rent.front.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.rent.front.dto.RentalPropertyResponseDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@ApplicationScoped
public class RentalPropertyService {

    private static final String RENTAL_PROPERTIES_URL = "http://localhost:8081/rent-properties-api/rental-properties";

    public List<RentalPropertyResponseDto> getRentalProperties() {
        HttpClient httpClient = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RENTAL_PROPERTIES_URL))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            int statusCode = response.statusCode();
            String responseBody = response.body();

            if (statusCode == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                List<RentalPropertyResponseDto> rentalProperties = objectMapper.readValue(
                        responseBody,
                        new TypeReference<>() {
                        }
                );
                return rentalProperties;
            } else {
                System.err.println("Error response received. Status code: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
