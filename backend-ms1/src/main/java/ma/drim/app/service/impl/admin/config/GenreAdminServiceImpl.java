package ma.drim.app.service.impl.admin.config;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.dao.criteria.core.config.GenreCriteria;
import ma.drim.app.dao.facade.core.config.GenreDao;
import ma.drim.app.dao.specification.core.config.GenreSpecification;
import ma.drim.app.service.facade.admin.config.GenreAdminService;
import ma.drim.app.zynerator.service.AbstractServiceImpl;
import static ma.drim.app.zynerator.util.ListUtil.*;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.drim.app.zynerator.util.RefelexivityUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class GenreAdminServiceImpl implements GenreAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Genre update(Genre t) {
        Genre loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Genre.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Genre findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Genre findOrSave(Genre t) {
        if (t != null) {
            Genre result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Genre> findAll() {
        return dao.findAll();
    }

    public List<Genre> findByCriteria(GenreCriteria criteria) {
        List<Genre> content = null;
        if (criteria != null) {
            GenreSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private GenreSpecification constructSpecification(GenreCriteria criteria) {
        GenreSpecification mySpecification =  (GenreSpecification) RefelexivityUtil.constructObjectUsingOneParam(GenreSpecification.class, criteria);
        return mySpecification;
    }

    public List<Genre> findPaginatedByCriteria(GenreCriteria criteria, int page, int pageSize, String order, String sortField) {
        GenreSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GenreCriteria criteria) {
        GenreSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
	public boolean deleteById(Long id) {
        boolean condition = (id != null);
        if (condition) {
            dao.deleteById(id);
        }
        return condition;
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Genre> delete(List<Genre> list) {
		List<Genre> result = new ArrayList();
        if (list != null) {
            for (Genre t : list) {
                if(dao.findById(t.getId()).isEmpty()){
					result.add(t);
				}else{
                    dao.deleteById(t.getId());
                }
            }
        }
		return result;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Genre create(Genre t) {
        Genre loaded = findByReferenceEntity(t);
        Genre saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Genre findWithAssociatedLists(Long id){
        Genre result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Genre> update(List<Genre> ts, boolean createIfNotExist) {
        List<Genre> result = new ArrayList<>();
        if (ts != null) {
            for (Genre t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Genre loadedItem = dao.findById(t.getId()).orElse(null);
                    if (isEligibleForCreateOrUpdate(createIfNotExist, t, loadedItem)) {
                        dao.save(t);
                    } else {
                        result.add(t);
                    }
                }
            }
        }
        return result;
    }


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Genre t, Genre loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Genre findByReferenceEntity(Genre t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Genre> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Genre>> getToBeSavedAndToBeDeleted(List<Genre> oldList, List<Genre> newList) {
        List<List<Genre>> result = new ArrayList<>();
        List<Genre> resultDelete = new ArrayList<>();
        List<Genre> resultUpdateOrSave = new ArrayList<>();
        if (isEmpty(oldList) && isNotEmpty(newList)) {
            resultUpdateOrSave.addAll(newList);
        } else if (isEmpty(newList) && isNotEmpty(oldList)) {
            resultDelete.addAll(oldList);
        } else if (isNotEmpty(newList) && isNotEmpty(oldList)) {
			extractToBeSaveOrDelete(oldList, newList, resultUpdateOrSave, resultDelete);
        }
        result.add(resultUpdateOrSave);
        result.add(resultDelete);
        return result;
    }

    private void extractToBeSaveOrDelete(List<Genre> oldList, List<Genre> newList, List<Genre> resultUpdateOrSave, List<Genre> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Genre myOld = oldList.get(i);
                Genre t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Genre myNew = newList.get(i);
                Genre t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public GenreAdminServiceImpl(GenreDao dao) {
        this.dao = dao;
    }

    private GenreDao dao;
}
