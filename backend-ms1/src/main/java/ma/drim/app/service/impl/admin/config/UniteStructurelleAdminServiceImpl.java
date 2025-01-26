package ma.drim.app.service.impl.admin.config;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.dao.criteria.core.config.UniteStructurelleCriteria;
import ma.drim.app.dao.facade.core.config.UniteStructurelleDao;
import ma.drim.app.dao.specification.core.config.UniteStructurelleSpecification;
import ma.drim.app.service.facade.admin.config.UniteStructurelleAdminService;
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

import java.util.List;
@Service
public class UniteStructurelleAdminServiceImpl implements UniteStructurelleAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public UniteStructurelle update(UniteStructurelle t) {
        UniteStructurelle loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{UniteStructurelle.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public UniteStructurelle findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public UniteStructurelle findOrSave(UniteStructurelle t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            UniteStructurelle result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<UniteStructurelle> findAll() {
        return dao.findAll();
    }

    public List<UniteStructurelle> findByCriteria(UniteStructurelleCriteria criteria) {
        List<UniteStructurelle> content = null;
        if (criteria != null) {
            UniteStructurelleSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private UniteStructurelleSpecification constructSpecification(UniteStructurelleCriteria criteria) {
        UniteStructurelleSpecification mySpecification =  (UniteStructurelleSpecification) RefelexivityUtil.constructObjectUsingOneParam(UniteStructurelleSpecification.class, criteria);
        return mySpecification;
    }

    public List<UniteStructurelle> findPaginatedByCriteria(UniteStructurelleCriteria criteria, int page, int pageSize, String order, String sortField) {
        UniteStructurelleSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(UniteStructurelleCriteria criteria) {
        UniteStructurelleSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<UniteStructurelle> findByUniteMereId(Long id){
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
            deleteAssociatedLists(id);
            dao.deleteById(id);
        }
        return condition;
    }

    public void deleteAssociatedLists(Long id) {
        employeeService.deleteByUniteStructurelleId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<UniteStructurelle> delete(List<UniteStructurelle> list) {
		List<UniteStructurelle> result = new ArrayList();
        if (list != null) {
            for (UniteStructurelle t : list) {
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
    public UniteStructurelle create(UniteStructurelle t) {
        UniteStructurelle loaded = findByReferenceEntity(t);
        UniteStructurelle saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getEmployee() != null) {
                t.getEmployee().forEach(element-> {
                    element.setUniteStructurelle(saved);
                    employeeService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public UniteStructurelle findWithAssociatedLists(Long id){
        UniteStructurelle result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setEmployee(employeeService.findByUniteStructurelleId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<UniteStructurelle> update(List<UniteStructurelle> ts, boolean createIfNotExist) {
        List<UniteStructurelle> result = new ArrayList<>();
        if (ts != null) {
            for (UniteStructurelle t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    UniteStructurelle loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, UniteStructurelle t, UniteStructurelle loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(UniteStructurelle uniteStructurelle){
    if(uniteStructurelle !=null && uniteStructurelle.getId() != null){
        List<List<Employee>> resultEmployee= employeeService.getToBeSavedAndToBeDeleted(employeeService.findByUniteStructurelleId(uniteStructurelle.getId()),uniteStructurelle.getEmployee());
            employeeService.delete(resultEmployee.get(1));
        emptyIfNull(resultEmployee.get(0)).forEach(e -> e.setUniteStructurelle(uniteStructurelle));
        employeeService.update(resultEmployee.get(0),true);
        }
    }








    public UniteStructurelle findByReferenceEntity(UniteStructurelle t){
        return t==null? null : dao.findByCode(t.getCode());
    }
    public void findOrSaveAssociatedObject(UniteStructurelle t){
        if( t != null) {
            t.setUniteMere(findOrSave(t.getUniteMere()));
        }
    }



    public List<UniteStructurelle> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<UniteStructurelle>> getToBeSavedAndToBeDeleted(List<UniteStructurelle> oldList, List<UniteStructurelle> newList) {
        List<List<UniteStructurelle>> result = new ArrayList<>();
        List<UniteStructurelle> resultDelete = new ArrayList<>();
        List<UniteStructurelle> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<UniteStructurelle> oldList, List<UniteStructurelle> newList, List<UniteStructurelle> resultUpdateOrSave, List<UniteStructurelle> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                UniteStructurelle myOld = oldList.get(i);
                UniteStructurelle t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                UniteStructurelle myNew = newList.get(i);
                UniteStructurelle t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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

    public UniteStructurelleAdminServiceImpl(UniteStructurelleDao dao) {
        this.dao = dao;
    }

    private UniteStructurelleDao dao;
}
