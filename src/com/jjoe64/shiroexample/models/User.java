package com.jjoe64.shiroexample.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class User implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -4746333924452133573L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String username;

	@Column
	private String email;

	@Column
	private String password;

	@Column
	private String salt;

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public String getSalt() {
		return salt;
	}

	public String getUsername() {
		return username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public void setUsername(String username) {
		this.username = username;
	}


}
