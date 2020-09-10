package cn.itcast.service.impl;

import cn.itcast.dao.IPermissionDao;
import cn.itcast.domain.Permission;
import cn.itcast.service.IPermissionService;
import cn.itcast.util.UuidUtil;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        permission.setId(UuidUtil.getUuid());
        permissionDao.save(permission);
    }

    @Override
    public void deleteByPermissionId(String id) {
        permissionDao.deleteFromRole_PermissionByPermissionId(id);
        permissionDao.deleteByPermissionId(id);
    }
}
