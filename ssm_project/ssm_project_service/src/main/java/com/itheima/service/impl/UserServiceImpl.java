package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    //登录
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Role> roles = userInfo.getRoles();
        List<SimpleGrantedAuthority> authoritys = getAuthority(roles);

        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus() == 0 ? false : true,
                true, true, true, authoritys);
        return user;
    }

    //登录方法中调用,获取权限列表
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> authoritys = new ArrayList();
        for (Role role : roles) {
            authoritys.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return authoritys;
    }

    //查询所有user
    public List<UserInfo> findAll(Integer pageNum, Integer pageSize, String searchValue) throws Exception {
        PageHelper.startPage(pageNum, pageSize);
        return userDao.findAll(searchValue);
    }

    //添加新user

    public void addUserInfo(UserInfo userInfo) throws Exception {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.addUserInfo(userInfo);
    }

    //用户详情
    public UserInfo findUserById(String id) throws Exception {
        return userDao.findUserById(id);
    }

    //查询当前用户没有的角色
    public List<Role> getRolesNotInUser(String id) throws Exception {
        return userDao.getRolesNotInUser(id);
    }

    //给用户添加角色
    public void addRoleToUser(String userId, String[] roleIds) throws Exception {
        for (String roleId : roleIds) {
            userDao.addRoleToUser(userId, roleId);
        }
    }

    //移除用户的角色
    public void removeRoleOnUser(String userId, String roleId) throws Exception{
        userDao.removeRoleOnUser(userId,roleId);
    }
}
