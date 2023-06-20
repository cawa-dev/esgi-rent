package fr.rent.front.api;

import fr.rent.front.dto.request.RentalPropertyRequestDto;
import fr.rent.front.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.rent.front.exception.InvalidRequestRentalPropertyException;
import fr.rent.front.exception.NotFoundRentalPropertyException;
import fr.rent.front.mapper.RentalPropertyMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

@ApplicationScoped
public class RentalPropertyApiClient {

    private static final String GLOBAL_RENTAL_PROPERTIES_API = "http://localhost:8081/rent-properties-api/rental-properties";

    private final HttpClient httpClient;

    private RentalPropertyMapper rentalPropertyMapper;

    @Inject
    public RentalPropertyApiClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.rentalPropertyMapper = new RentalPropertyMapper();
    }

    public String fetchRentalProperties() {
        HttpRequest request = newBuilder()
                .uri(URI.create(GLOBAL_RENTAL_PROPERTIES_API))
                .GET()
                .build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String fetchRentalProperty(String id) {
        HttpRequest request = newBuilder()
                .uri(URI.create(GLOBAL_RENTAL_PROPERTIES_API + "/%s".formatted(id)))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new NotFoundRentalPropertyException("Le bien immobilier avec l'id : %s est introuvable".formatted(id));
            }

            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void postRentalProperty(RentalPropertyRequestDto rentalPropertyRequestDto) {
        var rentalPropertyRequestDtoMapped = rentalPropertyMapper.mapToBodyRequest(rentalPropertyRequestDto);

        HttpRequest request = newBuilder()
                .uri(URI.create(GLOBAL_RENTAL_PROPERTIES_API))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(rentalPropertyRequestDtoMapped))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 201) {
                throw new InvalidRequestRentalPropertyException("La requête est invalide !");
            }

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void putRentalProperty(String id, RentalPropertyRequestDto rentalPropertyRequestDto) {
        var rentalPropertyRequestDtoMapped = rentalPropertyMapper.mapToBodyRequest(rentalPropertyRequestDto);

        HttpRequest request = newBuilder()
                .uri(URI.create(GLOBAL_RENTAL_PROPERTIES_API + "/%s".formatted(id)))
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(rentalPropertyRequestDtoMapped))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new InvalidRequestRentalPropertyException("La requête est invalide !");
            }

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void patchRentalProperty(String id, RentalPropertyRequestDtoPatch rentalPropertyRequestDtoPatch) {
        var rentalPropertyRequestPatchDtoMapped = rentalPropertyMapper.mapToBodyRequestPatch(rentalPropertyRequestDtoPatch);

        HttpRequest request = newBuilder()
                .uri(URI.create(GLOBAL_RENTAL_PROPERTIES_API + "/%s".formatted(id)))
                .header("Content-Type", "application/json")
                .method("PATCH", BodyPublishers.ofString(rentalPropertyRequestPatchDtoMapped))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 404 ->
                        throw new NotFoundRentalPropertyException("Le bien immobilier avec l'id : %s est introuvable".formatted(id));
                case 400 -> throw new InvalidRequestRentalPropertyException("La requête est invalide !");
            }

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteRentalProperty(String id) {
        HttpRequest request = newBuilder()
                .uri(URI.create(GLOBAL_RENTAL_PROPERTIES_API + "/%s".formatted(id)))
                .DELETE()
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}
