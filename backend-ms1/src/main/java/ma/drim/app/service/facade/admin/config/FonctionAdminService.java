package ma.drim.app.service.facade.admin.config;

import java.util.List;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.dao.criteria.core.config.FonctionCriteria;
import ma.drim.app.zynerator.service.IService;



public interface FonctionAdminService {







	Fonction create(Fonction t);

    Fonction update(Fonction t);

    List<Fonction> update(List<Fonction> ts,boolean createIfNotExist);

    Fonction findById(Long id);

    Fonction findOrSave(Fonction t);

    Fonction findByReferenceEntity(Fonction t);

    Fonction findWithAssociatedLists(Long id);

    List<Fonction> findAllOptimized();

    List<Fonction> findAll();

    List<Fonction> findByCriteria(FonctionCriteria criteria);

    List<Fonction> findPaginatedByCriteria(FonctionCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(FonctionCriteria criteria);

    List<Fonction> delete(List<Fonction> ts);

    boolean deleteById(Long id);

    List<List<Fonction>> getToBeSavedAndToBeDeleted(List<Fonction> oldList, List<Fonction> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
