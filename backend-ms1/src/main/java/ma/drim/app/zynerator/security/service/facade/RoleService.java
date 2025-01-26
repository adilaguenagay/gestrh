package ma.drim.app.zynerator.security.service.facade;

import ma.drim.app.zynerator.security.bean.Role;
import ma.drim.app.zynerator.security.dao.criteria.core.RoleCriteria;
import ma.drim.app.zynerator.service.IService;



public interface RoleService extends  IService<Role,RoleCriteria>  {
    Role findByAuthority(String authority);
    int deleteByAuthority(String authority);


    



}
