package  ma.drim.app.dao.specification.core.config;

import ma.drim.app.dao.criteria.core.config.GenreCriteria;
import ma.drim.app.bean.core.config.Genre;
import ma.drim.app.zynerator.specification.AbstractSpecification;


public class GenreSpecification extends  AbstractSpecification<GenreCriteria, Genre>  {

    @Override
    public void constructPredicates() {
        addPredicateId("id", criteria);
        addPredicate("code", criteria.getCode(),criteria.getCodeLike());
        addPredicate("libelle", criteria.getLibelle(),criteria.getLibelleLike());
    }

    public GenreSpecification(GenreCriteria criteria) {
        super(criteria);
    }

    public GenreSpecification(GenreCriteria criteria, boolean distinct) {
        super(criteria, distinct);
    }

}
