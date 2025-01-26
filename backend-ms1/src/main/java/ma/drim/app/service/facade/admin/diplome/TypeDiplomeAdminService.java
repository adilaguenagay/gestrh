package ma.drim.app.service.facade.admin.diplome;

import java.util.List;
import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.dao.criteria.core.diplome.TypeDiplomeCriteria;
import ma.drim.app.zynerator.service.IService;



public interface TypeDiplomeAdminService {







	TypeDiplome create(TypeDiplome t);

    TypeDiplome update(TypeDiplome t);

    List<TypeDiplome> update(List<TypeDiplome> ts,boolean createIfNotExist);

    TypeDiplome findById(Long id);

    TypeDiplome findOrSave(TypeDiplome t);

    TypeDiplome findByReferenceEntity(TypeDiplome t);

    TypeDiplome findWithAssociatedLists(Long id);

    List<TypeDiplome> findAllOptimized();

    List<TypeDiplome> findAll();

    List<TypeDiplome> findByCriteria(TypeDiplomeCriteria criteria);

    List<TypeDiplome> findPaginatedByCriteria(TypeDiplomeCriteria criteria, int page, int pageSize, String order, String sortField);

    int getDataSize(TypeDiplomeCriteria criteria);

    List<TypeDiplome> delete(List<TypeDiplome> ts);

    boolean deleteById(Long id);

    List<List<TypeDiplome>> getToBeSavedAndToBeDeleted(List<TypeDiplome> oldList, List<TypeDiplome> newList);

    public String uploadFile(String checksumOld, String tempUpladedFile,String destinationFilePath) throws Exception ;

}
