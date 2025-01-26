package ma.drim.app.unit.service.impl.admin.config;

import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.dao.facade.core.config.UniteStructurelleDao;
import ma.drim.app.service.impl.admin.config.UniteStructurelleAdminServiceImpl;

import ma.drim.app.bean.core.employee.Employee ;
import ma.drim.app.bean.core.config.SituationFamiliale ;
import ma.drim.app.bean.core.config.Fonction ;
import ma.drim.app.bean.core.config.Local ;
import ma.drim.app.bean.core.config.Genre ;
import ma.drim.app.bean.core.config.Grade ;
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
class UniteStructurelleAdminServiceImplTest {

    @Mock
    private UniteStructurelleDao repository;
    private AutoCloseable autoCloseable;
    private UniteStructurelleAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new UniteStructurelleAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllUniteStructurelle() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveUniteStructurelle() {
        // Given
        UniteStructurelle toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteUniteStructurelle() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetUniteStructurelleById() {
        // Given
        Long idToRetrieve = 1L; // Example UniteStructurelle ID to retrieve
        UniteStructurelle expected = new UniteStructurelle(); // You need to replace UniteStructurelle with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        UniteStructurelle result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private UniteStructurelle constructSample(int i) {
		UniteStructurelle given = new UniteStructurelle();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        given.setUniteMere(new UniteStructurelle(1L));
        List<Employee> employee = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Employee element = new Employee();
                                                element.setId((long)id);
                                                element.setPpr("ppr"+id);
                                                element.setNom("nom"+id);
                                                element.setPrenom("prenom"+id);
                                                element.setLieuNaissance("lieuNaissance"+id);
                                                element.setPhotoUrl("photoUrl"+id);
                                                element.setTelephone("telephone"+id);
                                                element.setAddress("address"+id);
                                                element.setEmail("email"+id);
                                                element.setLocal(new Local(Long.valueOf(9)));
                                                element.setSituationFamiliale(new SituationFamiliale(Long.valueOf(10)));
                                                element.setGenre(new Genre(Long.valueOf(11)));
                                                element.setGrade(new Grade(Long.valueOf(12)));
                                                element.setFonction(new Fonction(Long.valueOf(13)));
                                                element.setUniteStructurelle(new UniteStructurelle(Long.valueOf(14)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setEmployee(employee);
        return given;
    }

}
