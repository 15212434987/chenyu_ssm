package com.itheima.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class UserInfo implements Serializable {

  private String id;//用户id
  private String email;//邮箱
  private String username;//用户名
  private String password;//密码
  private String phonenum;//电话
  private Integer status;//用户状态,1已激活,可以登录.0未激活,不能登录
  private String statusStr;
  private List<Role> roles;//用户的角色列表

  @Override
  public String toString() {
    return "Users{" +
            "id='" + id + '\'' +
            ", email='" + email + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", phonenum='" + phonenum + '\'' +
            ", status=" + status +
            '}';
  }

  public String getStatusStr() {
    if (status == 0){
      statusStr = "未激活";
    }else{
      statusStr = "已激活";
    }
    return statusStr;
  }

  public void setStatusStr(String statusStr) {
    this.statusStr = statusStr;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getPhonenum() {
    return phonenum;
  }

  public void setPhonenum(String phonenum) {
    this.phonenum = phonenum;
  }


  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public List<Role> getRoles() {
    return roles;
  }

  public void setRoles(List<Role> roles) {
    this.roles = roles;
  }
}
