package  ma.drim.app.dao.specification.core.employee;

import ma.drim.app.dao.criteria.core.employee.EmployeeCriteria;
import ma.drim.app.bean.core.employee.Employee;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class EmployeeSpecification extends  AbstractSpecification<EmployeeCriteria, Employee>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("ppr", criteria.getPpr(),criteria.getPprLike());
        addPredicate("nom", criteria.getNom(),criteria.getNomLike());
        addPredicate("prenom", criteria.getPrenom(),criteria.getPrenomLike());
        addPredicate("lieuNaissance", criteria.getLieuNaissance(),criteria.getLieuNaissanceLike());
        addPredicate("photoUrl", criteria.getPhotoUrl(),criteria.getPhotoUrlLike());
        addPredicate("telephone", criteria.getTelephone(),criteria.getTelephoneLike());
        addPredicate("address", criteria.getAddress(),criteria.getAddressLike());
        addPredicate("email", criteria.getEmail(),criteria.getEmailLike());
        addPredicateFk("local","id", criteria.getLocal()==null?null:criteria.getLocal().getId());
        addPredicateFk("local","id", criteria.getLocals());
        addPredicateFk("local","code", criteria.getLocal()==null?null:criteria.getLocal().getCode());
        addPredicateFk("situationFamiliale","id", criteria.getSituationFamiliale()==null?null:criteria.getSituationFamiliale().getId());
        addPredicateFk("situationFamiliale","id", criteria.getSituationFamiliales());
        addPredicateFk("situationFamiliale","code", criteria.getSituationFamiliale()==null?null:criteria.getSituationFamiliale().getCode());
        addPredicateFk("genre","id", criteria.getGenre()==null?null:criteria.getGenre().getId());
        addPredicateFk("genre","id", criteria.getGenres());
        addPredicateFk("genre","code", criteria.getGenre()==null?null:criteria.getGenre().getCode());
        addPredicateFk("grade","id", criteria.getGrade()==null?null:criteria.getGrade().getId());
        addPredicateFk("grade","id", criteria.getGrades());
        addPredicateFk("grade","code", criteria.getGrade()==null?null:criteria.getGrade().getCode());
        addPredicateFk("fonction","id", criteria.getFonction()==null?null:criteria.getFonction().getId());
        addPredicateFk("fonction","id", criteria.getFonctions());
        addPredicateFk("fonction","code", criteria.getFonction()==null?null:criteria.getFonction().getCode());
        addPredicateFk("uniteStructurelle","id", criteria.getUniteStructurelle()==null?null:criteria.getUniteStructurelle().getId());
        addPredicateFk("uniteStructurelle","id", criteria.getUniteStructurelles());
        addPredicateFk("uniteStructurelle","code", criteria.getUniteStructurelle()==null?null:criteria.getUniteStructurelle().getCode());
    }

    public EmployeeSpecification(EmployeeCriteria criteria) {
        super(criteria);
    }

    public EmployeeSpecification(EmployeeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
