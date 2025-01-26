package ma.drim.app.dao.facade.core.config;

import org.springframework.data.jpa.repository.Query;
import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.config.Grade;
import org.springframework.stereotype.Repository;
import ma.drim.app.bean.core.config.Grade;
import java.util.List;


@Repository
public interface GradeDao extends AbstractRepository<Grade,Long>  {
    Grade findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Grade(item.id,item.libelle) FROM Grade item")
    List<Grade> findAllOptimized();

}
