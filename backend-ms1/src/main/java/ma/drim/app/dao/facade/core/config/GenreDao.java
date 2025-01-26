package ma.drim.app.dao.facade.core.config;

import org.springframework.data.jpa.repository.Query;
import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.config.Genre;
import org.springframework.stereotype.Repository;
import ma.drim.app.bean.core.config.Genre;
import java.util.List;


@Repository
public interface GenreDao extends AbstractRepository<Genre,Long>  {
    Genre findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Genre(item.id,item.libelle) FROM Genre item")
    List<Genre> findAllOptimized();

}
