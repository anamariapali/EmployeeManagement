package com.proiect.management.model;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;



public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	//	Angajat u = new Angajat(3,"florina1","prenume","detali","baia mare","florina2","parola2","admin");       create(u);
	        System.out.println(" =======READ =======");
	        List<Angajat> ems1 = read();
	        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
	        System.out.println(date.toString());
	        for(Angajat e: ems1) System.out.println(e.getNume()+ e.getIdE());

 

	    }


	    private static SessionFactory getSessionFactory() {
	        Configuration configuration = new Configuration().configure();

	        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()

	                .applySettings(configuration.getProperties());

	        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

	        return sessionFactory;

	    }
	    public static Integer create(Angajat u) {

	        Session session = getSessionFactory().openSession();

	        session.beginTransaction();

	        session.save(u);

	        session.getTransaction().commit();

	        session.close();

	        System.out.println("Successfully created " + u.getIdE()+ u.getNume());

	        return u.getIdE();


	    }

	    public static List<Angajat> read() {

	        Session session = getSessionFactory().openSession();


	        String hql="FROM Angajat ";
	        Query e = session.createQuery(hql);
	        List<Angajat> employees= e.list();
	        session.close();

	        System.out.println("Found " + employees.size() + " Employees");

	        return employees;



	    }
}
