package fr.rent.front.api;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@ApplicationScoped
public class RentalPropertyApiClient {

    private static final String RENTAL_PROPERTIES_URL = "http://localhost:8081/rent-properties-api/rental-properties";

    private final HttpClient httpClient;

    @Inject
    public RentalPropertyApiClient() {
        this.httpClient = HttpClient.newHttpClient();
    }

    public String fetchRentalProperties() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(RENTAL_PROPERTIES_URL))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
