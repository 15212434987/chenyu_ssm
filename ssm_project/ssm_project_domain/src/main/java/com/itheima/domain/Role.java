package com.itheima.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class Role implements Serializable {
    private String id;//角色id
    private String roleName;//角色名称
    private String roleDesc;//角色描述
    private List<Permission> permissions;//角色权限列表

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }
}
