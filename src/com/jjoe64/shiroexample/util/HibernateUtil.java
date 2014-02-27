package com.jjoe64.shiroexample.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.google.common.base.Optional;


public class HibernateUtil {

	private static Optional<SessionFactory> sessionFactory = Optional.absent();

	private static SessionFactory buildSessionFactory() {
		try {
			// Create the SessionFactory from hibernate.cfg.xml
			return new Configuration().configure().buildSessionFactory();

		} catch (Throwable ex) {
			// Make sure you log the exception, as it might be swallowed
			System.err.println("Initial SessionFactory creation failed." + ex);
			throw new ExceptionInInitializerError(ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory.isPresent()) {
			return sessionFactory.get();
		}
		sessionFactory = Optional.fromNullable(buildSessionFactory());
		return sessionFactory.get();
	}

	public static void shutdown() {
		// Close caches and connection pools
		getSessionFactory().close();
	}
}
