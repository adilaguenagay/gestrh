package ma.drim.app.service.facade.admin.config;

import java.util.List;
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.dao.criteria.core.config.SituationFamilialeCriteria;
import ma.drim.app.zynerator.service.IService;



public interface SituationFamilialeAdminService {







	SituationFamiliale create(SituationFamiliale t);

    SituationFamiliale update(SituationFamiliale t);

    List<SituationFamiliale> update(List<SituationFamiliale> ts,boolean createIfNotExist);

    SituationFamiliale findById(Long id);

    SituationFamiliale findOrSave(SituationFamiliale t);

    SituationFamiliale findByReferenceEntity(SituationFamiliale t);

    SituationFamiliale findWithAssociatedLists(Long id);

    List<SituationFamiliale> findAllOptimized();

    List<SituationFamiliale> findAll();

    List<SituationFamiliale> findByCriteria(SituationFamilialeCriteria criteria);

    List<SituationFamiliale> findPaginatedByCriteria(SituationFamilialeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(SituationFamilialeCriteria criteria);

    List<SituationFamiliale> delete(List<SituationFamiliale> ts);

    boolean deleteById(Long id);

    List<List<SituationFamiliale>> getToBeSavedAndToBeDeleted(List<SituationFamiliale> oldList, List<SituationFamiliale> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
