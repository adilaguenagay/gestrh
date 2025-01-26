package ma.drim.app.dao.facade.core.affectation;

import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.affectation.Affectation;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface AffectationDao extends AbstractRepository<Affectation,Long>  {

    List<Affectation> findByEmployeeId(Long id);
    int deleteByEmployeeId(Long id);
    long countByEmployeePpr(String ppr);
    List<Affectation> findByUniteMereId(Long id);
    int deleteByUniteMereId(Long id);
    long countByUniteMereCode(String code);


}
