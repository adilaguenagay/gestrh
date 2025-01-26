package  ma.drim.app.dao.specification.core.config;

import ma.drim.app.dao.criteria.core.config.LocalCriteria;
import ma.drim.app.bean.core.config.Local;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class LocalSpecification extends  AbstractSpecification<LocalCriteria, Local>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public LocalSpecification(LocalCriteria criteria) {
        super(criteria);
    }

    public LocalSpecification(LocalCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
