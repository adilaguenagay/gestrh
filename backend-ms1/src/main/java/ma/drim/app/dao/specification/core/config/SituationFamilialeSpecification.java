package  ma.drim.app.dao.specification.core.config;

import ma.drim.app.dao.criteria.core.config.SituationFamilialeCriteria;
import ma.drim.app.bean.core.config.SituationFamiliale;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class SituationFamilialeSpecification extends  AbstractSpecification<SituationFamilialeCriteria, SituationFamiliale>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public SituationFamilialeSpecification(SituationFamilialeCriteria criteria) {
        super(criteria);
    }

    public SituationFamilialeSpecification(SituationFamilialeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
