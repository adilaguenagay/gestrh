package ma.drim.app.service.impl.admin.config;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.dao.criteria.core.config.LocalCriteria;
import ma.drim.app.dao.facade.core.config.LocalDao;
import ma.drim.app.dao.specification.core.config.LocalSpecification;
import ma.drim.app.service.facade.admin.config.LocalAdminService;
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
public class LocalAdminServiceImpl implements LocalAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Local update(Local t) {
        Local loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Local.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Local findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Local findOrSave(Local t) {
        if (t != null) {
            Local result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Local> findAll() {
        return dao.findAll();
    }

    public List<Local> findByCriteria(LocalCriteria criteria) {
        List<Local> content = null;
        if (criteria != null) {
            LocalSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private LocalSpecification constructSpecification(LocalCriteria criteria) {
        LocalSpecification mySpecification =  (LocalSpecification) RefelexivityUtil.constructObjectUsingOneParam(LocalSpecification.class, criteria);
        return mySpecification;
    }

    public List<Local> findPaginatedByCriteria(LocalCriteria criteria, int page, int pageSize, String order, String sortField) {
        LocalSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(LocalCriteria criteria) {
        LocalSpecification mySpecification = constructSpecification(criteria);
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
    public List<Local> delete(List<Local> list) {
		List<Local> result = new ArrayList();
        if (list != null) {
            for (Local t : list) {
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
    public Local create(Local t) {
        Local loaded = findByReferenceEntity(t);
        Local saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Local findWithAssociatedLists(Long id){
        Local result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Local> update(List<Local> ts, boolean createIfNotExist) {
        List<Local> result = new ArrayList<>();
        if (ts != null) {
            for (Local t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Local loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Local t, Local loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Local findByReferenceEntity(Local t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Local> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Local>> getToBeSavedAndToBeDeleted(List<Local> oldList, List<Local> newList) {
        List<List<Local>> result = new ArrayList<>();
        List<Local> resultDelete = new ArrayList<>();
        List<Local> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Local> oldList, List<Local> newList, List<Local> resultUpdateOrSave, List<Local> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Local myOld = oldList.get(i);
                Local t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Local myNew = newList.get(i);
                Local t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public LocalAdminServiceImpl(LocalDao dao) {
        this.dao = dao;
    }

    private LocalDao dao;
}
