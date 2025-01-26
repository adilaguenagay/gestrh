package ma.drim.app.service.facade.admin.config;

import java.util.List;
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.dao.criteria.core.config.LocalCriteria;
import ma.drim.app.zynerator.service.IService;



public interface LocalAdminService {







	Local create(Local t);

    Local update(Local t);

    List<Local> update(List<Local> ts,boolean createIfNotExist);

    Local findById(Long id);

    Local findOrSave(Local t);

    Local findByReferenceEntity(Local t);

    Local findWithAssociatedLists(Long id);

    List<Local> findAllOptimized();

    List<Local> findAll();

    List<Local> findByCriteria(LocalCriteria criteria);

    List<Local> findPaginatedByCriteria(LocalCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(LocalCriteria criteria);

    List<Local> delete(List<Local> ts);

    boolean deleteById(Long id);

    List<List<Local>> getToBeSavedAndToBeDeleted(List<Local> oldList, List<Local> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
