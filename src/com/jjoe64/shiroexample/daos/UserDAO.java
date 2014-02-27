package com.jjoe64.shiroexample.daos;

import org.hibernate.Session;

import com.jjoe64.shiroexample.models.User;
import com.jjoe64.shiroexample.util.HibernateUtil;

public class UserDAO {
	public static User getUserByEmail(String email) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		User user = (User) session.createQuery("from User where email=?")
				.setString(0, email).uniqueResult();
		session.getTransaction().commit();
		session.close();
		return user;
	}
}
