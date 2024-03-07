package model;

import java.sql.Date;

public class UserModel {
	private Integer ID;
	private String userName;
	private String phone;
	private String address;
	private Date dateWork;
	private Date createDate;
	private String password;
	private String fullName;
	private RoleModel role;
	private String code;
	private String status;
	private String email;
	
	public UserModel() {
		
	}
	
	public UserModel(Integer iD, String userName, String phone, String address, Date dateWork, Date createDate,
			String password, String fullName, RoleModel role, String code, String status,String email) {
		ID = iD;
		this.userName = userName;
		this.phone = phone;
		this.address = address;
		this.dateWork = dateWork;
		this.createDate = createDate;
		this.password = password;
		this.fullName = fullName;
		this.role = role;
		this.code = code;
		this.status = status;
		this.email = email;
	}
	
	public Integer getID() {
		return ID;
	}
	public void setID(int iD) {
		this.ID = iD;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
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
	public Date getDateWork() {
		return dateWork;
	}
	public void setDateWork(Date dateWork) {
		this.dateWork = dateWork;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public RoleModel getRole() {
		return role;
	}
	public void setRole(RoleModel role) {
		this.role = role;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
