package cn.itcast.service;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(Integer page, Integer pageSize);

    void save(Role role);

    Role findById(String id);

    void deleteById(String id);

    List<Permission> findOtherPermissionByRoleId(String id);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
