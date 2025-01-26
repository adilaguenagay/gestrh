package ma.drim.app.unit.dao.facade.core.config;

import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.dao.facade.core.config.GenreDao;

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
public class GenreDaoTest {

@Autowired
    private GenreDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }

    @Test
    void shouldFindByCode(){
        String code = "code-1";
        Genre entity = new Genre();
        entity.setCode(code);
        underTest.save(entity);
        Genre loaded = underTest.findByCode(code);
        assertThat(loaded.getCode()).isEqualTo(code);
    }

    @Test
    void shouldDeleteByCode() {
        String code = "code-12345678";
        int result = underTest.deleteByCode(code);

        Genre loaded = underTest.findByCode(code);
        assertThat(loaded).isNull();
        assertThat(result).isEqualTo(0);
    }

    @Test
    void shouldFindById(){
        Long id = 1L;
        Genre entity = new Genre();
        entity.setId(id);
        underTest.save(entity);
        Genre loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        Genre entity = new Genre();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        Genre loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<Genre> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<Genre> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        Genre given = constructSample(1);
        Genre saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private Genre constructSample(int i) {
		Genre given = new Genre();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
