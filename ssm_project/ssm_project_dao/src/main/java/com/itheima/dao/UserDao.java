package com.itheima.dao;

import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    //登录
    UserInfo findByUsername(String username) throws Exception;

    //查询所有
    List<UserInfo> findAll(String searchValue) throws Exception;

    //添加
    void addUserInfo(UserInfo userInfo) throws Exception;

    //用户详情
    UserInfo findUserById(String id)throws Exception;

    //查询当前用户没有的角色
    List<Role> getRolesNotInUser(String id)throws Exception;

    //给用户添加角色
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String roleId)throws Exception;

    //移除用户的角色
    void removeRoleOnUser(@Param("userId")String userId, @Param("roleId")String roleId) throws Exception;
}
