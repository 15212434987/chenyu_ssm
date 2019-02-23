package com.itheima.domain;

import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class Traveller implements Serializable {

  private String id;//traveller的id
  private String name;//姓名
  private String sex;//性别
  private String phoneNum;//电话号码
  private Integer credentialstype;//证件类型
  private String credentialsTypeStr;
  private String credentialsNum;//证件号码
  private Integer travellertype;//游客类型(成人or儿童)
  private String travellerTypeStr;

  @Override
  public String toString() {
    return "Traveller{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", sex='" + sex + '\'' +
            ", phoneNum='" + phoneNum + '\'' +
            ", credentialstype=" + credentialstype +
            ", credentialsTypeStr='" + credentialsTypeStr + '\'' +
            ", credentialsNum='" + credentialsNum + '\'' +
            ", travellertype=" + travellertype +
            ", travellerTypeStr='" + travellerTypeStr + '\'' +
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

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getPhoneNum() {
    return phoneNum;
  }

  public void setPhoneNum(String phoneNum) {
    this.phoneNum = phoneNum;
  }

  public Integer getCredentialstype() {
    return credentialstype;
  }

  public void setCredentialstype(Integer credentialstype) {
    this.credentialstype = credentialstype;
  }

  public String getCredentialsTypeStr() {
    if (credentialstype == 0){
      credentialsTypeStr = "身份证";
    }
    return credentialsTypeStr;
  }

  public void setCredentialsTypeStr(String credentialsTypeStr) {
    this.credentialsTypeStr = credentialsTypeStr;
  }

  public String getCredentialsNum() {
    return credentialsNum;
  }

  public void setCredentialsNum(String credentialsNum) {
    this.credentialsNum = credentialsNum;
  }

  public Integer getTravellertype() {
    return travellertype;
  }

  public void setTravellertype(Integer travellertype) {
    this.travellertype = travellertype;
  }

  public String getTravellerTypeStr() {
    if (travellertype == 0){
      travellerTypeStr="儿童";
    }
    if (travellertype == 1){
      travellerTypeStr="成人";
    }
    return travellerTypeStr;
  }

  public void setTravellerTypeStr(String travellerTypeStr) {
    this.travellerTypeStr = travellerTypeStr;
  }
}
