package com.itheima.controller;

import com.itheima.domain.Permission;
import com.itheima.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    //查询所有权限
    @RequestMapping("/findAll")
    public String findAll(Model model) throws Exception {
        List<Permission> list = permissionService.findAll();
        model.addAttribute("permissionList",list);
        return "permission-list";
    }

    //添加新权限
    @RequestMapping("/addPermission")
    public String addPermission(Permission permission){
        permissionService.addPermission(permission);
        return "redirect:/permission/findAll";
    }
}
