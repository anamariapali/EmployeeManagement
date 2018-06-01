package client;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
public class AdminView {


private JFrame frame;
private JTextField idUser;
private JTextField username;
private JTextField password;
private JTextField nume;
private JTextField type;
private JTextField idM;
private JTextField detalii;
private JTextField prenume;
private JTextField departament;
private JTextField adresa;
private JTextField no;
private DefaultTableModel tableModel;
private String col_table[]= {"id","Name", "Type","username"};
private JTable table_1;
private JScrollPane scrollPane;
private client.LogInCont client;

public JFrame getAFrame()
{
	return this.frame;
}
/**
 * Create the application.
 */
public AdminView(LogInCont client) {  
	System.out.println("View Admin");
    this.client = client;
	initialize();
}
 public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { System.out.println("Client View run");
                    AdminView window = new AdminView(client);
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
/**
 * 
 */
private void initialize() {
	frame = new JFrame();
	frame.setBounds(100, 100, 700, 700);
	//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(frame,
                        "Are you sure to close this window?", "Really Closing?",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                    client.closeServer();
                    System.exit(0);
                }
            }
        });
	frame.getContentPane().setLayout(null);
	
	 table_1 = new JTable();
        table_1.setBounds(45, 480, 300, 250);
        frame.getContentPane().add(table_1);
        scrollPane = new JScrollPane(table_1);
	
        scrollPane.setBounds(45, 480, 300, 100);
        frame.getContentPane().add(scrollPane);
        tableModel = new DefaultTableModel(col_table, 0);
        
	JButton btnAddUser = new JButton("Add user");
	btnAddUser.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            client.btnAddUserClicked();
        }
    });
	btnAddUser.setBounds(45, 229, 110, 25);
	frame.getContentPane().add(btnAddUser);
	
	JButton btnDeleteUser = new JButton("Delete user");
	btnDeleteUser.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            client.btnDeleteUserClicked();
        }
    });
	btnDeleteUser.setBounds(45, 279, 110, 25);
	frame.getContentPane().add(btnDeleteUser);
	
	JButton btnUpdateUser = new JButton("Update user");
	btnUpdateUser.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            client.btnUpDateUserClicked();
        }
    });
	btnUpdateUser.setBounds(45, 329, 110, 25);
	frame.getContentPane().add(btnUpdateUser);
	
	JButton btnListUsers = new JButton("List users");
	btnListUsers.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
            client.btnListUsersClicked();
        }
    });
	btnListUsers.setBounds(45, 379, 110, 25);
	frame.getContentPane().add(btnListUsers);
	
	
	
	
	
	
	
	idUser = new JTextField();
	idUser.setBounds(139, 13, 116, 22);
	frame.getContentPane().add(idUser);
	idUser.setColumns(10);
	
	type = new JTextField();
	type.setBounds(265, 13, 116, 22);
	frame.getContentPane().add(type);
	type.setColumns(10);
	
	nume = new JTextField();
	nume.setBounds(139, 59, 116, 22);
	frame.getContentPane().add(nume);
	nume.setColumns(10);
	
	prenume = new JTextField();
	prenume.setBounds(265, 59, 116, 22);
	frame.getContentPane().add(prenume);
	prenume.setColumns(10);
	
	detalii = new JTextField();
	detalii.setBounds(139, 108, 116, 22);
	frame.getContentPane().add(detalii);
	detalii.setColumns(10);
	
	adresa = new JTextField();
	adresa.setBounds(265, 108, 116, 22);
	frame.getContentPane().add(adresa);
	adresa.setColumns(10);
	
	username = new JTextField();
	username.setBounds(139, 156, 116, 22);
	frame.getContentPane().add(username);
	username.setColumns(10);
	
	password = new JTextField();
	password.setBounds(265, 156, 116, 22);
	frame.getContentPane().add(password);
	password.setColumns(10);
	
	idM = new JTextField();
	idM.setBounds(265, 189, 116, 22);
	frame.getContentPane().add(idM);
	idM.setColumns(10);
	
	departament = new JTextField();
	departament.setBounds(139, 189, 116, 22);
	frame.getContentPane().add(departament);
	departament.setColumns(10);
	
	no = new JTextField();
	no.setBounds(202, 229, 110, 25);
	frame.getContentPane().add(no);
	no.setColumns(10);
	
	
	JLabel lblIdUser = new JLabel("ID user/type");
	lblIdUser.setBounds(45, 16, 80, 16);
	frame.getContentPane().add(lblIdUser);
	
	JLabel lblUsername = new JLabel("Nume/Prenume");
	lblUsername.setBounds(45, 62, 80, 16);
	frame.getContentPane().add(lblUsername);
	
	JLabel lblPassword = new JLabel("detali/adresa");
	lblPassword.setBounds(45, 111, 80, 16);
	frame.getContentPane().add(lblPassword);
	
	JLabel lblType = new JLabel("username/password");
	lblType.setBounds(45, 159, 80, 16);
	frame.getContentPane().add(lblType);
	
	
	JLabel lblRate = new JLabel("departament/idManager");
	lblRate.setBounds(45, 189, 80, 16);
	frame.getContentPane().add(lblRate);
	
	JButton btnLogOut = new JButton("Log out");
	btnLogOut.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			//LogInView log = new LogInView();
		//	log.getFrame().setVisible(true);
			frame.setVisible(false);
		}
	});
	btnLogOut.setBounds(45, 429, 110, 25);
	frame.getContentPane().add(btnLogOut);
}

 public void displayShows(List<Object[]> data) {

        tableModel.setRowCount(0);	 // delete the old rows
        table_1.setModel(tableModel);

        System.out.println("View table: ");
        for(Object[] ob : data) {
            System.out.println(ob[0] + " " + ob[1] + " "+ ob[2]+" " +ob[3]);
        }

        for(Object[] show: data) {
            tableModel.addRow(show);
        }

    }

public String getIdUser() {
	 String s=idUser.getText();
    if(s== null)
    idUser.setText("introduceti id");
    
    return s;
}
public String getUserName() {
	 String s= username.getText();
	if(s!= null)
		return s;
   return"name";
}
public String getPassword() {
  
    
    String s=password.getText();
	if(s!= null)
		return s;
   return"name";
    
}
public String getType() {
	
	String s=type.getText();
	if(s!= null)
		return s;
   return"name";
    
}
public void setIdUser(int id1) {
	String id=String.valueOf(id1);
     idUser.setText(id);
}

public String getNume() {
    return nume.getText();
}
public String getPrenume() {
    return prenume.getText();
}
public String getDetalii() {
    return detalii.getText();
}
public String getDepartament() {
    return departament.getText();
}
public String getAdresa() {
    return adresa.getText();
}
public String getIdM() {
    return idM.getText();
}
public String getNo() {
    return no.getText();
}

}