package ma.drim.app.unit.dao.facade.core.diplome;

import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.dao.facade.core.diplome.DiplomeDao;

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
import ma.drim.app.bean.core.diplome.TypeDiplome ;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class DiplomeDaoTest {

@Autowired
    private DiplomeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        Diplome entity = new Diplome();
        entity.setId(id);
        underTest.save(entity);
        Diplome loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Diplome entity = new Diplome();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Diplome loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Diplome> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Diplome> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Diplome given = constructSample(1);
        Diplome saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
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
