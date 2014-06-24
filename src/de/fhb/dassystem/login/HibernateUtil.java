package de.fhb.dassystem.login;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Initialisiert Hibernate Session-Fabrik
 * 
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    static {
	try {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(
		    configuration.getProperties()).buildServiceRegistry();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	} catch (Throwable ex) {
	    ex.printStackTrace();
	    throw new ExceptionInInitializerError(ex);
	}
    }

    /**
     * @return Hibernate Session-Fabrik
     */
    public static SessionFactory getSessionFactory() {
	return sessionFactory;
    }

    /**
     * Hibernate Session-Fabrik schlieﬂen
     */
    public static void shutdown() {
	getSessionFactory().close();
    }
}
