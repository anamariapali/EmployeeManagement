package com.proiect.management.model;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class LeaveDao {
	private static	 SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

                .applySettings(configuration.getProperties());

        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        return sessionFactory;}
public static Integer create(Leave u) {

        Session session = getSessionFactory().openSession();

        session.beginTransaction();

        session.save(u);

        session.getTransaction().commit();

        session.close();

        System.out.println("Successfully created " + u.getIdLeave()+ u.getIdE());

        return u.getIdLeave();


    }
public List<Leave> search(int idE) {
 Session session = getSessionFactory().openSession();
 Transaction transaction = null;
 List<Leave> user = null;
 try {
     transaction = session.beginTransaction();
     Query q = session.createQuery("from Leave where idE = :x");
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

public
List<Leave> retriveAll() {
    Session session = getSessionFactory().openSession();

    Transaction transaction = null;
    List<Leave> users = null;
    try {
        transaction = session.beginTransaction();
        users = session.createQuery("FROM Leave").list();
        transaction.commit();
    } catch (HibernateException e) {
        transaction.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
    return users;
}


public void delete(int id) {
    Session session = getSessionFactory().openSession();
    Transaction transaction = null;
    try {
        transaction = session.beginTransaction();

        session.createQuery("DELETE FROM Leave where idE = :x")
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
public void update(Leave user) {

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
}
