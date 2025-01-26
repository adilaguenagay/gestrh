package ma.drim.app.service.facade.admin.diplome;

import java.util.List;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.dao.criteria.core.diplome.DiplomeCriteria;
import ma.drim.app.zynerator.service.IService;



public interface DiplomeAdminService {



    List<Diplome> findByTypeId(Long id);
    int deleteByTypeId(Long id);
    long countByTypeId(Long id);
    List<Diplome> findByEmployeeId(Long id);
    int deleteByEmployeeId(Long id);
    long countByEmployeePpr(String ppr);




	Diplome create(Diplome t);

    Diplome update(Diplome t);

    List<Diplome> update(List<Diplome> ts,boolean createIfNotExist);

    Diplome findById(Long id);

    Diplome findOrSave(Diplome t);

    Diplome findByReferenceEntity(Diplome t);

    Diplome findWithAssociatedLists(Long id);

    List<Diplome> findAllOptimized();

    List<Diplome> findAll();

    List<Diplome> findByCriteria(DiplomeCriteria criteria);

    List<Diplome> findPaginatedByCriteria(DiplomeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(DiplomeCriteria criteria);

    List<Diplome> delete(List<Diplome> ts);

    boolean deleteById(Long id);

    List<List<Diplome>> getToBeSavedAndToBeDeleted(List<Diplome> oldList, List<Diplome> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
