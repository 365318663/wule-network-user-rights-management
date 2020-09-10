package cn.itcast.service;

import cn.itcast.domain.Role;
import cn.itcast.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(Integer page, Integer pageSize);

    void save(UserInfo userInfo);

    UserInfo findByid(String id);

    List<Role> findOherRoles(String id);

    void addRolesToUser(String userId, String[] roleId);
}
