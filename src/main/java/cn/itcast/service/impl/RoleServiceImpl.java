package cn.itcast.service.impl;

import cn.itcast.dao.IRoleDao;
import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import cn.itcast.service.IRoleService;
import cn.itcast.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleDao roleDao;



    @Override
    public List<Role> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        role.setId(UuidUtil.getUuid());
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findByById(id);
    }

    @Override
    public void deleteById(String id) {

        roleDao.deleteFromRole_PermissionByRoleId(id);
        roleDao.deleteFromUser_RoleByRoleId(id);
        roleDao.deleteById(id);
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(String id) {
        return roleDao.findOtherPermissionByRoleId(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermisionToRole(roleId,permissionId);
        }
    }
}
