package  ma.drim.app.dao.specification.core.affectation;

import ma.drim.app.dao.criteria.core.affectation.AffectationCriteria;
import ma.drim.app.bean.core.affectation.Affectation;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class AffectationSpecification extends  AbstractSpecification<AffectationCriteria, Affectation>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicateFk("employee","id", criteria.getEmployee()==null?null:criteria.getEmployee().getId());
        addPredicateFk("employee","id", criteria.getEmployees());
        addPredicateFk("employee","ppr", criteria.getEmployee()==null?null:criteria.getEmployee().getPpr());
        addPredicateFk("uniteMere","id", criteria.getUniteMere()==null?null:criteria.getUniteMere().getId());
        addPredicateFk("uniteMere","id", criteria.getUniteMeres());
        addPredicateFk("uniteMere","code", criteria.getUniteMere()==null?null:criteria.getUniteMere().getCode());
    }

    public AffectationSpecification(AffectationCriteria criteria) {
        super(criteria);
    }

    public AffectationSpecification(AffectationCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
