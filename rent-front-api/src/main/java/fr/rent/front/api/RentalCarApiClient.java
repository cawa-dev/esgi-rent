package fr.rent.front.api;

import fr.rent.front.dto.request.RentalCarRequestDto;
import fr.rent.front.dto.request.patch.RentalCarRequestDtoPatch;
import fr.rent.front.exception.InvalidRequestRentalCarException;
import fr.rent.front.exception.NotFoundRentalCarException;
import fr.rent.front.mapper.RentalCarMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.net.http.HttpRequest.BodyPublishers;
import static java.net.http.HttpRequest.newBuilder;

@ApplicationScoped
public class RentalCarApiClient {

    @Inject
    @ConfigProperty(name = "rental-cars-api-host")
    private String rental_cars_api_host;

    private final HttpClient httpClient;

    private RentalCarMapper rentalCarMapper;

    @Inject
    public RentalCarApiClient() {
        this.httpClient = HttpClient.newHttpClient();
        this.rentalCarMapper = new RentalCarMapper();
    }

    public String fetchRentalCars() {
        HttpRequest request = newBuilder()
                .uri(URI.create(rental_cars_api_host))
                .GET()
                .build();

        try {
            return httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public String fetchRentalCar(String id) {
        HttpRequest request = newBuilder()
                .uri(URI.create(rental_cars_api_host + "/%s".formatted(id)))
                .GET()
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new NotFoundRentalCarException("Le véhicule avec l'id : %s est introuvable".formatted(id));
            }

            return response.body();
        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void postRentalCar(RentalCarRequestDto rentalCarRequestDto) {
        var rentalCarRequestDtoMapped = rentalCarMapper.mapToBodyRequest(rentalCarRequestDto);

        HttpRequest request = newBuilder()
                .uri(URI.create(rental_cars_api_host))
                .header("Content-Type", "application/json")
                .POST(BodyPublishers.ofString(rentalCarRequestDtoMapped))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 201) {
                throw new InvalidRequestRentalCarException("La requête est invalide !");
            }

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void putRentalCar(String id, RentalCarRequestDto rentalCarRequestDto) {
        var rentalCarRequestDtoMapped = rentalCarMapper.mapToBodyRequest(rentalCarRequestDto);

        HttpRequest request = newBuilder()
                .uri(URI.create(rental_cars_api_host + "/%s".formatted(id)))
                .header("Content-Type", "application/json")
                .PUT(BodyPublishers.ofString(rentalCarRequestDtoMapped))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() != 200) {
                throw new InvalidRequestRentalCarException("La requête est invalide !");
            }

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void patchRentalCar(String id, RentalCarRequestDtoPatch rentalCarRequestDtoPatch) {
        var rentalCarRequestPatchDtoMapped = rentalCarMapper.mapToBodyRequestPatch(rentalCarRequestDtoPatch);

        HttpRequest request = newBuilder()
                .uri(URI.create(rental_cars_api_host + "/%s".formatted(id)))
                .header("Content-Type", "application/json")
                .method("PATCH", BodyPublishers.ofString(rentalCarRequestPatchDtoMapped))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            switch (response.statusCode()) {
                case 404 ->
                        throw new NotFoundRentalCarException("Le véhicule avec l'id : %s est introuvable".formatted(id));
                case 400 -> throw new InvalidRequestRentalCarException("La requête est invalide !");
            }

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void deleteRentalCar(String id) {
        HttpRequest request = newBuilder()
                .uri(URI.create(rental_cars_api_host + "/%s".formatted(id)))
                .DELETE()
                .build();

        try {
            httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException exception) {
            throw new RuntimeException(exception);
        }
    }
}
