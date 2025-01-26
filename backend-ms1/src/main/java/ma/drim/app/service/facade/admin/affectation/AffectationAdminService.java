package ma.drim.app.service.facade.admin.affectation;

import java.util.List;
import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.dao.criteria.core.affectation.AffectationCriteria;
import ma.drim.app.zynerator.service.IService;



public interface AffectationAdminService {



    List<Affectation> findByEmployeeId(Long id);
    int deleteByEmployeeId(Long id);
    long countByEmployeePpr(String ppr);
    List<Affectation> findByUniteMereId(Long id);
    int deleteByUniteMereId(Long id);
    long countByUniteMereCode(String code);




	Affectation create(Affectation t);

    Affectation update(Affectation t);

    List<Affectation> update(List<Affectation> ts,boolean createIfNotExist);

    Affectation findById(Long id);

    Affectation findOrSave(Affectation t);

    Affectation findByReferenceEntity(Affectation t);

    Affectation findWithAssociatedLists(Long id);

    List<Affectation> findAllOptimized();

    List<Affectation> findAll();

    List<Affectation> findByCriteria(AffectationCriteria criteria);

    List<Affectation> findPaginatedByCriteria(AffectationCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(AffectationCriteria criteria);

    List<Affectation> delete(List<Affectation> ts);

    boolean deleteById(Long id);

    List<List<Affectation>> getToBeSavedAndToBeDeleted(List<Affectation> oldList, List<Affectation> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
