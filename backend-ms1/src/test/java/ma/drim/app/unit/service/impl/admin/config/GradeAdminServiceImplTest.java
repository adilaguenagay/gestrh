package ma.drim.app.unit.service.impl.admin.config;

import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.dao.facade.core.config.GradeDao;
import ma.drim.app.service.impl.admin.config.GradeAdminServiceImpl;

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
class GradeAdminServiceImplTest {

    @Mock
    private GradeDao repository;
    private AutoCloseable autoCloseable;
    private GradeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new GradeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllGrade() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveGrade() {
        // Given
        Grade toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteGrade() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetGradeById() {
        // Given
        Long idToRetrieve = 1L; // Example Grade ID to retrieve
        Grade expected = new Grade(); // You need to replace Grade with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Grade result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Grade constructSample(int i) {
		Grade given = new Grade();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
