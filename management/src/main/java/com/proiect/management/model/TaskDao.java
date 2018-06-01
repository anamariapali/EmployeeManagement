package com.proiect.management.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;





public class TaskDao {
private static	 SessionFactory getSessionFactory() {
	        Configuration configuration = new Configuration().configure();

	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

	                .applySettings(configuration.getProperties());

	        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

	        return sessionFactory;}
 public static Integer create(Task u) {

	        Session session = getSessionFactory().openSession();

	        session.beginTransaction();

	        session.save(u);

	        session.getTransaction().commit();

	        session.close();

	        System.out.println("Successfully created " + u.getIdTask()+ u.getIdE());

	        return u.getIdTask();


	    }
 public List<Task> search(int idE) {
     Session session = getSessionFactory().openSession();
     Transaction transaction = null;
     System.out.println("search "+ idE);
     List<Task> user = null;
     try {
         transaction = session.beginTransaction();
         Query q = session.createQuery("FROM Task WHERE idE = :x");
         q.setInteger("x", idE);
         user = q.list();
         transaction.commit();
     } catch (HibernateException e) {
         transaction.rollback();
         e.printStackTrace();
     } finally {
         session.close();
     }
     return user;
 }
 public List<Task> searchid(int idE) {
     Session session = getSessionFactory().openSession();
     Transaction transaction = null;
     System.out.println("search "+ idE);
     List<Task> user = null;
     try {
         transaction = session.beginTransaction();
         Query q = session.createQuery("FROM Task WHERE idTask = :x");
         q.setInteger("x", idE);
         user = q.list();
         transaction.commit();
     } catch (HibernateException e) {
         transaction.rollback();
         e.printStackTrace();
     } finally {
         session.close();
     }
     return user;
 }
 @SuppressWarnings("null")
		public void update(Task user) {

	        Session session = getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	            session.beginTransaction();
	            session.update(user);
	            session.getTransaction().commit();
	            session.close();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	          //  session.close();
	        }

 
 }
 
 public List<Task> retriveAll(int id) {
     Session session = getSessionFactory().openSession();
    
     Transaction transaction = null;
     List<Task> istoric = null;
    
     System.out.println("id1="+id);
     try {
         transaction = session.beginTransaction();
         Query q = session.createQuery("From Task WHERE idE = :x");
          q.setInteger("x", id);
          System.out.println("id4="+id);
          istoric= q.list();
          System.out.println(istoric.toString()+2);
          transaction.commit();
         
          
          
     } catch (HibernateException e) {
         transaction.rollback();
         e.printStackTrace();
     } finally {
         session.close();
     }
     return istoric;
 }
 
 
 
 
}
