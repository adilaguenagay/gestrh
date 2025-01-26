package ma.drim.app.zynerator.security.service.facade;

import ma.drim.app.zynerator.security.bean.ActionPermission;
import ma.drim.app.zynerator.security.dao.criteria.core.ActionPermissionCriteria;
import ma.drim.app.zynerator.service.IService;
import java.util.List;


public interface ActionPermissionService extends  IService<ActionPermission,ActionPermissionCriteria>  {

    List<ActionPermission> findAllOptimized();

}
