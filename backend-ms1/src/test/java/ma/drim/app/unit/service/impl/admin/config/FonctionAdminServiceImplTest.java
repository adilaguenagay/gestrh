package ma.drim.app.unit.service.impl.admin.config;

import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.dao.facade.core.config.FonctionDao;
import ma.drim.app.service.impl.admin.config.FonctionAdminServiceImpl;

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
class FonctionAdminServiceImplTest {

    @Mock
    private FonctionDao repository;
    private AutoCloseable autoCloseable;
    private FonctionAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new FonctionAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllFonction() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveFonction() {
        // Given
        Fonction toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteFonction() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetFonctionById() {
        // Given
        Long idToRetrieve = 1L; // Example Fonction ID to retrieve
        Fonction expected = new Fonction(); // You need to replace Fonction with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Fonction result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Fonction constructSample(int i) {
		Fonction given = new Fonction();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
