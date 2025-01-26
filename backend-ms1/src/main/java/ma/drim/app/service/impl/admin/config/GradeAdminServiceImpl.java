package ma.drim.app.service.impl.admin.config;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.dao.criteria.core.config.GradeCriteria;
import ma.drim.app.dao.facade.core.config.GradeDao;
import ma.drim.app.dao.specification.core.config.GradeSpecification;
import ma.drim.app.service.facade.admin.config.GradeAdminService;
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
public class GradeAdminServiceImpl implements GradeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Grade update(Grade t) {
        Grade loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Grade.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Grade findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Grade findOrSave(Grade t) {
        if (t != null) {
            Grade result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Grade> findAll() {
        return dao.findAll();
    }

    public List<Grade> findByCriteria(GradeCriteria criteria) {
        List<Grade> content = null;
        if (criteria != null) {
            GradeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private GradeSpecification constructSpecification(GradeCriteria criteria) {
        GradeSpecification mySpecification =  (GradeSpecification) RefelexivityUtil.constructObjectUsingOneParam(GradeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Grade> findPaginatedByCriteria(GradeCriteria criteria, int page, int pageSize, String order, String sortField) {
        GradeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(GradeCriteria criteria) {
        GradeSpecification mySpecification = constructSpecification(criteria);
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
    public List<Grade> delete(List<Grade> list) {
		List<Grade> result = new ArrayList();
        if (list != null) {
            for (Grade t : list) {
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
    public Grade create(Grade t) {
        Grade loaded = findByReferenceEntity(t);
        Grade saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Grade findWithAssociatedLists(Long id){
        Grade result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Grade> update(List<Grade> ts, boolean createIfNotExist) {
        List<Grade> result = new ArrayList<>();
        if (ts != null) {
            for (Grade t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Grade loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Grade t, Grade loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Grade findByReferenceEntity(Grade t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Grade> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Grade>> getToBeSavedAndToBeDeleted(List<Grade> oldList, List<Grade> newList) {
        List<List<Grade>> result = new ArrayList<>();
        List<Grade> resultDelete = new ArrayList<>();
        List<Grade> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Grade> oldList, List<Grade> newList, List<Grade> resultUpdateOrSave, List<Grade> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Grade myOld = oldList.get(i);
                Grade t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Grade myNew = newList.get(i);
                Grade t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public GradeAdminServiceImpl(GradeDao dao) {
        this.dao = dao;
    }

    private GradeDao dao;
}
