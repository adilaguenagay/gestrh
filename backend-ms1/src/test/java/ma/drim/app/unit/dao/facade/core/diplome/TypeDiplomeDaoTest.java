package ma.drim.app.unit.dao.facade.core.diplome;

import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.dao.facade.core.diplome.TypeDiplomeDao;

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
public class TypeDiplomeDaoTest {

@Autowired
    private TypeDiplomeDao underTest;

    @BeforeEach
    void setUp() {
        underTest.deleteAll();
    }


    @Test
    void shouldFindById(){
        Long id = 1L;
        TypeDiplome entity = new TypeDiplome();
        entity.setId(id);
        underTest.save(entity);
        TypeDiplome loaded = underTest.findById(id).orElse(null);
        assertThat(loaded.getId()).isEqualTo(id);
    }

    @Test
    void shoulDeleteById() {
        Long id = 1L;
        TypeDiplome entity = new TypeDiplome();
        entity.setId(id);
        underTest.save(entity);

        underTest.deleteById(id);

        TypeDiplome loaded = underTest.findById(id).orElse(null);
        assertThat(loaded).isNull();
    }


    @Test
    void shouldFindAll() {
        // Given
        List<TypeDiplome> items = IntStream.rangeClosed(1, 10).mapToObj(i->constructSample(i)).collect(Collectors.toList());

        // Init
        items.forEach(underTest::save);

        // When
        List<TypeDiplome> loadedItems = underTest.findAll();

        // Then
        assertThat(loadedItems).isNotNull();
        assertThat(loadedItems.size()).isEqualTo(10);
    }

    @Test
    void shouldSave(){
        TypeDiplome given = constructSample(1);
        TypeDiplome saved = underTest.save(given);
        assertThat(saved.getId()).isNotNull();
    }

    private TypeDiplome constructSample(int i) {
		TypeDiplome given = new TypeDiplome();
        given.setCode("code-"+i);
        given.setLibelle("libelle-"+i);
        return given;
    }

}
