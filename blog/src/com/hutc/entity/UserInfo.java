package com.hutc.entity;

import java.io.Serializable;
import java.sql.Date;

public class UserInfo implements Serializable {
	private long userId;
	private String real_name;
	private String sex;
	private String institute;
	private Date birthday;
	private String email;
	private String contact;
	private String introduce;
	public UserInfo() {
		super();
	}
	public UserInfo(long userId, String real_name, String sex,
			String institute, Date birthday, String email, String contact,
			String introduce) {
		super();
		this.userId = userId;
		this.real_name = real_name;
		this.sex = sex;
		this.institute = institute;
		this.birthday = birthday;
		this.email = email;
		this.contact = contact;
		this.introduce = introduce;
	}
	@Override
	public String toString() {
		return "UserInfo [userId=" + userId + ", real_name=" + real_name
				+ ", sex=" + sex + ", institute=" + institute + ", birthday="
				+ birthday + ", email=" + email + ", contact=" + contact
				+ ", introduce=" + introduce + "]";
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getInstitute() {
		return institute;
	}
	public void setInstitute(String institute) {
		this.institute = institute;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	
	
}
