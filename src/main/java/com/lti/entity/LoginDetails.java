package com.lti.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user_login")
public class LoginDetails {
	
	@Id
	private int user_id;
	@Column(length=40)
	private String email;
	private String password;
	public LoginDetails() {
	}
	public LoginDetails(int user_id, String email, String password) {
		this.user_id = user_id;
		this.email = email;
		this.password = password;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LoginDetails [user_id=" + user_id + ", email=" + email + ", password=" + password + "]";
	}
	
	
	
	
}
