package ma.drim.app.unit.dao.facade.core.affectation;

import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.dao.facade.core.affectation.AffectationDao;

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

import ma.drim.app.bean.core.employee.Employee ;
import ma.drim.app.bean.core.config.UniteStructurelle ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class AffectationDaoTest {

@Autowired
    private AffectationDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Affectation entity = new Affectation();
        entity.setId(id);
        underTest.save(entity);
        Affectation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Affectation entity = new Affectation();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Affectation loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Affectation> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Affectation> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Affectation given = constructSample(1);
        Affectation saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Affectation constructSample(int i) {
		Affectation given = new Affectation();
        given.setEmployee(new Employee(1L));
        given.setUniteMere(new UniteStructurelle(1L));
        return given;
    }

}
