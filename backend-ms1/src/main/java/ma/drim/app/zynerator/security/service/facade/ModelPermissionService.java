package ma.drim.app.zynerator.security.service.facade;

import ma.drim.app.zynerator.security.bean.ModelPermission;
import ma.drim.app.zynerator.security.dao.criteria.core.ModelPermissionCriteria;
import ma.drim.app.zynerator.service.IService;
import java.util.List;



public interface ModelPermissionService extends  IService<ModelPermission,ModelPermissionCriteria>  {
    List<ModelPermission> findAllOptimized();

}
