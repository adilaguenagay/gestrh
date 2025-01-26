package ma.drim.app.zynerator.service;

import ma.drim.app.zynerator.bean.BaseEntity;
import ma.drim.app.zynerator.criteria.BaseCriteria;
import ma.drim.app.zynerator.repository.AbstractRepository;

public abstract class AbstractReferentielServiceImpl<T extends BaseEntity, CRITERIA extends BaseCriteria, REPO extends AbstractRepository<T, Long>> extends AbstractServiceImpl<T, CRITERIA, REPO> {

    public AbstractReferentielServiceImpl(REPO dao) {
        super(dao);
    }

}
