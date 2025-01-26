package  ma.drim.app.dao.specification.core.diplome;

import ma.drim.app.dao.criteria.core.diplome.TypeDiplomeCriteria;
import ma.drim.app.bean.core.diplome.TypeDiplome;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class TypeDiplomeSpecification extends  AbstractSpecification<TypeDiplomeCriteria, TypeDiplome>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public TypeDiplomeSpecification(TypeDiplomeCriteria criteria) {
        super(criteria);
    }

    public TypeDiplomeSpecification(TypeDiplomeCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
