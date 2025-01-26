package ma.drim.app.unit.service.impl.admin.config;

import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.dao.facade.core.config.GenreDao;
import ma.drim.app.service.impl.admin.config.GenreAdminServiceImpl;

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
class GenreAdminServiceImplTest {

    @Mock
    private GenreDao repository;
    private AutoCloseable autoCloseable;
    private GenreAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new GenreAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllGenre() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveGenre() {
        // Given
        Genre toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteGenre() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetGenreById() {
        // Given
        Long idToRetrieve = 1L; // Example Genre ID to retrieve
        Genre expected = new Genre(); // You need to replace Genre with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Genre result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Genre constructSample(int i) {
		Genre given = new Genre();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
