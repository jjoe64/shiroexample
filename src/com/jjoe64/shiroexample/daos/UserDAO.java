package com.jjoe64.shiroexample.daos;

import org.hibernate.Session;

import com.jjoe64.shiroexample.models.User;

public class UserDAO {
	public static User getUserByEmail(String email) {
		Session session = HibernateUtil.getCurrentSession();
		session.beginTransaction();
		User user = (User) session.createQuery("from User where email=?")
				.setString(0, email).uniqueResult();
		session.getTransaction().commit();
		return user;
	}
}
