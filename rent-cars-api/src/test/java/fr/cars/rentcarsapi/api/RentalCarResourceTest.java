package fr.cars.rentcarsapi.api;

import fr.cars.rentcarsapi.dto.request.RentalCarRequestDto;
import fr.cars.rentcarsapi.dto.response.RentalCarResponseDto;
import fr.cars.rentcarsapi.service.RentalCarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static fr.cars.rentcarsapi.samples.RentalCarDtoSample.*;
import static fr.cars.rentcarsapi.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalCarResource.class)
class RentalCarResourceTest {

    @Value("classpath:/json/rentalCars.json")
    Resource rentalCars;

    @Value("classpath:/json/rentalCar.json")
    Resource rentalCar;

    @Value("classpath:/json/rentalCarRequest.json")
    Resource rentalCarRequest;

    @Value("classpath:/json/invalidRentalCarRequest.json")
    Resource invalidRentalCarRequest;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RentalCarService rentalCarService;

    @Test
    void shouldGetRentalCars() throws Exception {
        // GIVEN
        List<RentalCarResponseDto> rentalCarsResponseList = rentalCarsResponseList();

        // WHEN
        when(rentalCarService.getRentalCars()).thenReturn(rentalCarsResponseList);

        mockMvc.perform(get("/rent-cars-api/rental-cars"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalCars)));

        // THEN
        verify(rentalCarService).getRentalCars();
        verifyNoMoreInteractions(rentalCarService);
    }

    @Test
    void shouldGetRentalCarById() throws Exception {
        // GIVEN
        RentalCarResponseDto rentalCarResponseDto = oneRentalCarResponse();
        int id = 1;

        // WHEN
        when(rentalCarService.getRentalCar((id))).thenReturn(rentalCarResponseDto);

        mockMvc.perform(get("/rent-cars-api/rental-cars/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalCar)));

        // THEN
        verify(rentalCarService).getRentalCar(id);
        verifyNoMoreInteractions(rentalCarService);
    }

    @Test
    void shouldCreateRentalCar() throws Exception {
        // GIVEN
        RentalCarRequestDto rentalCarRequestDto = oneRentalCarRequest();

        // WHEN
        doNothing().when(rentalCarService).createRentalCar(rentalCarRequestDto);

        mockMvc.perform(post("/rent-cars-api/rental-cars")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalCarRequest)))
                .andExpect(status().isCreated());

        // THEN
        verify(rentalCarService).createRentalCar(rentalCarRequestDto);
        verifyNoMoreInteractions(rentalCarService);
    }

    @Test
    void shouldReturnBadRequestWhenSendInvalidRequestBodyToCreateRentalCar() throws Exception {
        // WHEN
        mockMvc.perform(post("/rent-cars-api/rental-cars")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(invalidRentalCarRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\": \"La requête envoyée est invalide\"}"));

        // THEN
        verifyNoInteractions(rentalCarService);
    }
}
