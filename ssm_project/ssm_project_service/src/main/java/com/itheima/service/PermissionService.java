package com.itheima.service;

import com.itheima.domain.Permission;


import java.util.List;


public interface PermissionService {

    //查询所有权限
    List<Permission> findAll() throws Exception;

    //添加新权限
    void addPermission(Permission permission);
}
