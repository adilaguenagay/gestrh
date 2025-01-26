package ma.drim.app.unit.dao.facade.core.employee;

import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.dao.facade.core.employee.EmployeeDao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.util.List;

import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.IntStream;
import java.time.LocalDateTime;

import ma.drim.app.bean.core.config.SituationFamiliale ;
import ma.drim.app.bean.core.config.Fonction ;
import ma.drim.app.bean.core.config.Local ;
import ma.drim.app.bean.core.config.Genre ;
import ma.drim.app.bean.core.config.Grade ;
import ma.drim.app.bean.core.config.UniteStructurelle ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class EmployeeDaoTest {

@Autowired
    private EmployeeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByPpr(){
        String ppr = "ppr-1";
        Employee entity = new Employee();
        entity.setPpr(ppr);
        underTest.save(entity);
        Employee loaded = underTest.findByPpr(ppr);
        assertThat(loaded.getPpr()).isEqualTo(ppr);
    }

    @Test
    void shouldDeleteByPpr() {
        String ppr = "ppr-12345678";
        int result = underTest.deleteByPpr(ppr);

        Employee loaded = underTest.findByPpr(ppr);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Employee entity = new Employee();
        entity.setId(id);
        underTest.save(entity);
        Employee loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Employee entity = new Employee();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Employee loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Employee> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Employee> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Employee given = constructSample(1);
        Employee saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
        return given;
    }

}
