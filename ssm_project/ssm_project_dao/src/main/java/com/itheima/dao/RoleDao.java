package com.itheima.dao;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDao {
    //用于向userInfo中注入属性
    List<Role> findByUserId(String userId);

    //查询所有
    List<Role> findAll() throws Exception;

    //查询角色详情
    Role findRoleById(String id) throws Exception;

    //添加角色
    void addRole(Role role) throws Exception;

    //查询角色没有的权限
    List<Permission> findPermissionNotInRole(String roleId);

    //给角色添加权限
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId) throws Exception;

    //移除角色权限
    void removePermissionOnRole(@Param("roleId") String roleId,@Param("permissionId") String permissionId)throws Exception;
}
