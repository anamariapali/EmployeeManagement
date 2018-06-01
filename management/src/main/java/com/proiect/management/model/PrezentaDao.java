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



public class PrezentaDao {
	 private static SessionFactory getSessionFactory() {
	        Configuration configuration = new Configuration().configure();

	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

	                .applySettings(configuration.getProperties());

	        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

	        return sessionFactory;}

	    public static Integer create(Prezenta u) {

	        Session session = getSessionFactory().openSession();

	        session.beginTransaction();

	        session.save(u);

	        session.getTransaction().commit();

	        session.close();

	        System.out.println("Successfully created "+ u.getIdE() + u.getIdPrezenta());

	        return u.getIdE();


	    }




	    public
	    List<Prezenta> retriveAll() {
	        Session session = getSessionFactory().openSession();

	        Transaction transaction = null;
	        List<Prezenta> users = null;
	        try {
	            transaction = session.beginTransaction();
	            users = session.createQuery("FROM Prezenta").list();
	            transaction.commit();
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	        return users;
	    }

	    public List<Prezenta> search(int idE) {
	        Session session = getSessionFactory().openSession();
	        Transaction transaction = null;
	        List<Prezenta> user = null;
	        try {
	            transaction = session.beginTransaction();
	            Query q = session.createQuery("from Prezenta where idE = :x");
	            q.setInteger("x", idE);;
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
	    public void delete(int id) {
	        Session session = getSessionFactory().openSession();
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();

	            session.createQuery("DELETE FROM Prezenta where idE = :x")
	                    .setInteger("x", id)
	                    .executeUpdate();

	            System.out.println("Admin records deleted!");
	        } catch (HibernateException e) {
	            transaction.rollback();
	            e.printStackTrace();
	        } finally {
	            session.close();
	        }
	    }

	    @SuppressWarnings("null")
		public void update(Prezenta user) {

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
	    public List<Angajat> retriveAngajati() {
	        
	        AngajatDao angajatData= new AngajatDao();
	        List<Angajat> angajat=null;
	        List<Angajat> angajatF = new ArrayList<Angajat>();
	        List<Prezenta> rec = retriveAll();
	      
	            for(Prezenta i:rec) {
	            	System.out.println(i.getNr());
	            	 angajat=angajatData.searchName(i.getIdE());
	            	 //System.out.println("ana"+angajat.get(0).getNume()+"  "+angajat.size());
	            	 angajatF.addAll(angajat);
	            }
	    
	        
	        return  angajatF;
	    }

}
