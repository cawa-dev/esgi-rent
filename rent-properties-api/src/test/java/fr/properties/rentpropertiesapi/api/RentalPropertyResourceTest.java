package fr.properties.rentpropertiesapi.api;

import java.util.List;
import java.util.Optional;

import fr.properties.rentpropertiesapi.domain.RentalPropertyEntity;
import fr.properties.rentpropertiesapi.dto.request.RentalPropertyRequestDto;
import fr.properties.rentpropertiesapi.dto.response.RentalPropertyResponseDto;
import fr.properties.rentpropertiesapi.mapper.RentalPropertyDtoMapper;
import fr.properties.rentpropertiesapi.repository.RentalPropertyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.test.web.servlet.MockMvc;

import static fr.properties.rentpropertiesapi.samples.RentalPropertyDtoSample.*;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.oneRentalPropertyEntity;
import static fr.properties.rentpropertiesapi.samples.RentalPropertyEntitySample.rentalPropertyEntities;
import static fr.properties.rentpropertiesapi.utils.TestUtils.readResource;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RentalPropertyResource.class)
class RentalPropertyResourceTest {

    @Value("classpath:/json/rentalProperties.json")
    private Resource rentalProperties;

    @Value("classpath:/json/rentalProperty.json")
    private Resource rentalProperty;

    @Value("classpath:/json/rentalPropertyRequest.json")
    private Resource rentalPropertyRequest;

    @Value("classpath:/json/invalidRentalPropertyRequest.json")
    private Resource invalidRentalPropertyRequest;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalPropertyRepository rentalPropertyRepository;

    @MockBean
    private RentalPropertyDtoMapper rentalPropertyDtoMapper;

    @Test
    void shouldGetRentalProperties() throws Exception {
        // GIVEN
        List<RentalPropertyEntity> rentalPropertyEntities = rentalPropertyEntities();
        List<RentalPropertyResponseDto> rentalPropertyResponseList = rentalPropertyResponseList();

        // WHEN
        when(rentalPropertyRepository.findAll()).thenReturn(rentalPropertyEntities);
        when(rentalPropertyDtoMapper.mapToDtoList(rentalPropertyEntities)).thenReturn(rentalPropertyResponseList);

        mockMvc.perform(get("/rent-properties-api/rental-properties"))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperties)));

        // THEN
        verify(rentalPropertyRepository).findAll();
        verify(rentalPropertyDtoMapper).mapToDtoList(rentalPropertyEntities);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void shouldGetRentalPropertyById() throws Exception {
        // GIVEN
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();
        RentalPropertyResponseDto rentalPropertyResponseDto = oneRentalPropertyResponse();
        int id = 1;

        // WHEN
        when(rentalPropertyRepository.findById((id))).thenReturn(Optional.of(rentalPropertyEntity));
        when(rentalPropertyDtoMapper.mapToDto(rentalPropertyEntity)).thenReturn(rentalPropertyResponseDto);

        mockMvc.perform(get("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().json(readResource(rentalProperty)));

        // THEN
        verify(rentalPropertyRepository).findById(id);
        verify(rentalPropertyDtoMapper).mapToDto(rentalPropertyEntity);
        verifyNoMoreInteractions(rentalPropertyRepository, rentalPropertyDtoMapper);
    }

    @Test
    void shouldThrowNotFoundRentalPropertyException_whenRentalPropertyNotFound() throws Exception {
        // GIVEN
        int id = 2;

        // WHEN
        when(rentalPropertyRepository.findById(id)).thenReturn(Optional.empty());

        mockMvc.perform(get("/rent-properties-api/rental-properties/{id}", id))
                .andExpect(status().isNotFound())
                .andExpect(content().json("{\"message\":\"Le bien immobilier avec l'id : " + id + " est introuvable\"}"));

        // THEN
        verify(rentalPropertyRepository).findById(id);
        verifyNoInteractions(rentalPropertyDtoMapper);
        verifyNoMoreInteractions(rentalPropertyRepository);
    }

    @Test
    void shouldCreateRentalProperty() throws Exception {
        RentalPropertyRequestDto rentalPropertyRequestDto = oneRentalPropertyRequest();
        RentalPropertyEntity rentalPropertyEntity = oneRentalPropertyEntity();

        when(rentalPropertyDtoMapper.mapToEntity(rentalPropertyRequestDto)).thenReturn(rentalPropertyEntity);
        when(rentalPropertyRepository.save(rentalPropertyEntity)).thenReturn(rentalPropertyEntity);

        mockMvc.perform(post("/rent-properties-api/rental-properties")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(rentalPropertyRequest)))
                .andExpect(status().isCreated());

        verify(rentalPropertyDtoMapper).mapToEntity(rentalPropertyRequestDto);
        verify(rentalPropertyRepository).save(rentalPropertyEntity);
        verifyNoMoreInteractions(rentalPropertyDtoMapper, rentalPropertyRepository);
    }

    @Test
    void shouldReturnBadRequestWhenSendInvalidRequestBody() throws Exception {
        mockMvc.perform(post("/rent-properties-api/rental-properties")
                        .contentType(APPLICATION_JSON_VALUE)
                        .content(readResource(invalidRentalPropertyRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\": \"La requête envoyée est invalide\"}"));

        verifyNoInteractions(rentalPropertyDtoMapper, rentalPropertyRepository);
    }
}
