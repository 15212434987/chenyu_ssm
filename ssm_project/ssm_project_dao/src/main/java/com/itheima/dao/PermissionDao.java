package com.itheima.dao;

import com.itheima.domain.Permission;

import java.util.List;

public interface PermissionDao {

    //查询所有权限
    List<Permission> findAll() throws Exception;

    //用于给Role注入属性列表permissions
    List<Permission> findPermissionsInIds(String roleId) throws Exception;

    //添加权限
    void addPermission(Permission permission);
}
