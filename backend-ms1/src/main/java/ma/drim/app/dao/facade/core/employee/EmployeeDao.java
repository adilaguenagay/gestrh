package ma.drim.app.dao.facade.core.employee;

import org.springframework.data.jpa.repository.Query;
import ma.drim.app.zynerator.repository.AbstractRepository;
import ma.drim.app.bean.core.employee.Employee;
import org.springframework.stereotype.Repository;
import ma.drim.app.bean.core.employee.Employee;
import java.util.List;


@Repository
public interface EmployeeDao extends AbstractRepository<Employee,Long>  {
    Employee findByPpr(String ppr);
    int deleteByPpr(String ppr);

    List<Employee> findByLocalId(Long id);
    int deleteByLocalId(Long id);
    long countByLocalCode(String code);
    List<Employee> findBySituationFamilialeId(Long id);
    int deleteBySituationFamilialeId(Long id);
    long countBySituationFamilialeCode(String code);
    List<Employee> findByGenreId(Long id);
    int deleteByGenreId(Long id);
    long countByGenreCode(String code);
    List<Employee> findByGradeId(Long id);
    int deleteByGradeId(Long id);
    long countByGradeCode(String code);
    List<Employee> findByFonctionId(Long id);
    int deleteByFonctionId(Long id);
    long countByFonctionCode(String code);
    List<Employee> findByUniteStructurelleId(Long id);
    int deleteByUniteStructurelleId(Long id);
    long countByUniteStructurelleCode(String code);

    @Query("SELECT NEW Employee(item.id,item.ppr) FROM Employee item")
    List<Employee> findAllOptimized();

}
