package com.mkyong.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HibernateUtils {

	private static final Logger log = LoggerFactory.getLogger(HibernateUtils.class);

	private static SessionFactory sessionFactory;
	
	private HibernateUtils() {
		super();
	}

	private static SessionFactory buildSessionFactory() {
		try {
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().configure().build();

			Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();

			sessionFactory = metadata.getSessionFactoryBuilder().build();

			return sessionFactory;
		} catch (Exception e) {
			throw new ExceptionInInitializerError(e);
		}
	}

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = buildSessionFactory();
		}
		return sessionFactory;
	}

}