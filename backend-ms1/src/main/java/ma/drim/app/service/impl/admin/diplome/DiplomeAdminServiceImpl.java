package ma.drim.app.service.impl.admin.diplome;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.dao.criteria.core.diplome.DiplomeCriteria;
import ma.drim.app.dao.facade.core.diplome.DiplomeDao;
import ma.drim.app.dao.specification.core.diplome.DiplomeSpecification;
import ma.drim.app.service.facade.admin.diplome.DiplomeAdminService;
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
import ma.drim.app.service.facade.admin.diplome.TypeDiplomeAdminService ;
import ma.drim.app.bean.core.diplome.TypeDiplome ;

import java.util.List;
@Service
public class DiplomeAdminServiceImpl implements DiplomeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Diplome update(Diplome t) {
        Diplome loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Diplome.class.getSimpleName(), t.getId().toString()});
        } else {
            dao.save(t);
            return loadedItem;
        }
    }

    public Diplome findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Diplome findOrSave(Diplome t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Diplome result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Diplome> findAll() {
        return dao.findAll();
    }

    public List<Diplome> findByCriteria(DiplomeCriteria criteria) {
        List<Diplome> content = null;
        if (criteria != null) {
            DiplomeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private DiplomeSpecification constructSpecification(DiplomeCriteria criteria) {
        DiplomeSpecification mySpecification =  (DiplomeSpecification) RefelexivityUtil.constructObjectUsingOneParam(DiplomeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Diplome> findPaginatedByCriteria(DiplomeCriteria criteria, int page, int pageSize, String order, String sortField) {
        DiplomeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(DiplomeCriteria criteria) {
        DiplomeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Diplome> findByTypeId(Long id){
        return dao.findByTypeId(id);
    }
    public int deleteByTypeId(Long id){
        return dao.deleteByTypeId(id);
    }
    public long countByTypeId(Long id){
        return dao.countByTypeId(id);
    }
    public List<Diplome> findByEmployeeId(Long id){
        return dao.findByEmployeeId(id);
    }
    public int deleteByEmployeeId(Long id){
        return dao.deleteByEmployeeId(id);
    }
    public long countByEmployeePpr(String ppr){
        return dao.countByEmployeePpr(ppr);
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
    public List<Diplome> delete(List<Diplome> list) {
		List<Diplome> result = new ArrayList();
        if (list != null) {
            for (Diplome t : list) {
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
    public Diplome create(Diplome t) {
        Diplome loaded = findByReferenceEntity(t);
        Diplome saved;
        if (loaded == null) {
            saved = dao.save(t);
        }else {
            saved = null;
        }
        return saved;
    }

    public Diplome findWithAssociatedLists(Long id){
        Diplome result = dao.findById(id).orElse(null);
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Diplome> update(List<Diplome> ts, boolean createIfNotExist) {
        List<Diplome> result = new ArrayList<>();
        if (ts != null) {
            for (Diplome t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Diplome loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Diplome t, Diplome loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }









    public Diplome findByReferenceEntity(Diplome t) {
        return t == null || t.getId() == null ? null : findById(t.getId());
    }
    public void findOrSaveAssociatedObject(Diplome t){
        if( t != null) {
            t.setType(typeDiplomeService.findOrSave(t.getType()));
            t.setEmployee(employeeService.findOrSave(t.getEmployee()));
        }
    }



    public List<Diplome> findAllOptimized() {
        return dao.findAll();
    }

    @Override
    public List<List<Diplome>> getToBeSavedAndToBeDeleted(List<Diplome> oldList, List<Diplome> newList) {
        List<List<Diplome>> result = new ArrayList<>();
        List<Diplome> resultDelete = new ArrayList<>();
        List<Diplome> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Diplome> oldList, List<Diplome> newList, List<Diplome> resultUpdateOrSave, List<Diplome> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Diplome myOld = oldList.get(i);
                Diplome t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Diplome myNew = newList.get(i);
                Diplome t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private TypeDiplomeAdminService typeDiplomeService ;

    public DiplomeAdminServiceImpl(DiplomeDao dao) {
        this.dao = dao;
    }

    private DiplomeDao dao;
}
