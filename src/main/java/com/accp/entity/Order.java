package com.accp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 订单表
 *
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer orderId;//订单id
    private String  clienteleName;//客户名
    private String  clientelePhone;//客户电话
    private String  clienteleAddress;//客户地址
    private String  comment;//备注
    private String status;//状态
    private Date createTime;//预约时间
    private Date deliveryTime;//交货时间
    private Commodity commodity =new Commodity();//产品类


    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getClienteleName() {
        return clienteleName;
    }

    public void setClienteleName(String clienteleName) {
        this.clienteleName = clienteleName;
    }

    public String getClientelePhone() {
        return clientelePhone;
    }

    public void setClientelePhone(String clientelePhone) {
        this.clientelePhone = clientelePhone;
    }

    public String getClienteleAddress() {
        return clienteleAddress;
    }

    public void setClienteleAddress(String clienteleAddress) {
        this.clienteleAddress = clienteleAddress;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(Date deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", clienteleName='" + clienteleName + '\'' +
                ", clientelePhone='" + clientelePhone + '\'' +
                ", clienteleAddress='" + clienteleAddress + '\'' +
                ", comment='" + comment + '\'' +
                ", createTime=" + createTime +
                ", status='" + status + '\'' +
                ", deliveryTime=" + deliveryTime +
                '}';
    }
}
