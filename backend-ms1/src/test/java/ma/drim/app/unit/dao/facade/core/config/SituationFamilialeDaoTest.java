package ma.drim.app.unit.dao.facade.core.config;

import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.dao.facade.core.config.SituationFamilialeDao;

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
public class SituationFamilialeDaoTest {

@Autowired
    private SituationFamilialeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        SituationFamiliale entity = new SituationFamiliale();
        entity.setCode(code);
        underTest.save(entity);
        SituationFamiliale loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        SituationFamiliale loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        SituationFamiliale entity = new SituationFamiliale();
        entity.setId(id);
        underTest.save(entity);
        SituationFamiliale loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        SituationFamiliale entity = new SituationFamiliale();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        SituationFamiliale loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<SituationFamiliale> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<SituationFamiliale> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        SituationFamiliale given = constructSample(1);
        SituationFamiliale saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private SituationFamiliale constructSample(int i) {
		SituationFamiliale given = new SituationFamiliale();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
