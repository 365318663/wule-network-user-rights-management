package cn.itcast.service;

import cn.itcast.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(Integer page, Integer pageSize);

    void save(Permission permission);

    void deleteByPermissionId(String id);
}
