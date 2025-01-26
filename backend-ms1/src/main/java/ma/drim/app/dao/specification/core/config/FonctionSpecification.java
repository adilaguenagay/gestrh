package  ma.drim.app.dao.specification.core.config;

import ma.drim.app.dao.criteria.core.config.FonctionCriteria;
import ma.drim.app.bean.core.config.Fonction;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class FonctionSpecification extends  AbstractSpecification<FonctionCriteria, Fonction>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public FonctionSpecification(FonctionCriteria criteria) {
        super(criteria);
    }

    public FonctionSpecification(FonctionCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
