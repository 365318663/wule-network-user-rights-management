package cn.itcast.dao;

import cn.itcast.domain.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPermissionDao {
    @Select("select *from permission where id in  (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findByRoleId(String roleId);

    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void save(Permission permission);

    @Delete("delete from role_permission where permissionId=#{id}")
    void deleteFromRole_PermissionByPermissionId(String id);

    @Delete("delete from permission where id=#{id}")
    void deleteByPermissionId(String id);
}
