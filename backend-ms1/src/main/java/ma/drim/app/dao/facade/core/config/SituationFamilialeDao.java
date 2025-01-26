package ma.drim.app.dao.facade.core.config;

import org.springframework.data.jpa.repository.Query;
import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.config.SituationFamiliale;
import org.springframework.stereotype.Repository;
import ma.drim.app.bean.core.config.SituationFamiliale;
import java.util.List;


@Repository
public interface SituationFamilialeDao extends AbstractRepository<SituationFamiliale,Long>  {
    SituationFamiliale findByCode(String code);
    int deleteByCode(String code);


    @Query("SELECT NEW SituationFamiliale(item.id,item.libelle) FROM SituationFamiliale item")
    List<SituationFamiliale> findAllOptimized();

}
