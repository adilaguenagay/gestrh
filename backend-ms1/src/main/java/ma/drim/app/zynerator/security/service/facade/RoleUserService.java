package ma.drim.app.zynerator.security.service.facade;

import ma.drim.app.zynerator.security.bean.RoleUser;
import ma.drim.app.zynerator.security.dao.criteria.core.RoleUserCriteria;
import ma.drim.app.zynerator.service.IService;

import java.util.List;



public interface RoleUserService extends  IService<RoleUser,RoleUserCriteria>  {

    List<RoleUser> findByRoleId(Long id);
    int deleteByRoleId(Long id);
    long countByRoleAuthority(String authority);
    List<RoleUser> findByUserId(Long id);
    int deleteByUserId(Long id);
    long countByUserEmail(String email);



}
