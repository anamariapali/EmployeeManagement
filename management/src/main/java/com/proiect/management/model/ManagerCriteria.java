package com.proiect.management.model;

import java.util.ArrayList;
import java.util.List;





public class ManagerCriteria implements Criteria {
    private int idM;
   public ManagerCriteria(int criteria) {
	   idM=criteria;
   }
	@Override
	public List<Angajat> meetCriteria(List<Angajat> persons) {
		// TODO Auto-generated method stub
		
		List<Angajat> mPersons = new ArrayList<Angajat>(); 
	      
	      for (Angajat person : persons) {
	         if(person.getIdM()==idM){
	            mPersons.add(person);
	            System.out.println(person.getNume());
	         }
	      }
	      return mPersons;
		
	}
	}


