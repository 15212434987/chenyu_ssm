package com.itheima.domain;


import com.itheima.utils.DateUtils;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
@Component
public class SysLog implements Serializable{

  private String id;           //日志id
  private Date visitTime;      //访问时间
  private String visitTimeStr;
  private String username;    //访问的用户名
  private String ip;          //访问的ip
  private String url;         //访问的路径
  private Long executionTime; //执行时间
  private String method;      //访问的方法

  public String getVisitTimeStr() {
    if (visitTime!= null){
      visitTimeStr = DateUtils.date2String(visitTime,"yyyy-MM-dd HH:mm:ss");
    }
    return visitTimeStr;
  }

  public void setVisitTimeStr(String visitTimeStr) {
    this.visitTimeStr = visitTimeStr;
  }

  public Date getVisitTime() {
    return visitTime;
  }

  public void setVisitTime(Date visitTime) {
    this.visitTime = visitTime;
  }

  public Long getExecutionTime() {
    return executionTime;
  }

  public void setExecutionTime(Long executionTime) {
    this.executionTime = executionTime;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    this.method = method;
  }

}
