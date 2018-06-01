package server;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.proiect.management.model.Angajat;
import com.proiect.management.model.AngajatDao;
import com.proiect.management.model.Prezenta;
import com.proiect.management.model.PrezentaDao;
import com.proiect.management.model.Task;
import com.proiect.management.model.TaskDao;


import server.Text;

public class ClientProtocol {
	private AngajatDao angajatDao =new AngajatDao();
	private TaskDao taskDao =new TaskDao();
	private PrezentaDao prezentaDao =new PrezentaDao();
	 public  Text processInput(Text theInput) {
	        Text theOutput = new Text();
	        int idA;
	        int id_show;

	        String methodName = (String) theInput.methodName;
	        if ("login".equals(methodName)) {
	            String username=(String) theInput.data.get(0)[0];
	            String pass=(String) theInput.data.get(1)[0];
	             theOutput.data = logIn(username,pass);
	        }else if ("giveTask".equals(methodName)) {
	        	 addTask(theInput.data.get(0));}
	        else if ("allAngajat".equals(methodName)) {
	        //	int id=(Integer)theInput.data.get(0[0];
	            theOutput.data = retrieveAllA();
	        } else if ("search".equals(methodName)) {
	            String name = (String) theInput.data.get(0)[0];
	            theOutput.data = search(name);
	        } else if ("searchPrezente".equals(methodName)) {
	            int name = (Integer) theInput.data.get(0)[0];
	            theOutput.data = searchPrezenta(name);
	        } else if ("searchTask".equals(methodName)) {
		            int name = (Integer) theInput.data.get(0)[0];
		            theOutput.data = searchTask(name);
	        }  else if ("getTaskForEmp".equals(methodName)) {
	            idA = (Integer) theInput.data.get(0)[0];
	            theOutput.data = getTaskForEmp(idA);

	        } else if ("dispalyPrezenta".equals(methodName)) {
	        	// theOutput.data = retrieveAllPrez();
	        }	else if ("allAngajat".equals(methodName)) {
	           	 theOutput.data = retrieveAllA();
	        }else if ("updateManager".equals(methodName)) {
	             update(theInput.data.get(0));
	             theOutput = null;
	        }else if ("updatePrezenta".equals(methodName)) {
	             updatePrezenta(theInput.data.get(0));
	             theOutput = null;
	        }else if ("updateA".equals(methodName)) {
	             updateA(theInput.data.get(0));
	             theOutput = null;
	        }else if ("updateTask".equals(methodName)) {
	             updateTask(theInput.data.get(0));
	             theOutput = null;
	        }else if ("retrieveAllTask".equals(methodName)) {
	        	int id=(Integer)theInput.data.get(0)[0];
	            theOutput.data = retrieveAllTask(id); 
	        }else if ("addUser".equals(methodName)) {
	            	int id=(Integer) theInput.data.get(0)[0];
	            	int idM=(Integer) theInput.data.get(1)[0];
	            	String type=(String)theInput.data.get(8)[0];
	                String pass=(String)theInput.data.get(7)[0];
	                String username=(String)theInput.data.get(6)[0];
	                String departament=(String)theInput.data.get(9)[0];
	                String adresa=(String)theInput.data.get(5)[0];
	                String detali=(String)theInput.data.get(4)[0];
	                String nume=(String)theInput.data.get(2)[0];
	                String prenume=(String)theInput.data.get(3)[0];
	                
	                 theOutput.data = AddUser(id,idM,nume,prenume, detali,adresa,username, pass,type,departament);
	        }else if ("deleteUser".equals(methodName)) {
	             deleteUser(theInput.data.get(0));
	             theOutput = null;
	        } else {
	                 theOutput = null;
	             }

	             return theOutput;
	      

	      
	    }
	 
	 
	 private void deleteUser(Object[] data) {
	        System.out.print("Protocol: deleteUser");
	        int id = (Integer) data[0];
	       
	      System.out.print("id"+id);
	        angajatDao.delete(id);
	    }
	 
	 
	 
	 
	 private List<Object[]> AddUser(int idE, int idM,String nume, String prenume, String detali, String adresa, String username, String password,
				String typeUser,String departament) {
	    	Angajat u=new Angajat(idE,idM,nume,prenume, detali,adresa,username, password,typeUser,departament);
	    	int id1= angajatDao.create(u);
	        List<Object[]> data = new ArrayList<Object[]>();
	        Object[] o= {idE};
	        data.add(o);
	        return data;
	    }
	 
	 
	 
	 
	  private List<Object[]> retrieveAllTask(int id) {
	    	System.out.println("id="+id);
	    	List<Task> istoric = taskDao.retriveAll(id);
	        List<Object[]> data = new ArrayList<Object[]>();
	        System.out.println("Protocol: retrieveAll ");
	        for(Task s: istoric) {
	        	//int idE, int idEfrom, Date dueDate, String statusTask
	            Object[] o = {s.getIdTask(),s.getIdE(),s.getIdEfrom(),s.getDueDate(),s.getStatusTask()};
	            data.add(o);
	            System.out.println(s.getIdTask());
	        }
	        return data;
	    }
	    
	 
	        private List<Object[]> retrieveAll() {
	        	System.out.println("id=");
	        	List<Angajat> istoric = angajatDao.retriveAll();
	            List<Object[]> data = new ArrayList<Object[]>();
	            System.out.println("Protocol: retrieveAll ");
	            for(Angajat s: istoric) {
	                Object[] o = {s.getNume(),s.getPrenume(),s.getIdE(),s.getIdM(),s.getDepartament(),s.getDetali()};
	                data.add(o);
	                System.out.println(s.getPrenume());
	            }
	            return data;
	        }
	        private List<Object[]> search(String name) {
	            List<Angajat> shows = angajatDao.search(name);
	            List<Object[]> data = new ArrayList<Object[]>();
	            System.out.println("Protocol: search ");
	            for(Angajat s: shows) {
	            	//(idE,idM,nume,prenume,detali,adresa,username, password,typeUser,departament) 
	                Object[] o =  { s.getIdE(), s.getIdM(),s.getNume(), s.getPrenume(),s.getDepartament(),s.getDetali(),s.getAdresa(),s.getUsername(),s.getPassword(),s.getTypeUser()};
	                data.add(o);
	                System.out.println(s.getNume());
	            }
	            return data;
	        }
	        
	        
	        private List<Object[]> searchPrezenta(int name) {
	            List<Prezenta> shows = prezentaDao.search(name);
	            List<Object[]> data = new ArrayList<Object[]>();
	            System.out.println("Protocol: search ");
	            for(Prezenta s: shows) {
	            	//(idE,idM,nume,prenume,detali,adresa,username, password,typeUser,departament) 
	                Object[] o =  { s.getIdPrezenta(), s.getIdE(),s.getNr(), s.getComentariu()};
	                data.add(o);
	                System.out.println(s.getIdPrezenta());
	            }
	            return data;
	        }
	        private List<Object[]> searchTask(int name) {
	            List<Task> shows = taskDao.searchid(name);
	            List<Object[]> data = new ArrayList<Object[]>();
	            System.out.println("Protocol: search ");
	            for(Task s: shows) {
	            	//(idE,idM,nume,prenume,detali,adresa,username, password,typeUser,departament) 
	                Object[] o =  {s.getIdTask(),s.getIdE(),s.getIdEfrom(),s.getDueDate(),s.getStatusTask()};
	                data.add(o);
	                System.out.println(s.getIdTask());
	            }
	            return data;
	        }
	        
	        
	    private List<Object[]> logIn(String username, String pass) {
	        
	          List<Object[]> data = new ArrayList<Object[]>();
	          System.out.println("Protocol: login ");
	          String type=angajatDao.Login(username, pass);
	          int id=angajatDao.Id(username, pass);
	          Object[] o= {type};
	          Object[] o1= {id};
	          //System.out.println(type);
	          data.add(o);
	          data.add(o1);
	          
	          return data;
	        
	}

	 private void addTask(Object[] data) {
	    	int idUser = (Integer) data[0];
	    	int idUserTo = (Integer) data[1];
	    	//String dueDate = (String) data[2];
	    	System.out.println("Protocol: give Task ");
	    	java.sql.Date sqlDate = null;
	    	try {
				java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[2]);
				sqlDate = new java.sql.Date(utilDate.getTime()); 
	    	} catch (ParseException e) {
				e.printStackTrace();
			}
	        Task r=new Task(idUser,idUserTo,sqlDate,"newTask");
	    	int i=taskDao.create(r);
	    	System.out.print("id task="+i);
	        
	    }
	  private List<Object[]> getTaskForEmp(int idA) {
	        List<Task> shows = taskDao.search(idA);
	        List<Object[]> data = new ArrayList<Object[]>();
	        System.out.println("Protocol: getTaskForEMp ");
	        for(Task t: shows) {
	            Object[] o = {t.getIdTask(), t.getDueDate(),t.getStatusTask()};
	            data.add(o);
	            //System.out.println(t.getDueDate().toString());
	        }
	        return data;
	      
	    }
	 /* private List<Object[]> retrieveAllPrez() {
	    	List<Angajat> users = prezentaDao.retriveAngajati();
	    	List<Object[]> data = new ArrayList<Object[]>();
	    	System.out.println("Protocol: retrieveALLPREZ ");
	    	for(Angajat u : users) {
	    		Object[] o = {u.getIdE(),u.getTypeUser(),u.getNume(),u.getPrenume()};
	    		data.add(o);
	    		System.out.println(u.getNume());
	    	}
	    	return data;
	    }*/
	   private List<Object[]> retrieveAllA() {
	    	List<Angajat> shows = angajatDao.retriveAll();
	    	List<Object[]> data = new ArrayList<Object[]>();
	    	System.out.println("Protocol: retrieveAllShows ");
	    	for(Angajat s: shows) {
	    		Object[] o = {s.getNume(),s.getPrenume(),s.getIdE(),s.getIdM(),s.getDepartament(),s.getDetali()};
	    		data.add(o);
	    		System.out.println(s.getNume());
	    	}
	    	return data;
	    }
	   private void update(Object[] data) {
	    	System.out.print("Admin Protocol: updateMan");
	    	
	            int id=(Integer)data[0];
	            int idM=(Integer)data[1];
	            String name= (String)data[2];
	            String prenume= (String)data[3];
	            String departament= (String)data[4];
	            String detali= (String)data[5];
	           Angajat a=new Angajat(id,idM,name,prenume,departament,detali);
	    	angajatDao.update(a);
			
	    }
	   private void updateA(Object[] data) {
	    	System.out.print("Admin Protocol: updateMan");
	    	int id=(Integer) data[0];
        	int idM=(Integer) data[1];
        	String type=(String)data[2];
            String pass=(String)data[3];
            String username=(String)data[4];
            String departament=(String)data[5];
            String adresa=(String)data[6];
            String detali=(String)data[7];
            String nume=(String)data[8];
            String prenume=(String)data[9];
            System.out.println(nume+" "+prenume);
            Angajat u=new Angajat(id,idM,nume,prenume, detali,adresa,username, pass,type,departament);
	    	angajatDao.update(u);
			
	    }
	   private void updatePrezenta(Object[] data) {
	    	System.out.print("Admin Protocol: updateMan");
	    	// s.getIdPrezenta(), s.getIdE(),s.getNr(), s.getComentariu()}
	            int id=(Integer)data[0];
	            int idE=(Integer)data[1];
	            int nr=(Integer)data[2];
		          
	            String comentariu= (String)data[3];
	            Prezenta p=new Prezenta(id,idE,nr,comentariu);
	    	prezentaDao.update(p);
			
	    }
	   private void updateTask(Object[] data) {
	    	System.out.print("Admin Protocol: updateMan1");
	    	// s.getIdPrezenta(), s.getIdE(),s.getNr(), s.getComentariu()}
	            int id=(Integer)data[0];
	            int idE=(Integer)data[1];
	            int idEfrom=(Integer)data[2];
	           /* java.sql.Date sqlDate = null;
	        	try {
	    			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)data[3]);
	    			sqlDate = new java.sql.Date(utilDate.getTime()); 
	        	} catch (ParseException e) {
	    			e.printStackTrace();
	    		
	        	}
	            Date d=sqlDate;*/
	            Date d =(Date)data[3];
	            String status=(String)data[4];
	            
		        
	         Task t=new Task(id,idE,idEfrom,d,status);
	    	taskDao.update(t);
			
	    }
	   
	    

}
