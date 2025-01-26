package ma.drim.app.unit.service.impl.admin.affectation;

import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.dao.facade.core.affectation.AffectationDao;
import ma.drim.app.service.impl.admin.affectation.AffectationAdminServiceImpl;

import ma.drim.app.bean.core.employee.Employee ;
import ma.drim.app.bean.core.config.UniteStructurelle ;
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
class AffectationAdminServiceImplTest {

    @Mock
    private AffectationDao repository;
    private AutoCloseable autoCloseable;
    private AffectationAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new AffectationAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllAffectation() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveAffectation() {
        // Given
        Affectation toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteAffectation() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetAffectationById() {
        // Given
        Long idToRetrieve = 1L; // Example Affectation ID to retrieve
        Affectation expected = new Affectation(); // You need to replace Affectation with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Affectation result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Affectation constructSample(int i) {
		Affectation given = new Affectation();
        given.setEmployee(new Employee(1L));
        given.setUniteMere(new UniteStructurelle(1L));
        return given;
    }

}
