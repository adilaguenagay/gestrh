package ma.drim.app.unit.service.impl.admin.diplome;

import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.dao.facade.core.diplome.DiplomeDao;
import ma.drim.app.service.impl.admin.diplome.DiplomeAdminServiceImpl;

import ma.drim.app.bean.core.employee.Employee ;
import ma.drim.app.bean.core.diplome.TypeDiplome ;
import java.util.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.time.LocalDateTime;



import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class DiplomeAdminServiceImplTest {

    @Mock
    private DiplomeDao repository;
    private AutoCloseable autoCloseable;
    private DiplomeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new DiplomeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllDiplome() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveDiplome() {
        // Given
        Diplome toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteDiplome() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetDiplomeById() {
        // Given
        Long idToRetrieve = 1L; // Example Diplome ID to retrieve
        Diplome expected = new Diplome(); // You need to replace Diplome with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Diplome result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Diplome constructSample(int i) {
		Diplome given = new Diplome();
        given.setAnneeObtention(i);
        given.setType(new TypeDiplome(1L));
        given.setSpecialie("specialie-"+i);
        given.setOrganisme("organisme-"+i);
        given.setEmployee(new Employee(1L));
        return given;
    }

}
