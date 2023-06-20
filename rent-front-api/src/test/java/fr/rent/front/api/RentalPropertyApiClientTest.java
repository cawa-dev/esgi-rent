//package fr.rent.front.api;
//
//import fr.rent.front.dto.request.RentalPropertyRequestDto;
//import fr.rent.front.exception.InvalidRequestRentalPropertyException;
//import fr.rent.front.exception.NotFoundRentalPropertyException;
//import fr.rent.front.mapper.RentalPropertyMapper;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.net.http.HttpClient;
//import java.net.http.HttpResponse;
//import java.net.http.HttpRequest;
//
//import static fr.rent.front.samples.RentalPropertyResponseDtoSample.oneRentalPropertyResponseDto;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class RentalPropertyApiClientTest {
//
//    @Mock
//    HttpClient httpClient;
//
//    @Mock
//    RentalPropertyMapper rentalPropertyMapper;
//
//    @Mock
//    HttpResponse httpResponse;
//
//    RentalPropertyApiClient client;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        client = new RentalPropertyApiClient(httpClient, rentalPropertyMapper);
//    }
//
//    @Test
//    void fetchRentalProperties_ReturnsExpectedResult() throws Exception {
//        String expectedResponse = "Expected Response";
//        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
//                .thenReturn(httpResponse);
//        when(httpResponse.body()).thenReturn(expectedResponse);
//
//        String actualResponse = client.fetchRentalProperties();
//
//        assertThat(actualResponse).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    void fetchRentalProperty_WhenSuccessful_ReturnsExpectedResult() throws Exception {
//        String expectedResponse = "Expected Response";
//        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
//                .thenReturn(httpResponse);
//        when(httpResponse.body()).thenReturn(expectedResponse);
//        when(httpResponse.statusCode()).thenReturn(200);
//
//        String actualResponse = client.fetchRentalProperty("1");
//
//        assertThat(actualResponse).isEqualTo(expectedResponse);
//    }
//
//    @Test
//    void fetchRentalProperty_WhenNotFound_ThrowsException() throws Exception {
//        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
//                .thenReturn(httpResponse);
//        when(httpResponse.statusCode()).thenReturn(404);
//
//        assertThatThrownBy(() -> client.fetchRentalProperty("1"))
//                .isInstanceOf(NotFoundRentalPropertyException.class);
//    }
//
//    @Test
//    void postRentalProperty_WhenSuccessful_DoesNotThrowException() throws Exception {
//        when(rentalPropertyMapper.mapToBody(any(RentalPropertyRequestDto.class))).thenReturn("{}");
//        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
//                .thenReturn(httpResponse);
//        when(httpResponse.statusCode()).thenReturn(201);
//
//        RentalPropertyRequestDto dto = new RentalPropertyRequestDto();
//        client.postRentalProperty(dto);
//
//        verify(httpClient, times(1)).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
//    }
//
//    @Test
//    void postRentalProperty_WhenInvalid_ThrowsException() throws Exception {
//        when(rentalPropertyMapper.mapToBody(any(RentalPropertyRequestDto.class))).thenReturn("{}");
//        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
//                .thenReturn(httpResponse);
//        when(httpResponse.statusCode()).thenReturn(400);
//
//        RentalPropertyRequestDto dto = oneRentalPropertyResponseDto();
//
//        assertThatThrownBy(() -> client.postRentalProperty(dto))
//                .isInstanceOf(InvalidRequestRentalPropertyException.class);
//    }
//