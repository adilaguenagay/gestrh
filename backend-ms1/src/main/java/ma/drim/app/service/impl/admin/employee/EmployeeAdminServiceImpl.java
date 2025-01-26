package ma.drim.app.service.impl.admin.employee;


import ma.drim.app.zynerator.exception.EntityNotFoundException;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.dao.criteria.core.employee.EmployeeCriteria;
import ma.drim.app.dao.facade.core.employee.EmployeeDao;
import ma.drim.app.dao.specification.core.employee.EmployeeSpecification;
import ma.drim.app.service.facade.admin.employee.EmployeeAdminService;
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

import ma.drim.app.service.facade.admin.config.SituationFamilialeAdminService ;
import ma.drim.app.bean.core.config.SituationFamiliale ;
import ma.drim.app.service.facade.admin.diplome.DiplomeAdminService ;
import ma.drim.app.bean.core.diplome.Diplome ;
import ma.drim.app.service.facade.admin.config.FonctionAdminService ;
import ma.drim.app.bean.core.config.Fonction ;
import ma.drim.app.service.facade.admin.config.LocalAdminService ;
import ma.drim.app.bean.core.config.Local ;
import ma.drim.app.service.facade.admin.config.GenreAdminService ;
import ma.drim.app.bean.core.config.Genre ;
import ma.drim.app.service.facade.admin.config.GradeAdminService ;
import ma.drim.app.bean.core.config.Grade ;
import ma.drim.app.service.facade.admin.config.UniteStructurelleAdminService ;
import ma.drim.app.bean.core.config.UniteStructurelle ;

import java.util.List;
@Service
public class EmployeeAdminServiceImpl implements EmployeeAdminService {

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Employee update(Employee t) {
        Employee loadedItem = dao.findById(t.getId()).orElse(null);
        if (loadedItem == null) {
            throw new EntityNotFoundException("errors.notFound", new String[]{Employee.class.getSimpleName(), t.getId().toString()});
        } else {
            updateWithAssociatedLists(t);
            dao.save(t);
            return loadedItem;
        }
    }

    public Employee findById(Long id) {
        return dao.findById(id).orElse(null);
    }


    public Employee findOrSave(Employee t) {
        if (t != null) {
            findOrSaveAssociatedObject(t);
            Employee result = findByReferenceEntity(t);
            if (result == null) {
                return dao.save(t);
            } else {
                return result;
            }
        }
        return null;
    }

    public List<Employee> findAll() {
        return dao.findAll();
    }

    public List<Employee> findByCriteria(EmployeeCriteria criteria) {
        List<Employee> content = null;
        if (criteria != null) {
            EmployeeSpecification mySpecification = constructSpecification(criteria);
            content = dao.findAll(mySpecification);
        } else {
            content = dao.findAll();
        }
        return content;

    }


    private EmployeeSpecification constructSpecification(EmployeeCriteria criteria) {
        EmployeeSpecification mySpecification =  (EmployeeSpecification) RefelexivityUtil.constructObjectUsingOneParam(EmployeeSpecification.class, criteria);
        return mySpecification;
    }

    public List<Employee> findPaginatedByCriteria(EmployeeCriteria criteria, int page, int pageSize, String order, String sortField) {
        EmployeeSpecification mySpecification = constructSpecification(criteria);
        order = (order != null && !order.isEmpty()) ? order : "desc";
        sortField = (sortField != null && !sortField.isEmpty()) ? sortField : "id";
        Pageable pageable = PageRequest.of(page, pageSize, Sort.Direction.fromString(order), sortField);
        return dao.findAll(mySpecification, pageable).getContent();
    }

    public int getDataSize(EmployeeCriteria criteria) {
        EmployeeSpecification mySpecification = constructSpecification(criteria);
        mySpecification.setDistinct(true);
        return ((Long) dao.count(mySpecification)).intValue();
    }

    public List<Employee> findByLocalId(Long id){
        return dao.findByLocalId(id);
    }
    public int deleteByLocalId(Long id){
        return dao.deleteByLocalId(id);
    }
    public long countByLocalCode(String code){
        return dao.countByLocalCode(code);
    }
    public List<Employee> findBySituationFamilialeId(Long id){
        return dao.findBySituationFamilialeId(id);
    }
    public int deleteBySituationFamilialeId(Long id){
        return dao.deleteBySituationFamilialeId(id);
    }
    public long countBySituationFamilialeCode(String code){
        return dao.countBySituationFamilialeCode(code);
    }
    public List<Employee> findByGenreId(Long id){
        return dao.findByGenreId(id);
    }
    public int deleteByGenreId(Long id){
        return dao.deleteByGenreId(id);
    }
    public long countByGenreCode(String code){
        return dao.countByGenreCode(code);
    }
    public List<Employee> findByGradeId(Long id){
        return dao.findByGradeId(id);
    }
    public int deleteByGradeId(Long id){
        return dao.deleteByGradeId(id);
    }
    public long countByGradeCode(String code){
        return dao.countByGradeCode(code);
    }
    public List<Employee> findByFonctionId(Long id){
        return dao.findByFonctionId(id);
    }
    public int deleteByFonctionId(Long id){
        return dao.deleteByFonctionId(id);
    }
    public long countByFonctionCode(String code){
        return dao.countByFonctionCode(code);
    }
    public List<Employee> findByUniteStructurelleId(Long id){
        return dao.findByUniteStructurelleId(id);
    }
    public int deleteByUniteStructurelleId(Long id){
        return dao.deleteByUniteStructurelleId(id);
    }
    public long countByUniteStructurelleCode(String code){
        return dao.countByUniteStructurelleCode(code);
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
        diplomeService.deleteByEmployeeId(id);
    }




    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Employee> delete(List<Employee> list) {
		List<Employee> result = new ArrayList();
        if (list != null) {
            for (Employee t : list) {
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
    public Employee create(Employee t) {
        Employee loaded = findByReferenceEntity(t);
        Employee saved;
        if (loaded == null) {
            saved = dao.save(t);
            if (t.getDiplome() != null) {
                t.getDiplome().forEach(element-> {
                    element.setEmployee(saved);
                    diplomeService.create(element);
                });
            }
        }else {
            saved = null;
        }
        return saved;
    }

    public Employee findWithAssociatedLists(Long id){
        Employee result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setDiplome(diplomeService.findByEmployeeId(id));
        }
        return result;
    }

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public List<Employee> update(List<Employee> ts, boolean createIfNotExist) {
        List<Employee> result = new ArrayList<>();
        if (ts != null) {
            for (Employee t : ts) {
                if (t.getId() == null) {
                    dao.save(t);
                } else {
                    Employee loadedItem = dao.findById(t.getId()).orElse(null);
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


    private boolean isEligibleForCreateOrUpdate(boolean createIfNotExist, Employee t, Employee loadedItem) {
        boolean eligibleForCreateCrud = t.getId() == null;
        boolean eligibleForCreate = (createIfNotExist && (t.getId() == null || loadedItem == null));
        boolean eligibleForUpdate = (t.getId() != null && loadedItem != null);
        return (eligibleForCreateCrud || eligibleForCreate || eligibleForUpdate);
    }

    public void updateWithAssociatedLists(Employee employee){
    if(employee !=null && employee.getId() != null){
        List<List<Diplome>> resultDiplome= diplomeService.getToBeSavedAndToBeDeleted(diplomeService.findByEmployeeId(employee.getId()),employee.getDiplome());
            diplomeService.delete(resultDiplome.get(1));
        emptyIfNull(resultDiplome.get(0)).forEach(e -> e.setEmployee(employee));
        diplomeService.update(resultDiplome.get(0),true);
        }
    }








    public Employee findByReferenceEntity(Employee t){
        return t==null? null : dao.findByPpr(t.getPpr());
    }
    public void findOrSaveAssociatedObject(Employee t){
        if( t != null) {
            t.setLocal(localService.findOrSave(t.getLocal()));
            t.setSituationFamiliale(situationFamilialeService.findOrSave(t.getSituationFamiliale()));
            t.setGenre(genreService.findOrSave(t.getGenre()));
            t.setGrade(gradeService.findOrSave(t.getGrade()));
            t.setFonction(fonctionService.findOrSave(t.getFonction()));
            t.setUniteStructurelle(uniteStructurelleService.findOrSave(t.getUniteStructurelle()));
        }
    }



    public List<Employee> findAllOptimized() {
        return dao.findAllOptimized();
    }

    @Override
    public List<List<Employee>> getToBeSavedAndToBeDeleted(List<Employee> oldList, List<Employee> newList) {
        List<List<Employee>> result = new ArrayList<>();
        List<Employee> resultDelete = new ArrayList<>();
        List<Employee> resultUpdateOrSave = new ArrayList<>();
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

    private void extractToBeSaveOrDelete(List<Employee> oldList, List<Employee> newList, List<Employee> resultUpdateOrSave, List<Employee> resultDelete) {
		for (int i = 0; i < oldList.size(); i++) {
                Employee myOld = oldList.get(i);
                Employee t = newList.stream().filter(e -> myOld.equals(e)).findFirst().orElse(null);
                if (t != null) {
                    resultUpdateOrSave.add(t); // update
                } else {
                    resultDelete.add(myOld);
                }
            }
            for (int i = 0; i < newList.size(); i++) {
                Employee myNew = newList.get(i);
                Employee t = oldList.stream().filter(e -> myNew.equals(e)).findFirst().orElse(null);
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
    private SituationFamilialeAdminService situationFamilialeService ;
    @Autowired
    private DiplomeAdminService diplomeService ;
    @Autowired
    private FonctionAdminService fonctionService ;
    @Autowired
    private LocalAdminService localService ;
    @Autowired
    private GenreAdminService genreService ;
    @Autowired
    private GradeAdminService gradeService ;
    @Autowired
    private UniteStructurelleAdminService uniteStructurelleService ;

    public EmployeeAdminServiceImpl(EmployeeDao dao) {
        this.dao = dao;
    }

    private EmployeeDao dao;
}
