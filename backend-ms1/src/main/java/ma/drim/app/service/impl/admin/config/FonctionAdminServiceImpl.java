package ma.drim.app.service.impl.admin.config;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.dao.criteria.core.config.FonctionCriteria;
import ma.drim.app.dao.facade.core.config.FonctionDao;
import ma.drim.app.dao.specification.core.config.FonctionSpecification;
import ma.drim.app.service.facade.admin.config.FonctionAdminService;
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
public class FonctionAdminServiceImpl implements FonctionAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Fonction update(Fonction t) {
        Fonction loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Fonction.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Fonction findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Fonction findOrSave(Fonction t) {
        if (t != null) {
            Fonction result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Fonction> findAll() {
        return dao.findAll();
    }

    public List<Fonction> findByCriteria(FonctionCriteria criteria) {
        List<Fonction> content = null;
        if (criteria != null) {
            FonctionSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private FonctionSpecification constructSpecification(FonctionCriteria criteria) {
        FonctionSpecification mySpecification =  (FonctionSpecification) RefelexivityUtil.constructObjectUsingOneParam(FonctionSpecification.class, criteria);
        return mySpecification;
    }

    public List<Fonction> findPaginatedByCriteria(FonctionCriteria criteria, int page, int pageSize, String order, String sortField) {
        FonctionSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(FonctionCriteria criteria) {
        FonctionSpecification mySpecification = constructSpecification(criteria);
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
    public List<Fonction> delete(List<Fonction> list) {
		List<Fonction> result = new ArrayList();
        if (list != null) {
            for (Fonction t : list) {
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
    public Fonction create(Fonction t) {
        Fonction loaded = findByReferenceEntity(t);
        Fonction saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Fonction findWithAssociatedLists(Long id){
        Fonction result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Fonction> update(List<Fonction> ts, boolean createIfNotExist) {
        List<Fonction> result = new ArrayList<>();
        if (ts != null) {
            for (Fonction t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Fonction loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Fonction t, Fonction loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Fonction findByReferenceEntity(Fonction t){
        return t==null? null : dao.findByCode(t.getCode());
    }



    public List<Fonction> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Fonction>> getToBeSavedAndToBeDeleted(List<Fonction> oldList, List<Fonction> newList) {
        List<List<Fonction>> result = new ArrayList<>();
        List<Fonction> resultDelete = new ArrayList<>();
        List<Fonction> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Fonction> oldList, List<Fonction> newList, List<Fonction> resultUpdateOrSave, List<Fonction> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Fonction myOld = oldList.get(i);
                Fonction t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Fonction myNew = newList.get(i);
                Fonction t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }








    public FonctionAdminServiceImpl(FonctionDao dao) {
        this.dao = dao;
    }

    private FonctionDao dao;
}
