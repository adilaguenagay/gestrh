package ma.drim.app.unit.service.impl.admin.employee;

import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.dao.facade.core.employee.EmployeeDao;
import ma.drim.app.service.impl.admin.employee.EmployeeAdminServiceImpl;

import ma.drim.app.bean.core.employee.Employee ;
import ma.drim.app.bean.core.diplome.TypeDiplome ;
import ma.drim.app.bean.core.config.SituationFamiliale ;
import ma.drim.app.bean.core.diplome.Diplome ;
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
class EmployeeAdminServiceImplTest {

    @Mock
    private EmployeeDao repository;
    private AutoCloseable autoCloseable;
    private EmployeeAdminServiceImpl underTest;



    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new EmployeeAdminServiceImpl(repository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetAllEmployee() {
         //when
        underTest.findAll();
        verify(repository).findAll();
    }

    @Test
    void itShouldSaveEmployee() {
        // Given
        Employee toSave = constructSample(1);
        when(repository.save(toSave)).thenReturn(toSave);

        // When
        underTest.create(toSave);

        // Then
        verify(repository).save(toSave);
    }

    @Test
    void itShouldDeleteEmployee() {
        // Given
        Long idToDelete = 1L;
        when(repository.existsById(idToDelete)).thenReturn(true);

        // When
        underTest.deleteById(idToDelete);

        // Then
        verify(repository).deleteById(idToDelete);
    }
    @Test
    void itShouldGetEmployeeById() {
        // Given
        Long idToRetrieve = 1L; // Example Employee ID to retrieve
        Employee expected = new Employee(); // You need to replace Employee with your actual class
        expected.setId(idToRetrieve);
        when(repository.findById(idToRetrieve)).thenReturn(java.util.Optional.of(expected));

        // When
        Employee result = underTest.findById(idToRetrieve);

        // Then
        assertEquals(expected, result);
    }
	
	private Employee constructSample(int i) {
		Employee given = new Employee();
        given.setPpr("ppr-"+i);
        given.setNom("nom-"+i);
        given.setPrenom("prenom-"+i);
        given.setLieuNaissance("lieuNaissance-"+i);
        given.setPhotoUrl("photoUrl-"+i);
        given.setTelephone("telephone-"+i);
        given.setAddress("address-"+i);
        given.setEmail("email-"+i);
        given.setLocal(new Local(1L));
        given.setSituationFamiliale(new SituationFamiliale(1L));
        given.setGenre(new Genre(1L));
        given.setGrade(new Grade(1L));
        given.setFonction(new Fonction(1L));
        given.setUniteStructurelle(new UniteStructurelle(1L));
        List<Diplome> diplome = IntStream.rangeClosed(1, 3)
                                             .mapToObj(id -> {
                                                Diplome element = new Diplome();
                                                element.setId((long)id);
                                                element.setAnneeObtention(1);
                                                element.setType(new TypeDiplome(Long.valueOf(2)));
                                                element.setSpecialie("specialie"+id);
                                                element.setOrganisme("organisme"+id);
                                                element.setEmployee(new Employee(Long.valueOf(5)));
                                                return element;
                                             })
                                             .collect(Collectors.toList());
        given.setDiplome(diplome);
        return given;
    }

}
