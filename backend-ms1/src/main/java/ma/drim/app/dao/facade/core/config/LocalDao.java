package ma.drim.app.dao.facade.core.config;

import org.springframework.data.jpa.repository.Query;
import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.config.Local;
import org.springframework.stereotype.Repository;
import ma.drim.app.bean.core.config.Local;
import java.util.List;


@Repository
public interface LocalDao extends AbstractRepository<Local,Long>  {
    Local findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW Local(item.id,item.libelle) FROM Local item")
    List<Local> findAllOptimized();

}
