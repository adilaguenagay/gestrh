package ma.drim.app.service.facade.admin.config;

import java.util.List;
import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.dao.criteria.core.config.GenreCriteria;
import ma.drim.app.zynerator.service.IService;



public interface GenreAdminService {







	Genre create(Genre t);

    Genre update(Genre t);

    List<Genre> update(List<Genre> ts,boolean createIfNotExist);

    Genre findById(Long id);

    Genre findOrSave(Genre t);

    Genre findByReferenceEntity(Genre t);

    Genre findWithAssociatedLists(Long id);

    List<Genre> findAllOptimized();

    List<Genre> findAll();

    List<Genre> findByCriteria(GenreCriteria criteria);

    List<Genre> findPaginatedByCriteria(GenreCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(GenreCriteria criteria);

    List<Genre> delete(List<Genre> ts);

    boolean deleteById(Long id);

    List<List<Genre>> getToBeSavedAndToBeDeleted(List<Genre> oldList, List<Genre> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
