package com.itheima.service;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{
    List<UserInfo> findAll(Integer pageNum,Integer pageSize,String searchValue) throws Exception;

    void addUserInfo(UserInfo userInfo) throws Exception;

    UserInfo findUserById(String id) throws Exception;

    List<Role> getRolesInUser(String id) throws Exception;

    void addRoleToUser(String userId, String[] roleIds) throws Exception;

    void removeRoleOnUser(String userId, String[] roleId) throws Exception;

    void removeAllRole(String userId) throws Exception;
//    UserInfo login(UserInfo userInfo);
}
