package com.itheima.domain;


import com.itheima.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Component
public class Order implements Serializable {

  private String id; //订单id
  private String orderNum;//订单编号
  @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
  private Date orderTime;//下单时间
  private String orderTimeStr;
  private Integer peopleCount;//出游人数
  private String orderDesc;//订单描述
  private Integer payType;//支付方式
  private String payTypeStr;
  private Integer orderStatus;//订单状态
  private String orderStatusStr;
  private String productid;//产品id
  private String memberid;//会员id
  @Autowired
  private Product product;
  @Autowired
  private List<Traveller> travellers;
  @Autowired
  private Member member;

  @Override
  public String toString() {
    return "Order{" +
            "id='" + id + '\'' +
            ", orderNum='" + orderNum + '\'' +
            ", orderTime=" + orderTime +
            ", orderTimeStr='" + orderTimeStr + '\'' +
            ", peopleCount=" + peopleCount +
            ", orderDesc='" + orderDesc + '\'' +
            ", payType=" + payType +
            ", payTypeStr='" + payTypeStr + '\'' +
            ", orderStatus=" + orderStatus +
            ", productid='" + productid + '\'' +
            ", memberid='" + memberid + '\'' +
            ", product=" + product +
            ", travellers=" + travellers +
            ", member=" + member +
            '}';
  }

  public String getOrderStatusStr() {
    if (orderStatus == 0){
      orderStatusStr = "已支付";
    }else {
      orderStatusStr = "未支付";
    }
    return orderStatusStr;
  }

  public void setOrderStatusStr(String orderStatusStr) {
    this.orderStatusStr = orderStatusStr;
  }

  public String getPayTypeStr() {
    if (payType == 0){
      payTypeStr = "微信支付";
    }
    if (payType == 1){
      payTypeStr = "支付宝";
    }
    if (payType == 2){
      payTypeStr = "微信支付";
    }
    return payTypeStr;
  }

  public void setPayTypeStr(String payTypeStr) {
    this.payTypeStr = payTypeStr;
  }

  public List<Traveller> getTravellers() {
    return travellers;
  }

  public void setTravellers(List<Traveller> travellers) {
    this.travellers = travellers;
  }

  public Member getMember() {
    return member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderNum() {
    return orderNum;
  }

  public void setOrderNum(String orderNum) {
    this.orderNum = orderNum;
  }

  public Date getOrderTime() {
    return orderTime;
  }

  public void setOrderTime(Date orderTime) {
    this.orderTime = orderTime;
  }

  public String getOrderTimeStr() {
    if(orderTime!=null){
      orderTimeStr= DateUtils.date2String(orderTime,"yyyy-MM-dd HH:mm:ss");
    }
    return orderTimeStr;
  }

  public void setOrderTimeStr(String orderTimeStr) {
    this.orderTimeStr = orderTimeStr;
  }

  public Integer getPeopleCount() {
    return peopleCount;
  }

  public void setPeopleCount(Integer peopleCount) {
    this.peopleCount = peopleCount;
  }

  public String getOrderDesc() {
    return orderDesc;
  }

  public void setOrderDesc(String orderDesc) {
    this.orderDesc = orderDesc;
  }

  public Integer getPayType() {
    return payType;
  }

  public void setPayType(Integer payType) {
    this.payType = payType;
  }

  public Integer getOrderStatus() {
    return orderStatus;
  }

  public void setOrderStatus(Integer orderStatus) {
    this.orderStatus = orderStatus;
  }

  public String getProductid() {
    return productid;
  }

  public void setProductid(String productid) {
    this.productid = productid;
  }

  public String getMemberid() {
    return memberid;
  }

  public void setMemberid(String memberid) {
    this.memberid = memberid;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }
}
