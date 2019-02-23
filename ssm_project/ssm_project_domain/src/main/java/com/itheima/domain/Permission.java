package com.itheima.domain;


import java.io.Serializable;

public class Permission implements Serializable {

  private String id;//权限id
  private String permissionname;//权限名称
  private String url;//该权限下可以访问的


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }


  public String getPermissionname() {
    return permissionname;
  }

  public void setPermissionname(String permissionname) {
    this.permissionname = permissionname;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

}
