package  ma.drim.app.dao.specification.core.diplome;

import ma.drim.app.dao.criteria.core.diplome.DiplomeCriteria;
import ma.drim.app.bean.core.diplome.Diplome;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class DiplomeSpecification extends  AbstractSpecification<DiplomeCriteria, Diplome>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateInt("anneeObtention", criteria.getAnneeObtention(), criteria.getAnneeObtentionMin(), criteria.getAnneeObtentionMax());
        addPredicate("specialie", criteria.getSpecialie(),criteria.getSpecialieLike());
        addPredicate("organisme", criteria.getOrganisme(),criteria.getOrganismeLike());
        addPredicateFk("type","id", criteria.getType()==null?null:criteria.getType().getId());
        addPredicateFk("type","id", criteria.getTypes());
        addPredicateFk("employee","id", criteria.getEmployee()==null?null:criteria.getEmployee().getId());
        addPredicateFk("employee","id", criteria.getEmployees());
        addPredicateFk("employee","ppr", criteria.getEmployee()==null?null:criteria.getEmployee().getPpr());
    }

    public DiplomeSpecification(DiplomeCriteria criteria) {
        super(criteria);
    }

    public DiplomeSpecification(DiplomeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
