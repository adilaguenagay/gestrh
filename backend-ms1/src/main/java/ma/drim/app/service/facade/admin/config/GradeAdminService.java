package ma.drim.app.service.facade.admin.config;

import java.util.List;
import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.dao.criteria.core.config.GradeCriteria;
import ma.drim.app.zynerator.service.IService;



public interface GradeAdminService {







	Grade create(Grade t);

    Grade update(Grade t);

    List<Grade> update(List<Grade> ts,boolean createIfNotExist);

    Grade findById(Long id);

    Grade findOrSave(Grade t);

    Grade findByReferenceEntity(Grade t);

    Grade findWithAssociatedLists(Long id);

    List<Grade> findAllOptimized();

    List<Grade> findAll();

    List<Grade> findByCriteria(GradeCriteria criteria);

    List<Grade> findPaginatedByCriteria(GradeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GradeCriteria criteria);

    List<Grade> delete(List<Grade> ts);

    boolean deleteById(Long id);

    List<List<Grade>> getToBeSavedAndToBeDeleted(List<Grade> oldList, List<Grade> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
