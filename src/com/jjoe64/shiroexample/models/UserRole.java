package com.jjoe64.shiroexample.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserRole implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = -5401510492152424643L;

	@Id
	@GeneratedValue
	private Integer id;

	@Column
	private String roleName;

	@Column
	private String email;

	public UserRole() {
	}

	public String getEmail() {
		return email;
	}

	public Integer getId() {
		return id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
