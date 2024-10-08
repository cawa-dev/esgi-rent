package fr.properties.rentpropertiesapi.api;

import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.request.patch.RentalPropertyRequestDtoPatch;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.service.RentalPropertyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static fr.properties.rentpropertiesapi.samples.RentalPropertyDtoSample.*;
import static fr.properties.rentpropertiesapi.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalPropertyResource.class)
class RentalPropertyResourceTest {

    @Value("classpath:/json/rentalProperties.json")
    Resource rentalProperties;

    @Value("classpath:/json/rentalProperty.json")
    Resource rentalProperty;

    @Value("classpath:/json/rentalPropertyRequest.json")
    Resource rentalPropertyRequest;

    @Value("classpath:/json/rentalPropertyRequestPatch.json")
    Resource rentalPropertyRequestPatch;

    @Value("classpath:/json/invalidRentalPropertyRequest.json")
    Resource invalidRentalPropertyRequest;

    @Value("classpath:/json/invalidRentalPropertyRequestPatch.json")
    Resource invalidRentalPropertyRequestPatch;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    RentalPropertyService rentalPropertyService;

    @Test
    void shouldGetRentalProperties() throws Exception {
        // GIVEN
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyResponseList();

        // WHEN
        when(rentalPropertyService.getRentalProperties()).thenReturn(rentalPropertyResponseList);

        mockMvc.perform(get("/rent-properties-api/rental-properties"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperties)));

        // THEN
        verify(rentalPropertyService).getRentalProperties();
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldGetRentalPropertyById() throws Exception {
        // GIVEN
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();
        int id = 1;

        // WHEN
        when(rentalPropertyService.getRentalProperty((id))).thenReturn(rentalPropertyResponseDto);

        mockMvc.perform(get("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperty)));

        // THEN
        verify(rentalPropertyService).getRentalProperty(id);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldCreateRentalProperty() throws Exception {
        // GIVEN
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();

        // WHEN
        doNothing().when(rentalPropertyService).createRentalProperty(rentalPropertyRequestDto);

        mockMvc.perform(post("/rent-properties-api/rental-properties")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isCreated());

        // THEN
        verify(rentalPropertyService).createRentalProperty(rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldReturnBadRequestWhenSendInvalidRequestBodyToCreateRentalProperty() throws Exception {
        // WHEN
        mockMvc.perform(post("/rent-properties-api/rental-properties")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(invalidRentalPropertyRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\": \"La requête envoyée est invalide\"}"));

        // THEN
        verifyNoInteractions(rentalPropertyService);
    }

    @Test
    void shouldUpdateRentalProperty() throws Exception {
        // GIVEN
        int id = 1;
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();

        // WHEN
        doNothing().when(rentalPropertyService).updateRentalProperty(id, rentalPropertyRequestDto);

        // THEN
        mockMvc.perform(put("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isOk());

        // THEN
        verify(rentalPropertyService).updateRentalProperty(id, rentalPropertyRequestDto);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldPatchRentalProperty() throws Exception {
        // GIVEN
        int id = 1;
        RentalPropertyRequestDtoPatch rentalPropertyRequestDtoPatch = oneRentalPropertyPatchRequest();

        // WHEN
        mockMvc.perform(patch("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequestPatch)))
                .andExpect(status().isOk());

        // THEN
        verify(rentalPropertyService).patchRentalProperty(id, rentalPropertyRequestDtoPatch);
        verifyNoMoreInteractions(rentalPropertyService);
    }

    @Test
    void shouldReturnBadRequestWhenSendInvalidRequestBodyToPatchRentalProperty() throws Exception {
        // GIVEN
        int id = 0;

        // WHEN
        mockMvc.perform(patch("/rent-properties-api/rental-properties/{id}", id)
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(invalidRentalPropertyRequestPatch)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\": \"La requête envoyée est invalide\"}"));

        // THEN
        verifyNoInteractions(rentalPropertyService);
    }

    @Test
    void shouldDeleteRentalProperty() throws Exception {
        // GIVEN
        int id = 0;

        // WHEN
        doNothing().when(rentalPropertyService).deleteRentalProperty(id);

        mockMvc.perform(delete("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isNoContent());

        // THEN
        verify(rentalPropertyService).deleteRentalProperty(id);
        verifyNoMoreInteractions(rentalPropertyService);
    }
}
