package com.itheima.service.impl;

import com.itheima.dao.RoleDao;
import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    //查询所有角色
    public List<Role> findAll() throws Exception{

        return roleDao.findAll();
    }

    //查看角色详情
    public Role findRoleById(String id) throws Exception {
        return roleDao.findRoleById(id);
    }

    //添加角色
    public void addRole(Role role) throws Exception {
        roleDao.addRole(role);
    }

    //查询角色没有的权限
    public List<Permission> findPermissionNotInRole(String roleId) throws Exception {
        return roleDao.findPermissionNotInRole(roleId);
    }

    //给角色添加权限
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    //移除角色权限
    public void removePermissionOnRole(String roleId, String permissionId) throws Exception {
        roleDao.removePermissionOnRole(roleId,permissionId);
    }
}
