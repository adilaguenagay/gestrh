package ma.drim.app.unit.ws.facade.admin.affectation;

import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.service.impl.admin.affectation.AffectationAdminServiceImpl;
import ma.drim.app.ws.facade.admin.affectation.AffectationRestAdmin;
import ma.drim.app.ws.converter.affectation.AffectationConverter;
import ma.drim.app.ws.dto.affectation.AffectationDto;
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
public class AffectationRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private AffectationAdminServiceImpl service;
    @Mock
    private AffectationConverter converter;

    @InjectMocks
    private AffectationRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllAffectationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<AffectationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<AffectationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveAffectationTest() throws Exception {
        // Mock data
        AffectationDto requestDto = new AffectationDto();
        Affectation entity = new Affectation();
        Affectation saved = new Affectation();
        AffectationDto savedDto = new AffectationDto();

        // Mock the converter to return the affectation object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved affectation DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<AffectationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        AffectationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved affectation DTO
    }

}
