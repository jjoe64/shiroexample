package com.jjoe64.shiroexample.daos;

import org.hibernate.Session;

import com.jjoe64.shiroexample.models.User;

public class UserDAO {
	private final Session session;

	public UserDAO(Session s) {
		session = s;
	}

	public User getUserByEmail(String email) {
		User user = (User) session.createQuery("from User where email=?")
				.setString(0, email).uniqueResult();
		return user;
	}
}
