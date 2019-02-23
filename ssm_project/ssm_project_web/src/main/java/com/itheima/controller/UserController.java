package com.itheima.controller;

import com.github.pagehelper.PageInfo;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    //查询所有用户
    @RequestMapping("/findAll")
    public String findAll(@RequestParam(name = "pageNum",required = false,defaultValue = "1") Integer pageNum,
                          @RequestParam(name = "pageSize",required = false,defaultValue = "5")Integer pageSize,
                          @RequestParam(name = "searchValue",required = false,defaultValue = "")String searchValue, Model model) throws Exception{

        List<UserInfo> list = userService.findAll(pageNum,pageSize,searchValue);
        PageInfo pageInfo = new PageInfo(list);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("sv",searchValue);
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
    public String getUserDetails(String id,Model model) throws Exception{
        UserInfo userInfo = userService.findUserById(id);
        model.addAttribute("user",userInfo);
        return "user-show";
    }

    //获取用户还没有的角色
    @RequestMapping("/getRoleNotInUser")
    public String getRoleNotInUser(@RequestParam(name = "id") String id,Model model) throws Exception{
        List<Role> list = userService.getRolesNotInUser(id);
        model.addAttribute("roleList",list);
        model.addAttribute("userId",id);
        return "user-role-add";
    }

    //给用户添加角色
    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId")String userId,
                                @RequestParam(name = "roleIds")String roleIds) throws Exception {

        String[] rids = roleIds.split(",");
        userService.addRoleToUser(userId,rids);
        return "redirect:/user/findAll";
    }

    //移除用户的角色
    @RequestMapping("/removeRoleOnUser")
    public String removeRoleOnUser(String userId,String roleId) throws Exception {

        userService.removeRoleOnUser(userId,roleId);
        return "redirect:/user/getUserDetails?id="+userId;
    }

}
