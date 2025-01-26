package  ma.drim.app.dao.specification.core.config;

import ma.drim.app.dao.criteria.core.config.GradeCriteria;
import ma.drim.app.bean.core.config.Grade;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class GradeSpecification extends  AbstractSpecification<GradeCriteria, Grade>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public GradeSpecification(GradeCriteria criteria) {
        super(criteria);
    }

    public GradeSpecification(GradeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
