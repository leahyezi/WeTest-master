package com.weibo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties
public class Teacher {

	private long uid;
	private int id;
	private int number;
	private String login;
	private String password;
	private String salt;
	private String firstName;
	private String givenName;
	private String phone;
	private String email;
	private String QQ;
	private String highScgool;
	
	public long getUid() {
		return uid;
	}
	public void setUid(long uid) {
		this.uid = uid;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getGivenName() {
		return givenName;
	}
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getHighScgool() {
		return highScgool;
	}
	public void setHighScgool(String highScgool) {
		this.highScgool = highScgool;
	}
	
	@Override
	public String toString() {
		return "Teacher [uid=" + uid + ", id=" + id + ", number=" + number + ", login=" + login + ", password="
				+ password + ", salt=" + salt + ", firstName=" + firstName + ", givenName=" + givenName + ", phone="
				+ phone + ", email=" + email + ", QQ=" + QQ + ", highScgool=" + highScgool + "]";
	}
	
	
}
