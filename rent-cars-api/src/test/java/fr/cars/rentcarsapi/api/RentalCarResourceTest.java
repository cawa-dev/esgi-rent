package fr.cars.rentcarsapi.api;

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

import static fr.cars.rentcarsapi.samples.RentalCarDtoSample.rentalCarsResponseList;
import static fr.cars.rentcarsapi.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalCarResource.class)
class RentalCarResourceTest {

    @Value("classpath:/json/rentalCars.json")
    Resource rentalCars;

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
}
