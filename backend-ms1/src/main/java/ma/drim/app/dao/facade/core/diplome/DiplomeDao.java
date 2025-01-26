package ma.drim.app.dao.facade.core.diplome;

import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.diplome.Diplome;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface DiplomeDao extends AbstractRepository<Diplome,Long>  {

    List<Diplome> findByTypeId(Long id);
    int deleteByTypeId(Long id);
    long countByTypeId(Long id);
    List<Diplome> findByEmployeeId(Long id);
    int deleteByEmployeeId(Long id);
    long countByEmployeePpr(String ppr);


}
