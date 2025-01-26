package ma.drim.app.service.facade.admin.config;

import java.util.List;
import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.dao.criteria.core.config.UniteStructurelleCriteria;
import ma.drim.app.zynerator.service.IService;



public interface UniteStructurelleAdminService {



    List<UniteStructurelle> findByUniteMereId(Long id);
    int deleteByUniteMereId(Long id);
    long countByUniteMereCode(String code);




	UniteStructurelle create(UniteStructurelle t);

    UniteStructurelle update(UniteStructurelle t);

    List<UniteStructurelle> update(List<UniteStructurelle> ts,boolean createIfNotExist);

    UniteStructurelle findById(Long id);

    UniteStructurelle findOrSave(UniteStructurelle t);

    UniteStructurelle findByReferenceEntity(UniteStructurelle t);

    UniteStructurelle findWithAssociatedLists(Long id);

    List<UniteStructurelle> findAllOptimized();

    List<UniteStructurelle> findAll();

    List<UniteStructurelle> findByCriteria(UniteStructurelleCriteria criteria);

    List<UniteStructurelle> findPaginatedByCriteria(UniteStructurelleCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(UniteStructurelleCriteria criteria);

    List<UniteStructurelle> delete(List<UniteStructurelle> ts);

    boolean deleteById(Long id);

    List<List<UniteStructurelle>> getToBeSavedAndToBeDeleted(List<UniteStructurelle> oldList, List<UniteStructurelle> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
