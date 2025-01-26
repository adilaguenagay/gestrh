package ma.drim.app.unit.ws.facade.admin.diplome;

import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.service.impl.admin.diplome.DiplomeAdminServiceImpl;
import ma.drim.app.ws.facade.admin.diplome.DiplomeRestAdmin;
import ma.drim.app.ws.converter.diplome.DiplomeConverter;
import ma.drim.app.ws.dto.diplome.DiplomeDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DiplomeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DiplomeAdminServiceImpl service;
    @Mock
    private DiplomeConverter converter;

    @InjectMocks
    private DiplomeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDiplomeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DiplomeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DiplomeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDiplomeTest() throws Exception {
        // Mock data
        DiplomeDto requestDto = new DiplomeDto();
        Diplome entity = new Diplome();
        Diplome saved = new Diplome();
        DiplomeDto savedDto = new DiplomeDto();

        // Mock the converter to return the diplome object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved diplome DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DiplomeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DiplomeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved diplome DTO
        assertEquals(savedDto.getAnneeObtention(), responseBody.getAnneeObtention());
        assertEquals(savedDto.getSpecialie(), responseBody.getSpecialie());
        assertEquals(savedDto.getOrganisme(), responseBody.getOrganisme());
    }

}
