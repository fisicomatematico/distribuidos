package com.utilidades;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
    public Session sesionActual;
    public Transaction transactionActual;
 
    public Session abrirSesion(){
           this.setSesionActual(createSessionFactory().openSession());
    return getSesionActual();
    }
    
    public void cerrarSesion(){
        sesionActual.close();
    }
    
    public void cerrarsesionTransaccion(){
        transactionActual.commit();
        sesionActual.close();
    
    }
    
    public void cerrarTransaccion(Session sesion){
    	Transaction transactionActual  = sesion.getTransaction();
    	transactionActual.commit();
    }
    
    public Session SesionTransaccion(){
        sesionActual = createSessionFactory().openSession();
        transactionActual = sesionActual.beginTransaction();
    return sesionActual;
    
    }
    
    public static SessionFactory createSessionFactory(){
    /*
        Configuration configuration = new Configuration().configure();
        
        StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(configuration.getProperties());

        ServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
     */
    	sessionFactory = new Configuration().configure().buildSessionFactory();
    return sessionFactory;
    
    }
    
    public Session getSesionActual() {
        return sesionActual;
    }

    public void setSesionActual(Session sesionActual) {
        this.sesionActual = sesionActual;
    }

    public Transaction getTransactionActual() {
        return transactionActual;
    }

    public void setTransactionActual(Transaction transactionActual) {
        this.transactionActual = transactionActual;
    }
	
}
