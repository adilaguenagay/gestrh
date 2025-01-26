package ma.drim.app.unit.ws.facade.admin.config;

import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.service.impl.admin.config.SituationFamilialeAdminServiceImpl;
import ma.drim.app.ws.facade.admin.config.SituationFamilialeRestAdmin;
import ma.drim.app.ws.converter.config.SituationFamilialeConverter;
import ma.drim.app.ws.dto.config.SituationFamilialeDto;
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
public class SituationFamilialeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SituationFamilialeAdminServiceImpl service;
    @Mock
    private SituationFamilialeConverter converter;

    @InjectMocks
    private SituationFamilialeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSituationFamilialeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SituationFamilialeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SituationFamilialeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSituationFamilialeTest() throws Exception {
        // Mock data
        SituationFamilialeDto requestDto = new SituationFamilialeDto();
        SituationFamiliale entity = new SituationFamiliale();
        SituationFamiliale saved = new SituationFamiliale();
        SituationFamilialeDto savedDto = new SituationFamilialeDto();

        // Mock the converter to return the situationFamiliale object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved situationFamiliale DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SituationFamilialeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SituationFamilialeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved situationFamiliale DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
