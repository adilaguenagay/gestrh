package ma.drim.app.service.facade.admin.employee;

import java.util.List;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.dao.criteria.core.employee.EmployeeCriteria;
import ma.drim.app.zynerator.service.IService;



public interface EmployeeAdminService {



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




	Employee create(Employee t);

    Employee update(Employee t);

    List<Employee> update(List<Employee> ts,boolean createIfNotExist);

    Employee findById(Long id);

    Employee findOrSave(Employee t);

    Employee findByReferenceEntity(Employee t);

    Employee findWithAssociatedLists(Long id);

    List<Employee> findAllOptimized();

    List<Employee> findAll();

    List<Employee> findByCriteria(EmployeeCriteria criteria);

    List<Employee> findPaginatedByCriteria(EmployeeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(EmployeeCriteria criteria);

    List<Employee> delete(List<Employee> ts);

    boolean deleteById(Long id);

    List<List<Employee>> getToBeSavedAndToBeDeleted(List<Employee> oldList, List<Employee> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
