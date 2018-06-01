package client;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;



public class ManagerView {
	
	
	private JFrame frame;
	private LogInCont login;
	private DefaultTableModel tableModel;
	private JTextField idAngajat;
	private JTextField dueDate;
    private String col_table[]= {"Name", "Type","username"};
    private JTable table_1;
    private JScrollPane scrollPane;
    private JScrollPane scrollPane_3;
    private DefaultTableModel tableModel_3;
    private JTable table_3;
    private String col_table_3[]= {"Name","Type","Detali"};
	public ManagerView(LogInCont login) {  
    	System.out.println("View manager");
        this.login = login;
		initialize();
	}
	
	 public JFrame getFrame()
		{
			return this.frame;
		}
	 
	 
	 private void initialize() {
		 
		 frame = new JFrame();
	        frame.setBounds(100, 100, 754, 486);

	        // used to firstly close the server before closing the window
	        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	        frame.addWindowListener(new java.awt.event.WindowAdapter() {
	            @Override
	            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
	                if (JOptionPane.showConfirmDialog(frame,
	                        "Are you sure to close this window?", "Really Closing?",
	                        JOptionPane.YES_NO_OPTION,
	                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

	                    login.closeServer();
	                    System.exit(0);
	                }
	            }
	        });

	        frame.getContentPane().setLayout(null);
	        
	      
	 
	        
	        idAngajat = new JTextField();
			idAngajat.setBounds(265, 13, 116, 22);
			frame.getContentPane().add(idAngajat);
			idAngajat.setColumns(10);
			
			dueDate = new JTextField();
			dueDate.setBounds(265, 59, 116, 22);
			frame.getContentPane().add(dueDate);
			dueDate.setColumns(10);
			
			JLabel lblIdUser = new JLabel("IdAngajat");
			lblIdUser.setBounds(45, 16, 80, 16);
			frame.getContentPane().add(lblIdUser);
			
			JLabel lblUsername = new JLabel("DueDate");
			lblUsername.setBounds(45, 62, 80, 16);
			frame.getContentPane().add(lblUsername);
			
			
			JButton btnAddUser = new JButton("GiveTask");
			btnAddUser.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent arg0) {
	               login.btnGiveTaskClicked();
	            }
	        });
			btnAddUser.setBounds(45, 229, 110, 25);
			frame.getContentPane().add(btnAddUser);
			
			JButton btnDeleteUser = new JButton("StatusTaks");
			btnDeleteUser.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent arg0) {
	            	 login.btnStatusTaksClicked();
	            }
	        });
			
			btnDeleteUser.setBounds(45, 279, 110, 25);
			frame.getContentPane().add(btnDeleteUser);
			
			JButton btnUpdateUser = new JButton("Raport");
			btnUpdateUser.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent arg0) {
	            	 login.btnRaportClicked();
	            }
	        });
			btnUpdateUser.setBounds(45, 329, 110, 25);
			frame.getContentPane().add(btnUpdateUser);
			
			 tableModel = new DefaultTableModel(col_table, 0);
		        
		        tableModel_3 = new DefaultTableModel(col_table_3, 0);
			   table_3 = new JTable();
			     table_3.setBounds(408, 39, 320, 161);
			     frame.getContentPane().add(table_3);
			     scrollPane_3 = new JScrollPane(table_3);
			     scrollPane_3.setBounds(408, 39, 320, 161);
			     frame.getContentPane().add(scrollPane_3);
			
	        
	       
		 
	 }
	 
	 public void displayShows(List<Object[]> data) {

	        tableModel.setRowCount(0);	 // delete the old rows
	        table_1.setModel(tableModel);

	        System.out.println("View table recomandari: ");
	        for(Object[] ob : data) {
	            System.out.println(ob[0]);
	        }

	        for(Object[] show: data) {
	            tableModel.addRow(show);
	        }

	    }

  
     
	  public void displayHistory(List<Object[]> data) {
	        tableModel_3.setRowCount(0);	 // delete the old rows
	        table_3.setModel(tableModel_3);

	        System.out.println("View 3: ");
	        for(Object[] ob : data) {
	            System.out.println(ob);
	        }

	        for(Object[] show: data) {
	            tableModel_3.addRow(show);
	        }
	    }

	 public String getIdAgajat() {
	        return idAngajat.getText();
	    }
		public String getDueDate() {
	        return dueDate.getText();
	    }
	 

}
