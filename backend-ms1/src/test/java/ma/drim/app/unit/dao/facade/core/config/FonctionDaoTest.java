package ma.drim.app.unit.dao.facade.core.config;

import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.dao.facade.core.config.FonctionDao;

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


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
public class FonctionDaoTest {

@Autowired
    private FonctionDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Fonction entity = new Fonction();
        entity.setCode(code);
        underTest.save(entity);
        Fonction loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Fonction loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Fonction entity = new Fonction();
        entity.setId(id);
        underTest.save(entity);
        Fonction loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Fonction entity = new Fonction();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Fonction loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Fonction> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Fonction> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Fonction given = constructSample(1);
        Fonction saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Fonction constructSample(int i) {
		Fonction given = new Fonction();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
