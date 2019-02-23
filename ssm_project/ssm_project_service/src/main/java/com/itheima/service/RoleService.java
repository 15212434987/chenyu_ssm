package com.itheima.service;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll() throws Exception;

    Role findRoleById(String id) throws Exception;

    void addRole(Role role) throws Exception;

    List<Permission> findPermissionNotInRole(String roleId) throws Exception;

    void addPermissionToRole(String roleId, String[] permissionIds) throws Exception;

    void removePermissionOnRole(String roleId, String permissionId) throws Exception;
}
