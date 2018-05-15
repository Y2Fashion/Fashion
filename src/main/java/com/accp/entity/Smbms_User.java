package com.accp.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 * 员工信息表
 * @author Administrator
 *
 */
public class Smbms_User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;//主键ID
	private String userCode;//用户编码
	private String userName;//用户姓名
	private String userPassword;//用户密码
	private Integer gender;//性别（1:女、 2:男）
	private Date birthday;//出生日期
	private String phone;//手机
	private String address;//地址
	private Integer userRole;//用户角色（取自角色表-角色id）
	private Integer createdBy;//创建者（userId）
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新者（userId）
	private Date modifyDate;//更新时间
	private Smbms_role smbms_role;//用户角色（取自角色表-角色id） 所对应的角色信息
	private String idPicPath;//证件图片路径 
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getUserRole() {
		return userRole;
	}
	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Smbms_role getSmbms_role() {
		return smbms_role;
	}
	public void setSmbms_role(Smbms_role smbms_role) {
		this.smbms_role = smbms_role;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getIdPicPath() {
		return idPicPath;
	}
	public void setIdPicPath(String idPicPath) {
		this.idPicPath = idPicPath;
	}
	
}
