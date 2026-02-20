package com.practice_app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ClgEntity {

	@Id
	private Integer code;
	private String name;
	private String password;
	private String email;
	public ClgEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ClgEntity(int code, String name, String password, String email) {
		super();
		this.code = code;
		this.name = name;
		this.password = password;
		this.email = email;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
