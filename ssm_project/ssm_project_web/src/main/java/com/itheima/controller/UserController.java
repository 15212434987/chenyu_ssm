package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.RoleService;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    RoleService roleService;

    //查询所有用户
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                          @RequestParam(name = "searchValue", required = false, defaultValue = "") String searchValue, Model model) throws Exception {

        List<UserInfo> list = userService.findAll(pageNum, pageSize, searchValue);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("sv", searchValue);
        return "user-list";
    }

    //添加用户
    @RequestMapping("/addUserInfo")
    public String addUserInfo(UserInfo userInfo) throws Exception {
        System.out.println(userInfo);
        userService.addUserInfo(userInfo);
        return "redirect:/user/findAll";
    }

    //用户详情
    @RequestMapping("/getUserDetails")
    public String getUserDetails(String id, Model model) throws Exception {
        UserInfo userInfo = userService.findUserById(id);
        model.addAttribute("user", userInfo);
        return "user-show";
    }

    //给用户拥有的角色role设置其属性flag为1
    @RequestMapping("/getRoleNotInUser")
    public String getRoleNotInUser(@RequestParam(name = "id") String id, Model model) throws Exception {
        //用户有的角色
        List<Role> rolesInUser = userService.getRolesInUser(id);
        //所有角色
        List<Role> allRole = roleService.findAll();
        //用户已有的角色,flag设置为1
        for (Role role : allRole) {
            for (Role role1 : rolesInUser) {
                if (role.getId().equals(role1.getId())) {
                    role.setFlag(1);
                }
            }
        }


        model.addAttribute("roleList", allRole);
        model.addAttribute("userId", id);
        return "user-role-add";
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId") String userId,
                                @RequestParam(name = "roleIds", required = false, defaultValue = "") String roleIds) throws Exception {



        List<Role> rolesInUser = userService.getRolesInUser(userId);//用户已有的角色列表

        //1.用户没有角色,并且页面也没有传入roleIds,不执行任何操作,直接返回
        if (rolesInUser.size() == 0 && roleIds.equals("")) {
            return "redirect:/user/findAll";
        }

        //2.当没有传入roleId时,并且用户有角色存在时,默认删除该用户的所有角色
        if (roleIds.equals("") && rolesInUser.size() > 0) {
            userService.removeAllRole(userId);
            return "redirect:/user/findAll";
        }

        String[] rids = roleIds.split(",");//页面传入的需要给用户添加的角色id

        //3.当用户不存在角色,并且页面传入了roleIds时,直接给用户添加角色
        if (rolesInUser.size() == 0 && roleIds.length() > 0) {
            userService.addRoleToUser(userId, rids);
            return "redirect:/user/findAll";
        }

        //4.如果用户角色个数和传入的角色id个数不相等,执行更新
        if (rolesInUser.size() != rids.length) {
            //4.1不等,执行更新
            userService.removeAllRole(userId);
            userService.addRoleToUser(userId, rids);
            return "redirect:/user/findAll";
        } else {
            //4.2用户角色个数和传入角色个数相等,再判断用户角色和传入角色是否完全相同
            // 不完全相同,执行更新操作
            boolean flag = false;//定义一个flag,判断是否需要执行更新操作
            for (Role role : rolesInUser) {//循环遍历,判断此次是否执行更新操作
                if (!Arrays.asList(rids).contains(role.getId())){
                    flag = true;
                    break;
                }
            }

            if (flag) {//根据flag判断是否执行更新操作
                userService.removeAllRole(userId);
                userService.addRoleToUser(userId, rids);
            }
            return "redirect:/user/findAll";
        }
    }

    //移除用户的角色
    @RequestMapping("/removeRoleOnUser")
    public String removeRoleOnUser(String userId, String roleId) throws Exception {
        String[] rids = roleId.split(",");
        userService.removeRoleOnUser(userId, rids);
        return "redirect:/user/getUserDetails?id=" + userId;
    }

}
