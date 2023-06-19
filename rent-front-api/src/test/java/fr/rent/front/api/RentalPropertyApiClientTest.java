package fr.rent.front.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RentalPropertyApiClientTest {

    @InjectMocks
    RentalPropertyApiClient rentalPropertyApiClient;

    @Mock
    HttpClient httpClient;

    @Mock
    HttpResponse<String> httpResponse;

    @Test
    void shouldFetchRentalProperties() throws IOException, InterruptedException {
        // GIVEN
        String responseBody = "response";
        when(httpResponse.body()).thenReturn(responseBody);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        // WHEN
        String response = rentalPropertyApiClient.fetchRentalProperties();

        // THEN
        assertThat(response).isEqualTo(responseBody);
    }

    @Test
    void shouldFetchRentalProperty() throws IOException, InterruptedException {
        // GIVEN
        String id = "1";
        String responseBody = "response";
        when(httpResponse.body()).thenReturn(responseBody);
        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class))).thenReturn(httpResponse);

        // WHEN
        String response = rentalPropertyApiClient.fetchRentalProperty(id);

        // THEN
        assertThat(response).isEqualTo(responseBody);
    }
}
