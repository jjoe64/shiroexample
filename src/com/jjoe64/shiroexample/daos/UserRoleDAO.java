package com.jjoe64.shiroexample.daos;

import java.util.List;

import org.hibernate.Session;

import com.jjoe64.shiroexample.models.UserRole;

public class UserRoleDAO {
	private final Session session;

	public UserRoleDAO(Session s) {
		session = s;
	}

	public List<UserRole> getUserRolesByEmail(String email) {
		@SuppressWarnings("unchecked")
		List<UserRole> roles = session
				.createQuery("from UserRole where email=?").setString(0, email)
				.list();
		return roles;
	}

	public void insert(UserRole r) {
		session.persist(r);
	}
}
