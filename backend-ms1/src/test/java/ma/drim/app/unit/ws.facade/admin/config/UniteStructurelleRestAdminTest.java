package ma.drim.app.unit.ws.facade.admin.config;

import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.service.impl.admin.config.UniteStructurelleAdminServiceImpl;
import ma.drim.app.ws.facade.admin.config.UniteStructurelleRestAdmin;
import ma.drim.app.ws.converter.config.UniteStructurelleConverter;
import ma.drim.app.ws.dto.config.UniteStructurelleDto;
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
public class UniteStructurelleRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private UniteStructurelleAdminServiceImpl service;
    @Mock
    private UniteStructurelleConverter converter;

    @InjectMocks
    private UniteStructurelleRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllUniteStructurelleTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<UniteStructurelleDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<UniteStructurelleDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveUniteStructurelleTest() throws Exception {
        // Mock data
        UniteStructurelleDto requestDto = new UniteStructurelleDto();
        UniteStructurelle entity = new UniteStructurelle();
        UniteStructurelle saved = new UniteStructurelle();
        UniteStructurelleDto savedDto = new UniteStructurelleDto();

        // Mock the converter to return the uniteStructurelle object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved uniteStructurelle DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<UniteStructurelleDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        UniteStructurelleDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved uniteStructurelle DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
