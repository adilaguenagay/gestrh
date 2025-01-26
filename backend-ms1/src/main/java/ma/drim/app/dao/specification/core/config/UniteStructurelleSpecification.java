package  ma.drim.app.dao.specification.core.config;

import ma.drim.app.dao.criteria.core.config.UniteStructurelleCriteria;
import ma.drim.app.bean.core.config.UniteStructurelle;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class UniteStructurelleSpecification extends  AbstractSpecification<UniteStructurelleCriteria, UniteStructurelle>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
        addPredicateFk("uniteMere","id", criteria.getUniteMere()==null?null:criteria.getUniteMere().getId());
        addPredicateFk("uniteMere","id", criteria.getUniteMeres());
        addPredicateFk("uniteMere","code", criteria.getUniteMere()==null?null:criteria.getUniteMere().getCode());
    }

    public UniteStructurelleSpecification(UniteStructurelleCriteria criteria) {
        super(criteria);
    }

    public UniteStructurelleSpecification(UniteStructurelleCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
