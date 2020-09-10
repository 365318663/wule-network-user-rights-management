package cn.itcast.dao;

import cn.itcast.domain.Permission;
import cn.itcast.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRoleDao {
    @Select("select * from role where id in (select roleid from users_role where userid=#{id})")
    @Results(id = "roleAll",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = List.class,
                many = @Many(select = "cn.itcast.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findByUserId(String id);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id =#{id}")
    @ResultMap("roleAll")
    Role findByById(String id);

    @Delete("delete from role where id=#{id}")
    void deleteById(String id);

    @Delete("delete from role_permission where roleId=#{id}")
    void deleteFromRole_PermissionByRoleId(String id);

    @Delete("delete from users_role where roleId=#{id}")
    void deleteFromUser_RoleByRoleId(String id);

    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findOtherPermissionByRoleId(String id);

    @Insert("insert into role_permission(roleId,permissionId) values(#{roleId},#{permissionId})")
    void addPermisionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId);
}
