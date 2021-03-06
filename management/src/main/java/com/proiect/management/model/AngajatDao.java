package com.proiect.management.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class AngajatDao {
 private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;}

    public static Integer create(Angajat u) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        session.save(u);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " +u.getNume()+ u.getAdresa());

        return u.getIdE();


    }




    public
    List<Angajat> retriveAll() {
        Session session = getSessionFactory().openSession();

        Transaction transaction = null;
        List<Angajat> users = null;
        try {
            transaction = session.beginTransaction();
            users = session.createQuery("FROM Angajat").list();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }

    public List<Angajat> search(String name) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Angajat> user = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from Angajat where nume = :x");
            q.setParameter("x", name);
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
    
    public String Login(String username,String password) {
    	System.out.println(username+" "+password+"dao");
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Angajat> user = null;
        String type=" ";
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Angajat  WHERE username = :x");
            q.setParameter("x", username);
            user = q.list();
            for(Angajat u: user)
            	if(u.getPassword().equals(password))
            		type=u.getTypeUser();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
       return type;
    }
    public int Id(String username,String password) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Angajat> user = null;
        int id=0;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("FROM Angajat  WHERE username = :x");
            q.setParameter("x", username);
            user = q.list();
            for(Angajat u: user)
            	if(u.getPassword().equals(password))
            	id=u.getIdE();
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
       return id;
    }


    ////
    public void delete(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM Angajat where idUser = :x")
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
	public void update(Angajat user) {

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
    public List<Angajat> searchName(int idA) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = null;
        List<Angajat> user = null;
        try {
            transaction = session.beginTransaction();
            Query q = session.createQuery("from Angajat where idE = :x");
            q.setInteger("x", idA);
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
   
}
