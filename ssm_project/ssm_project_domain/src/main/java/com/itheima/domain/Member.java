package com.itheima.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Member implements Serializable{

  private String id;//member的id
  private String name;//会员姓名
  private String nickname;//昵称
  private String phoneNum;//电话号码
  private String email;//邮箱

  @Override
  public String toString() {
    return "Member{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", nickname='" + nickname + '\'' +
            ", phoneNum='" + phoneNum + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }


  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

}
