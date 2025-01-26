package ma.drim.app.service.impl.admin.config;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.dao.criteria.core.config.SituationFamilialeCriteria;
import ma.drim.app.dao.facade.core.config.SituationFamilialeDao;
import ma.drim.app.dao.specification.core.config.SituationFamilialeSpecification;
import ma.drim.app.service.facade.admin.config.SituationFamilialeAdminService;
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
public class SituationFamilialeAdminServiceImpl implements SituationFamilialeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public SituationFamiliale update(SituationFamiliale t) {
        SituationFamiliale loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{SituationFamiliale.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public SituationFamiliale findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public SituationFamiliale findOrSave(SituationFamiliale t) {
        if (t != null) {
            SituationFamiliale result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<SituationFamiliale> findAll() {
        return dao.findAll();
    }

    public List<SituationFamiliale> findByCriteria(SituationFamilialeCriteria criteria) {
        List<SituationFamiliale> content = null;
        if (criteria != null) {
            SituationFamilialeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private SituationFamilialeSpecification constructSpecification(SituationFamilialeCriteria criteria) {
        SituationFamilialeSpecification mySpecification =  (SituationFamilialeSpecification) RefelexivityUtil.constructObjectUsingOneParam(SituationFamilialeSpecification.class, criteria);
        return mySpecification;
    }

    public List<SituationFamiliale> findPaginatedByCriteria(SituationFamilialeCriteria criteria, int page, int pageSize, String order, String sortField) {
        SituationFamilialeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(SituationFamilialeCriteria criteria) {
        SituationFamilialeSpecification mySpecification = constructSpecification(criteria);
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
    public List<SituationFamiliale> delete(List<SituationFamiliale> list) {
		List<SituationFamiliale> result = new ArrayList();
        if (list != null) {
            for (SituationFamiliale t : list) {
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
    public SituationFamiliale create(SituationFamiliale t) {
        SituationFamiliale loaded = findByReferenceEntity(t);
        SituationFamiliale saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public SituationFamiliale findWithAssociatedLists(Long id){
        SituationFamiliale result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<SituationFamiliale> update(List<SituationFamiliale> ts, boolean createIfNotExist) {
        List<SituationFamiliale> result = new ArrayList<>();
        if (ts != null) {
            for (SituationFamiliale t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    SituationFamiliale loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, SituationFamiliale t, SituationFamiliale loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public SituationFamiliale findByReferenceEntity(SituationFamiliale t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<SituationFamiliale> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<SituationFamiliale>> getToBeSavedAndToBeDeleted(List<SituationFamiliale> oldList, List<SituationFamiliale> newList) {
        List<List<SituationFamiliale>> result = new ArrayList<>();
        List<SituationFamiliale> resultDelete = new ArrayList<>();
        List<SituationFamiliale> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<SituationFamiliale> oldList, List<SituationFamiliale> newList, List<SituationFamiliale> resultUpdateOrSave, List<SituationFamiliale> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                SituationFamiliale myOld = oldList.get(i);
                SituationFamiliale t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                SituationFamiliale myNew = newList.get(i);
                SituationFamiliale t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public SituationFamilialeAdminServiceImpl(SituationFamilialeDao dao) {
        this.dao = dao;
    }

    private SituationFamilialeDao dao;
}
