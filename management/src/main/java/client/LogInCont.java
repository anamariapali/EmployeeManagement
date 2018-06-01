package client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.itextpdf.text.DocumentException;
import com.proiect.management.model.Angajat;
import com.proiect.management.model.Criteria;
import com.proiect.management.model.ManagerCriteria;
import com.proiect.management.model.Report;
import com.proiect.management.model.ReportFactory;
import com.proiect.management.model.Task;


import server.ClientServer;
import server.Text;



public class LogInCont extends Observable {
	 private final String hostName = "192.168.23.1";///"192.168.1.2"; // "192.168.0.227"; //"10.128.55.213";
     private final int portNumber = 1342;
     private Socket kkSocket = null;
     private ObjectOutputStream out = null;
     private ObjectInputStream in = null;

    
 	private ReportFactory reportFactory = new ReportFactory();
    private Report report1 = reportFactory.getReport("PDF");
    private Report report2 = reportFactory.getReport("CSV");
     private ManagerView managerView;
     private LogInView logInView;
     
     private int id_user;
     private Angajat a1 ;
    
     public LogInCont (LogInView login)throws FileNotFoundException, DocumentException {
         System.out.println("Client");
         this.logInView = login;
         
         //cs = new ClientServer(portNumber); NO bc the server is the first one which must be started
     }
     
     public
     void connect() {
         System.out.println("Client: try to connect");

         // connect to the server
         try {
             kkSocket = new Socket(hostName, portNumber);
          //  this.addObserver((Observer) kkSocket);
             //System.out.println("aici");
             out = new ObjectOutputStream(kkSocket.getOutputStream());
             System.out.println("aici out");
             in = new ObjectInputStream(kkSocket.getInputStream());
             System.out.println("Client: connected");
         } catch (UnknownHostException e) {
             System.err.println("Don't know about host " + hostName);
             System.exit(1);
         } catch (IOException e) {
             System.err.println("Client (connect): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         }
     }
     
     public List<Object[]> logIn(String username,String pass) {

  	   List<Object[]> data = new ArrayList<Object[]>();

         if (out == null) {
             connect();
             System.out.println("login connect");
         }

         try {
             Text fromServer = null;
             Text fromUser = new Text();

             // send the command with data to server
             fromUser.methodName = "login";
               boolean  val2= Validator.validatePas(pass);
              // boolean val3=AgentValidator.validateTel(telephone);
               boolean val4=Validator.validateUser(username);
               if(val2&&val4)
   	        {
            
             Object[] o = {username};
             Object[] p = {pass};
             fromUser.data.add(o);
             fromUser.data.add(p);}

             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();

                 data = fromServer.data;

             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         } catch (IOException e) {
             System.err.println("Client (login): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
      	 // closeServer();
         }

         return data;
     }
     
     public void btnLogInCliked() {
  	   String pass=logInView.getPass();
  	   String username=logInView.getUsern();
  	  List<Object[]> data = logIn(username,pass);
  	   String typeGet=(String)data.get(0)[0];
  	   id_user=(Integer)data.get(1)[0];
  	   System.out.println(id_user);
  	   if(typeGet.equals("admin"))
  	   {
  		   logInView.ViewAdmin();
  	   }
  	   if(typeGet.equals("manager")) {
  		 //  logInView.ViewClient();
  		   logInView.ViewManager();
  		   logInView.ViewClient();
  	   }
        if(typeGet.equals("regular")) {
  		   logInView.ViewRegular();
  	   }
  	   
     } 
  	   
  	   
     public void closeServer() {
         System.out.println("Client: tell server to close");
         try {
             out.writeObject(null);
             in.close();
             out.close();
         } catch (IOException e) {
             e.printStackTrace();
         }
         System.out.println("Client: closed");
     }

     public static void main(String[] args) throws FileNotFoundException, DocumentException {

    	//ClientServer cs= new ClientServer( 1342);
     	LogInView lv=new LogInView();
     	
 		lv.start();
 		

 		/*
 		Client c = new Client();
 		c.retrieveAll();
 		System.out.println("SECOND!!!!!!!!");
 		c.retrieveAll();
 		
 		c.closeServer();*/
 		
 	}
     public void addTask() {

         if (out == null) {
             connect();
         }

         try {
             Text fromServer = null;
             Text fromUser = new Text();
             int idAngajat=Integer.parseInt(logInView.getMV().getIdAgajat());
             
             String date=logInView.getMV().getDueDate();
             
             fromUser.methodName = "giveTask";
             Object[] o0 = {idAngajat,id_user,date};
            
             
             fromUser.data.add(o0);
           
            // fromUser.data.add(new Object[]{id_show});

             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         } catch (IOException e) {
             System.err.println("Client (add recomandare): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
         }

     }
     private Text displayTaskFor() {

         if (out == null) {
             connect();
         }
         Text fromServer=null;
         try {
              fromServer = null;
             Text fromUser = new Text();

             // send the command with data to server
             fromUser.methodName = "getTaskForEmp";
             int idAngajat=Integer.parseInt(logInView.getMV().getIdAgajat());
             Object[] o = {idAngajat};
             fromUser.data.add(o);

             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

             // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
             // tell and send to gui what to display from the data received from the server
             System.out.println("Client: send data to gui");
           //  List<Object[]> data = new ArrayList<Object[]>();
             //for (Object[] ob : fromServer.data) {
               //  data.add(new Object[]{ob[0]}); // the name of the show
          //   }

           //  logInView.getMV().displayHistory(data);

         } catch (IOException e) {
             System.err.println("Client (tasks for emp): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {}
         return fromServer;   
     }
     

     void displayPrezenta() {

    	  if (out == null) {
             connect();
         }

         try {
             Text fromServer = null;
             Text fromUser = new Text();

             // send the command with data to server
             fromUser.methodName = "dispalyPrezenta";
            // Object[] o = {id_user};
             //fromUser.data.add(o);

             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

             // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
             // tell and send to gui what to display from the data received from the server
             System.out.println("Client: send data to gui");
             List<Object[]> data = new ArrayList<Object[]>();
             for (Object[] ob : fromServer.data) {
                 data.add(new Object[]{ob}); // the name of the show
             }

             logInView.getMV().displayShows(data);

         } catch (IOException e) {
             System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
         }
     }
     public Text displayAllA() {
			Text fromServer=null;
			if (out == null) {
				connect();
			}
			
	        try {
	             fromServer = null;
	            Text fromUser = new Text();
	 
	            // send the command with data to server
	            fromUser.methodName = "allAngajat"; 
	            System.out.println("Admin: write to server all shows");
	            out.writeObject(fromUser); 
	            
	            // get data from server
	            try {
					fromServer = (Text) in.readObject();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
	            
	            // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
	            // tell and send to gui what to display from the data received from the server
	            System.out.println("Admin: send data to gui");        
		       // logInView.getAV().displayShows(fromServer.data);

				//closeServer();

	        } catch (IOException e) {
	            System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
	            System.exit(1);
	        } finally {
	        	// tell the server to close
	        	//closeServer();
	        }
	        return fromServer;
		}
     List<Object[]> search(String name) {

         List<Object[]> data = new ArrayList<Object[]>();

         if (out == null) {
             connect();
         }

         try {
             Text fromServer = null;
             Text fromUser = new Text();

             // send the command with data to server
             fromUser.methodName = "search";
             Object[] o = {name};
             fromUser.data.add(o);

             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();

                 data = fromServer.data;

             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         } catch (IOException e) {
             System.err.println("Client (search): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
         }

         return data;
     }
     List<Object[]> searchPrezenta(int user_id) {

         List<Object[]> data = new ArrayList<Object[]>();

         if (out == null) {
             connect();
         }

         try {
             Text fromServer = null;
             Text fromUser = new Text();

             // send the command with data to server
             fromUser.methodName = "searchPrezente";
             Object[] o = {user_id};
             fromUser.data.add(o);

             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();

                 data = fromServer.data;

             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         } catch (IOException e) {
             System.err.println("Client (search): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
         }

         return data;
     }
     
     
     public void retrieveAllTask(int idUser) {

         if (out == null) {
             connect();
         	System.out.println("nulll retriveall");
         }

         try {
         	 System.out.println("clientcont bntDisp");
             Text fromServer = null;
             Text fromUser = new Text();

             // send the command with data to server
             fromUser.methodName = "retrieveAllTask";
             Object[] i = {idUser};
             fromUser.data.add(i);
             System.out.println("Client: write to server");
             out.writeObject(fromUser);

             // get data from server
             try {
                 fromServer = (Text) in.readObject();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

             // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
             // tell and send to gui what to display from the data received from the server
             System.out.println("Client: send data to gui");
             List<Object[]> lo = new ArrayList<Object[]>();
             for(Object[] ob : fromServer.data) {
             	Object[] n={ob[2], ob[4],ob[0]};
 	        	lo.add(n);
 		    	 System.out.println(ob[2] + " " + ob[3]);
 	        }
             System.out.println(lo);
            logInView.getRV().displayShows(lo);;

             //closeServer();

         } catch (IOException e) {
             System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
             // tell the server to close
             //closeServer();
         }
     }
     
	public void btnGiveTaskClicked() {
		// TODO Auto-generated method stub
		addTask();
		
	}

	public void btnStatusTaksClicked() {
		// TODO Auto-generated method stub
		logInView.getMV().displayHistory( displayTaskFor().data);
		
	}

	public void btnRaportClicked() {
		// TODO Auto-generated method stub
		java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
		List<Task> data = new ArrayList<Task>();
        for (Object[] ob : displayTaskFor().data) {
        	
        	int idAngajat=(Integer)ob[0];
        	//System.out.println(()ob[1]);
        	
        	Date dueDate=(Date)ob[1];
        	//String dueDate=(String)ob[1];
        	//System.out.println(dueDate);
        	String status=(String)ob[2];
        	Task t= new Task(idAngajat,date,status);
        	data.add(t);
        }
        for (Task t : data) {
        	if(t.getStatusTask().equals("done"))
        		report1.generateReport(t.getIdE(), t.getDueDate(),t.getStatusTask());
        		report2.generateReport(t.getIdE(), t.getDueDate(),t.getStatusTask());
        		
        }
        
		
	}
	  public void RowDataSelected() {
          String name = logInView.getAnV().getRowData();
          List<Object[]> data = search(name);

          // {s.getId_show(), s.getName(), s.getType(), s.getDescription(), s.getRelease_date(), s.getImdb_no(), s.getImdb_s()}
          // tell and send to gui what to display from the data received from the server
          System.out.println("Client: send data to gui");
          List<Object[]> data1 = new ArrayList<Object[]>();
          List<Object[]> data2 = new ArrayList<Object[]>();
          
          for (Object[] ob : data) {
              data1.add(new Object[]{ob[2], ob[0],ob[3]});
          }
          for (Object[] ob : data) {
              data2.add(new Object[]{ob[4], ob[5],ob[6]});
          }

          logInView.getAnV().displayShows(data1);
          logInView.getAnV().displayDetails(data2);
      }
	  
	  public void addUser() {

          if (out == null) {
              connect();
          }

          try {
              Text fromServer = null;
              Text fromUser = new Text();
              int id=Integer.parseInt(logInView.getAV().getIdUser());
              int idM=Integer.parseInt(logInView.getAV().getIdM());
              String type=logInView.getAV().getType();
              String pass=logInView.getAV().getPassword();
              String username=logInView.getAV().getUserName();
              String departament=logInView.getAV().getDepartament();
              String adresa=logInView.getAV().getAdresa();
              String detali=logInView.getAV().getDetalii();
              String nume=logInView.getAV().getNume();
              String prenume=logInView.getAV().getPrenume();
              // send the command with data to server
              fromUser.methodName = "addUser";
              Object[] o0 = {id};
              Object[] o1 = {idM};
              Object[] o2 = {nume};
              Object[] o3 = {prenume};
              Object[] o4 = {detali};
              Object[] o5 = {adresa};
              Object[] o6 = {username};
              Object[] o7 = {pass};
              Object[] o8 = {type};
              Object[] o9 = {departament};
              fromUser.data.add(o0);
              fromUser.data.add(o1);
              fromUser.data.add(o2);
              fromUser.data.add(o3);
              fromUser.data.add(o4);
              fromUser.data.add(o5);
              fromUser.data.add(o6);
              fromUser.data.add(o7);
              fromUser.data.add(o8);
              fromUser.data.add(o9);
             // fromUser.data.add(new Object[]{id_show});

              System.out.println("Client: write to server");
              out.writeObject(fromUser);

              // get data from server
              try {
                  fromServer = (Text) in.readObject();
              } catch (ClassNotFoundException e) {
                  e.printStackTrace();
              }

          } catch (IOException e) {
              System.err.println("Client (retrieveAll): Couldn't get I/O for the connection to " + hostName);
              System.exit(1);
          } finally {
          }

      }
	  
	  
	  
	  
	public void btnAddUserClicked() {
		// TODO Auto-generated method stub
		addUser();
		
	}
	
	 void deleteUser() {

         if (out == null) {
             connect();
         }

         try {
             Text fromServer = null;
             Text fromUser = new Text();
             
             int id=Integer.parseInt(logInView.getAV().getIdUser());
             
             // send the command with data to server
             fromUser.methodName = "deleteUser";
             Object[] o = {id};
             
             fromUser.data.add(o);
             System.out.println("Client: write to server");
             out.writeObject(fromUser);
             try {
                 fromServer = (Text) in.readObject();
             } catch (ClassNotFoundException e) {
                 e.printStackTrace();
             }

         } catch (IOException e) {
             System.err.println("Client (deleteUser): Couldn't get I/O for the connection to " + hostName);
             System.exit(1);
         } finally {
         }

     }
     
	
	
	

	public void btnDeleteUserClicked() {
		// TODO Auto-generated method stub
		deleteUser();
		
	}

	public void btnUpDateUserClicked() {
		// TODO Auto-generated method stub
		 int id=Integer.parseInt(logInView.getAV().getIdUser());
         int idM=Integer.parseInt(logInView.getAV().getIdM());
         String type=logInView.getAV().getType();
         String pass=logInView.getAV().getPassword();
         String username=logInView.getAV().getUserName();
         String departament=logInView.getAV().getDepartament();
         String adresa=logInView.getAV().getAdresa();
         String detali=logInView.getAV().getDetalii();
         String nume=logInView.getAV().getNume();
         String prenume=logInView.getAV().getPrenume();
         // send the command with data to server
         List<Object[]> data = new ArrayList<Object[]>();
         Object[] o0 = {id,idM,nume,prenume,detali,adresa,username,pass,type,departament};
        
         data.add(o0);
        
		updateA(data);
		
	}

	public void btnListUsersClicked() {
		// TODO Auto-generated method stub
		logInView.getAV().displayShows(displayAllA().data);
		
	}

	



	public void btnDisplayAllClicked() {
		// TODO Auto-generated method stub
		 logInView.getAnV().displayShows(displayAllA().data);
		 List<Object[]> data = displayAllA().data;
		 List<Angajat> angajati=new ArrayList<Angajat>();
		 
		//s.getIdE(), s.getNume(), s.getPrenume(), s.getDepartament(),s.getDetali()};
		 for (Object[] ob : data) {
           int id=(Integer)ob[2];
           int idM=(Integer)ob[3];
           String name= (String)ob[0];
           String prenume= (String)ob[1];
           String departament= (String)ob[4];
           String detali=(String)ob[5];
           System.out.println(id);
           Angajat a= new Angajat(id,idM,name,prenume,departament,detali);
           angajati.add(a);
           System.out.println("add"+a.getNume()+"\n");
         }
		 
		 Criteria c=new ManagerCriteria(id_user);
		 //List<Angajat> angajatiM=new ArrayList<Angajat>();
		 List<Angajat> selectedA=new ArrayList<Angajat>();
		 selectedA=c.meetCriteria(angajati);
		 List<Object[]> data1 = new ArrayList<Object[]>();
		// System.out.println("for"+selectedA.get(0));
		 for (Angajat an : selectedA) {
			 System.out.println("criteria "+an.getNume());
			 Object[] o = {an.getNume(),an.getDepartament(),an.getDepartament()};
			 data1.add(o);
			 System.out.println("add"+an.getNume());
		 }
		 logInView.getAnV().displayHistory(data1);
		 }
		 
	public void update(List<Object[]> data) {
        if (out == null) {
            connect();
        }

        try {
            Text fromServer = null;
            Text fromUser = new Text();

            // send the command with data to server
            fromUser.methodName = "updateManager";
            fromUser.data = data;

            System.out.println("Client: write to server");
            out.writeObject(fromUser);

            // get data from server
            try {
                fromServer = (Text) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }

    }
	
	public void updateA(List<Object[]> data) {
        if (out == null) {
            connect();
        }

        try {
            Text fromServer = null;
            Text fromUser = new Text();

            // send the command with data to server
            fromUser.methodName = "updateA";
            fromUser.data = data;

            System.out.println("Client: write to server");
            out.writeObject(fromUser);

            // get data from server
            try {
                fromServer = (Text) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }

    }

	public void btnAddSubClicked() {
		// TODO Auto-generated method stub
		String nume= logInView.getAnV().getAddTextField();
          
		List<Object[]> data = search(nume);
		 Angajat a=null;
		//s.getIdE(), s.getNume(), s.getPrenume(), s.getDepartament(),s.getDetali()};
		/* for (Object[] ob : data) {
           int id=(Integer)ob[0];
           int idM=(Integer)ob[1];
           String name= (String)ob[2];
           String prenume= (String)ob[3];
           String departament= (String)ob[4];
           String detali= (String)ob[5];
           a=new Angajat(id,name,prenume,departament,detali);
           a1.add(a);
         }*/
		 data.get(0)[1]=id_user;
		 update(data);
		
		;
	}

	public void btnDisplayAllTasksClicked() {
		// TODO Auto-generated method stub
		retrieveAllTask(id_user);
		List<Object[]> data1 = searchPrezenta(id_user);
		List<Object[]> data2 = new ArrayList<Object[]>();
        
        for (Object[] ob : data1) {
            data2.add(new Object[]{ob[0],ob[2],ob[3]});
        }
	logInView.getRV().displayHistory(data2);
	}
	public void updatePrezenta(List<Object[]> data) {
        if (out == null) {
            connect();
        }

        try {
            Text fromServer = null;
            Text fromUser = new Text();

            // send the command with data to server
            fromUser.methodName = "updatePrezenta";
            fromUser.data = data;

            System.out.println("Client: write to server");
            out.writeObject(fromUser);

            // get data from server
            try {
                fromServer = (Text) in.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }
        
	}
	public void updateTask(List<Object[]> data) {
            if (out == null) {
                connect();
            }

            try {
                Text fromServer = null;
                Text fromUser = new Text();

                // send the command with data to server
                fromUser.methodName = "updateTask";
                fromUser.data = data;

                System.out.println("Client: write to server");
                out.writeObject(fromUser);

                // get data from server
                try {
                    fromServer = (Text) in.readObject();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } catch (IOException e) {
                System.err.println("Client (update): Couldn't get I/O for the connection to " + hostName);
                System.exit(1);
            } finally {
            }

    }
	public void btnAddPrezClicked() {
		// TODO Auto-generated method stub
		//String nume= logInView.getAnV().getAddTextField();
		 //int id=Integer.parseInt(logInView.getRV().getSearchData());
		List<Object[]> data = searchPrezenta(id_user);
		 
		//s.getIdE(), s.getNume(), s.getPrenume(), s.getDepartament(),s.getDetali()};
		/* for (Object[] ob : data) {
           int id=(Integer)ob[0];
           int idM=(Integer)ob[1];
           String name= (String)ob[2];
           String prenume= (String)ob[3];
           String departament= (String)ob[4];
           String detali= (String)ob[5];
           a=new Angajat(id,name,prenume,departament,detali);
           a1.add(a);
         }*/
		 int nr=(Integer)data.get(0)[2];
		 nr++;
		 System.out.println("Prezenta:"+nr);
		 data.get(0)[2]= (Object)nr;
		 System.out.println("Prezenta:"+data.get(0)[2]);
		 updatePrezenta(data);
		
	}
	
	List<Object[]> searchTask(int user_id) {

        List<Object[]> data = new ArrayList<Object[]>();

        if (out == null) {
            connect();
        }

        try {
            Text fromServer = null;
            Text fromUser = new Text();

            // send the command with data to server
            fromUser.methodName = "searchTask";
            Object[] o = {user_id};
            fromUser.data.add(o);

            System.out.println("Client: write to server");
            out.writeObject(fromUser);

            // get data from server
            try {
                fromServer = (Text) in.readObject();

                data = fromServer.data;

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.err.println("Client (search): Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        } finally {
        }

        return data;
    }
    
    
	

	public void btnStatusTaskClicked() {
		
		// TODO Auto-generated method stub
		int id=Integer.parseInt(logInView.getRV().getSearchData());
		List<Object[]> data = searchTask(id);
		//System.out.println(data.get(0)[3].toString());
		data.get(0)[4]=(String) logInView.getRV().getRateTextField();
		setChanged();
		notifyObservers(); 
		updateTask(data);	
	}
}
