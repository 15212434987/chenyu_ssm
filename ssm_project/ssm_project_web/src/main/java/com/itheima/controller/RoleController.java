package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.domain.Role;
import com.itheima.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    //查询所有角色
    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception{
        List<Role> list = roleService.findAll();
        model.addAttribute("roleList",list);
        return "role-list";
    }

    //角色详情
    @RequestMapping("/findRoleByid")
    public String findRoleById(String id,Model model) throws Exception{
        Role role = roleService.findRoleById(id);
        model.addAttribute("role",role);
        return "role-show";
    }

    //添加角色
    @RequestMapping("/addRole")
    public String addRole(Role role) throws Exception{
        roleService.addRole(role);
        return "redirect:/role/findAll";
    }

    //查询角色没有(可以添加)的权限
    @RequestMapping("/findPermissionNotInRole")
    public String findPermissionNotInRole(@RequestParam(name = "roleId",required = true) String roleId,
                                          Model model) throws Exception{
        List<Permission> list = roleService.findPermissionNotInRole(roleId);
        model.addAttribute("permissionList",list);
        model.addAttribute("roleId",roleId);
        return "role-permission-add";
    }

    //给角色添加权限
    @RequestMapping("/addPermissionToRole")
    public String addRoleToUser(@RequestParam(name = "roleId",required = true)String roleId,
                                @RequestParam(name = "permissionIds",required = true)String permissionIds) throws Exception {

        String[] pids = permissionIds.split(",");
        roleService.addPermissionToRole(roleId,pids);
        return "redirect:/role/findAll";
    }

    //移除角色的权限
    @RequestMapping("/removePermissionOnRole")
    public String removePermissionOnRole(String roleId,String permissionId) throws Exception {
        roleService.removePermissionOnRole(roleId,permissionId);
        return "redirect:/role/findRoleByid?id="+roleId;
    }
}
