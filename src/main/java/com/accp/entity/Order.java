package com.accp.entity;

import java.io.Serializable;

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
}
