package ma.drim.app.dao.facade.core.config;

import org.springframework.data.jpa.repository.Query;
import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.config.UniteStructurelle;
import org.springframework.stereotype.Repository;
import ma.drim.app.bean.core.config.UniteStructurelle;
import java.util.List;


@Repository
public interface UniteStructurelleDao extends AbstractRepository<UniteStructurelle,Long>  {
    UniteStructurelle findByCode(String code);
    int deleteByCode(String code);

    List<UniteStructurelle> findByUniteMereId(Long id);
    int deleteByUniteMereId(Long id);
    long countByUniteMereCode(String code);

    @Query("SELECT NEW UniteStructurelle(item.id,item.code) FROM UniteStructurelle item")
    List<UniteStructurelle> findAllOptimized();

}
