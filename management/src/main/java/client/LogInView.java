package client;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.itextpdf.text.DocumentException;

import server.ClientServer;



public class LogInView {
	private JFrame frame;
	private JTextField type;
	private JTextField usern;
	private JPasswordField pass;
	private AdminView adminpanel;
	private AngajatView clientpanel;
	private ManagerView premiumpanel;
	private RegularView regularpanel;

	private int id_user;
	//private client.ClientCont client;
	private LogInCont logIn;
	public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { System.out.println("Client LogIn run");
                    LogInView window = new LogInView();
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
	
	public JFrame getFrame()
	{
		return this.frame;
	}
/**
 * Create the application.
 * @throws DocumentException 
 * @throws FileNotFoundException 
 */
	public 	LogInView() throws FileNotFoundException, DocumentException {  
		System.out.println("LogInView");
		logIn = new LogInCont(this);
		//ClientServer cs=new ClientServer(1342);
		//logIn.addObserver(cs);
		initialize();
}
	private void initialize()	{
	frame = new JFrame();
	frame.setBounds(200, 200, 450, 300);
	
	 frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	 frame.getContentPane().setLayout(null);
     frame.addWindowListener(new java.awt.event.WindowAdapter() {
         @Override
         public void windowClosing(java.awt.event.WindowEvent windowEvent) {
             if (JOptionPane.showConfirmDialog(frame,
                     "Are you sure to close this window?", "Really Closing?",
                     JOptionPane.YES_NO_OPTION,
                     JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){

                 logIn.closeServer();
                 System.exit(0);
             }
         }
     });
	
	JButton btnLogIn = new JButton("Log In");
	btnLogIn.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent arg0) {
        	logIn.btnLogInCliked();
        }
    });
	btnLogIn.setBounds(138, 184, 97, 25);
	frame.getContentPane().add(btnLogIn);
	
	JLabel lblUsername = new JLabel("Username");
	lblUsername.setBounds(37, 57, 66, 16);
	frame.getContentPane().add(lblUsername);
	
	JLabel lblPassword = new JLabel("Password");
	lblPassword.setBounds(37, 106, 56, 16);
	frame.getContentPane().add(lblPassword);
	

	
	usern = new JTextField();
	usern.setBounds(138, 54, 116, 22);
	frame.getContentPane().add(usern);
	usern.setColumns(10);
	

	
	pass = new JPasswordField();
	pass.setBounds(138, 106, 116, 22);
	frame.getContentPane().add(pass);
	pass.setColumns(10);
}
	public String getUsern() {
		String n=usern.getText();
		return n;
	}



	public String getPass() {
		return pass.getText().toString();
	}
	
public void ViewAdmin() {
	adminpanel=new AdminView(logIn);
	//adminpanel.start();
	adminpanel.getAFrame().setVisible(true);
	frame.setVisible(false);
}
public void ViewRegular() {
	regularpanel=new RegularView(logIn);
	//adminpanel.start();
	regularpanel.getFrame().setVisible(true);
	frame.setVisible(false);
}
public void ViewClient() {
	clientpanel=new AngajatView(logIn);
	//clientpanel.start();
	clientpanel.getFrame().setVisible(true);
	frame.setVisible(false);
}
public void ViewManager() {
	premiumpanel=new ManagerView(logIn);
	premiumpanel.getFrame().setVisible(true);
	frame.setVisible(false);
}
public AngajatView getAnV() {
	return this.clientpanel;
}
public AdminView getAV() {
	return this.adminpanel;
}

public ManagerView getMV() {
	return this.premiumpanel;
}
public RegularView getRV() {
	return this.regularpanel;
}
	
}