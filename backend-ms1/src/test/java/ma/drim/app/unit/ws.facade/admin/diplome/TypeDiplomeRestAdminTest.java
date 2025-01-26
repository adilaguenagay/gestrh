package ma.drim.app.unit.ws.facade.admin.diplome;

import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.service.impl.admin.diplome.TypeDiplomeAdminServiceImpl;
import ma.drim.app.ws.facade.admin.diplome.TypeDiplomeRestAdmin;
import ma.drim.app.ws.converter.diplome.TypeDiplomeConverter;
import ma.drim.app.ws.dto.diplome.TypeDiplomeDto;
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
public class TypeDiplomeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private TypeDiplomeAdminServiceImpl service;
    @Mock
    private TypeDiplomeConverter converter;

    @InjectMocks
    private TypeDiplomeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllTypeDiplomeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<TypeDiplomeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<TypeDiplomeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveTypeDiplomeTest() throws Exception {
        // Mock data
        TypeDiplomeDto requestDto = new TypeDiplomeDto();
        TypeDiplome entity = new TypeDiplome();
        TypeDiplome saved = new TypeDiplome();
        TypeDiplomeDto savedDto = new TypeDiplomeDto();

        // Mock the converter to return the typeDiplome object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved typeDiplome DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<TypeDiplomeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        TypeDiplomeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved typeDiplome DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
