package com.menjin.user.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * User用户对象
 * @author Jack
 *
 */
public class User implements Serializable , UserDetails{
	
	private static final long serialVersionUID = -7517937362922901896L;

	//与t_user关联的主键id
	private Integer id ;
	
	//登录名
	private String username;
	
	//密码
	private String password;
	
	//状态̬
	private String status;
	
	//̬删除状态
	private String deleteStatus;
	
	//客户类型
	private String customerType;
	
	//用户真实名
	private String name;
	
	//出生日期
	private java.sql.Date birthday;
	
	//身份证号码
	private String idCardNumber;
	
	//邮箱
	private String email;
	
	//座机电话
	private String phone;
	
	//移动电话
	private String mobilephone;
	
	//住址
	private String address;
	
	//国籍
	private String country;
	
	//安全问题
	private String securityQuestion;
	
	//安全答案
	private String securityAnswer;
	
	//创建时间
	private Date createdDate;
	
	//创建者
	private String createdBy;
	
	//修改时间
	private Date modifiedDate;
	
	//修改者
	private String modifiedBy;
	
	//过期时间
	private Date expiredDate;
	
	private Set<Role> roles;

	public User() {
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public java.sql.Date getBirthday() {
		return birthday;
	}

	public void setBirthday(java.sql.Date birthday) {
		this.birthday = birthday;
	}

	public String getIdCardNumber() {
		return idCardNumber;
	}

	public void setIdCardNumber(String idCardNumber) {
		this.idCardNumber = idCardNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobilephone() {
		return mobilephone;
	}

	public void setMobilephone(String mobilephone) {
		this.mobilephone = mobilephone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSecurityQuestion() {
		return securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public String getSecurityAnswer() {
		return securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getExpiredDate() {
		return expiredDate;
	}

	public void setExpiredDate(Date expiredDate) {
		this.expiredDate = expiredDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", status=" + status + ", deleteStatus="
				+ deleteStatus + ", customerType=" + customerType + ", name="
				+ name + ", birthday=" + birthday + ", idCardNumber="
				+ idCardNumber + ", email=" + email + ", phone=" + phone
				+ ", mobilephone=" + mobilephone + ", address=" + address
				+ ", country=" + country + ", securityQuestion="
				+ securityQuestion + ", securityAnswer=" + securityAnswer
				+ ", createdDate=" + createdDate + ", createdBy=" + createdBy
				+ ", modifiedDate=" + modifiedDate + ", modifiedBy="
				+ modifiedBy + ", expiredDate=" + expiredDate + "]";
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {  
	     List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();  
	       
	     for (Role role : roles) {  
	         list.add(role.generateGrantedAuthority());  
	         for (Resource resource : role.getResources()) {  
	             list.add(resource);  
	         }  
	     }  
	     return list;  
	 } 

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		int a = Integer.parseInt(this.status);
		return a == 0 ? false:true;
	}
}
