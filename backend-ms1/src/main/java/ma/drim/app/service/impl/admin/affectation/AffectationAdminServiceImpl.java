package ma.drim.app.service.impl.admin.affectation;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.dao.criteria.core.affectation.AffectationCriteria;
import ma.drim.app.dao.facade.core.affectation.AffectationDao;
import ma.drim.app.dao.specification.core.affectation.AffectationSpecification;
import ma.drim.app.service.facade.admin.affectation.AffectationAdminService;
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

import ma.drim.app.service.facade.admin.employee.EmployeeAdminService ;
import ma.drim.app.bean.core.employee.Employee ;
import ma.drim.app.service.facade.admin.config.UniteStructurelleAdminService ;
import ma.drim.app.bean.core.config.UniteStructurelle ;

import java.util.List;
@Service
public class AffectationAdminServiceImpl implements AffectationAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Affectation update(Affectation t) {
        Affectation loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Affectation.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Affectation findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Affectation findOrSave(Affectation t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Affectation result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Affectation> findAll() {
        return dao.findAll();
    }

    public List<Affectation> findByCriteria(AffectationCriteria criteria) {
        List<Affectation> content = null;
        if (criteria != null) {
            AffectationSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private AffectationSpecification constructSpecification(AffectationCriteria criteria) {
        AffectationSpecification mySpecification =  (AffectationSpecification) RefelexivityUtil.constructObjectUsingOneParam(AffectationSpecification.class, criteria);
        return mySpecification;
    }

    public List<Affectation> findPaginatedByCriteria(AffectationCriteria criteria, int page, int pageSize, String order, String sortField) {
        AffectationSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(AffectationCriteria criteria) {
        AffectationSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Affectation> findByEmployeeId(Long id){
        return dao.findByEmployeeId(id);
    }
    public int deleteByEmployeeId(Long id){
        return dao.deleteByEmployeeId(id);
    }
    public long countByEmployeePpr(String ppr){
        return dao.countByEmployeePpr(ppr);
    }
    public List<Affectation> findByUniteMereId(Long id){
        return dao.findByUniteMereId(id);
    }
    public int deleteByUniteMereId(Long id){
        return dao.deleteByUniteMereId(id);
    }
    public long countByUniteMereCode(String code){
        return dao.countByUniteMereCode(code);
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
    public List<Affectation> delete(List<Affectation> list) {
		List<Affectation> result = new ArrayList();
        if (list != null) {
            for (Affectation t : list) {
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
    public Affectation create(Affectation t) {
        Affectation loaded = findByReferenceEntity(t);
        Affectation saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Affectation findWithAssociatedLists(Long id){
        Affectation result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Affectation> update(List<Affectation> ts, boolean createIfNotExist) {
        List<Affectation> result = new ArrayList<>();
        if (ts != null) {
            for (Affectation t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Affectation loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Affectation t, Affectation loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Affectation findByReferenceEntity(Affectation t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Affectation t){
        if( t != null) {
            t.setEmployee(employeeService.findOrSave(t.getEmployee()));
            t.setUniteMere(uniteStructurelleService.findOrSave(t.getUniteMere()));
        }
    }



    public List<Affectation> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Affectation>> getToBeSavedAndToBeDeleted(List<Affectation> oldList, List<Affectation> newList) {
        List<List<Affectation>> result = new ArrayList<>();
        List<Affectation> resultDelete = new ArrayList<>();
        List<Affectation> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Affectation> oldList, List<Affectation> newList, List<Affectation> resultUpdateOrSave, List<Affectation> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Affectation myOld = oldList.get(i);
                Affectation t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Affectation myNew = newList.get(i);
                Affectation t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
                if (t == null) {
                    resultUpdateOrSave.add(myNew); // create
                }
            }
	}

    @Override
    public String uploadFile(String checksumOld, String tempUpladedFile, String destinationFilePath) throws Exception {
        return null;
    }







    @Autowired
    private EmployeeAdminService employeeService ;
    @Autowired
    private UniteStructurelleAdminService uniteStructurelleService ;

    public AffectationAdminServiceImpl(AffectationDao dao) {
        this.dao = dao;
    }

    private AffectationDao dao;
}
