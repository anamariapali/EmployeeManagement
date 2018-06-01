package com.proiect.management;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.proiect.management.model.Angajat;
import com.proiect.management.model.AngajatDao;
import com.proiect.management.model.Criteria;
import com.proiect.management.model.ManagerCriteria;

public class TestManagement {

	@Test
	public void test() {
		AngajatDao a=new AngajatDao();
		 Criteria c=new ManagerCriteria(1);
		 List<Angajat> angajatiM=new ArrayList<Angajat>();
		 angajatiM=a.retriveAll();
		 List<Angajat> selectedA=new ArrayList<Angajat>();
		 selectedA=c.meetCriteria(angajatiM);
		 System.out.println(selectedA.get(0).getNume());
		 System.out.println(selectedA.get(1).getNume());
		// System.out.println(selectedA.get(2).getNume());
		 
		 assertEquals("anapali", selectedA.get(0).getNume());
		 assertEquals("anapali1", selectedA.get(1).getNume());
		// assertEquals("anapali", selectedA.get(2).getNume());

}
	}
