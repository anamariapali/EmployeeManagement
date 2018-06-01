package client;

import java.awt.EventQueue;
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


import client.LogInCont;

public class AngajatView {
    private JFrame frame;


    private LogInCont client;

    private JScrollPane scrollPane;
    private DefaultTableModel tableModel;
    private String col_table[]= {"Name", "Type","id"};
    private JScrollPane scrollPane_2;
    private DefaultTableModel tableModel_2;
    private String col_table_2[]= {"Description", "Release Date", "imdb"};
    private JScrollPane scrollPane_3;
    private DefaultTableModel tableModel_3;
    private String col_table_3[]= {"Name"};

    private JTable table_1;
    private JTextField textField_search;
    private JTable table_2;
    private JTable table_3;
    private JTextField textField_add;
    private JTextField textField_rate;
    private JTextField textField_coment;
	private AdminView adminpanel;

	private LogInView loginpanel;

    /**
     * Launch the application.
     */
	
    public void start() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try { System.out.println("Client View run");
                    AngajatView window = new AngajatView(client);
                    window.frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }



    /**
     * Create the application.
     */
    public AngajatView( LogInCont client) {  
    	System.out.println("View Client");
        this.client = client;
    	//client = new ClientCont(loginpanel,adminpanel,this);
        initialize();
    }
    public JFrame getFrame()
	{
		return this.frame;
	}

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // add shutdown hook

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

                    client.closeServer();
                    System.exit(0);
                }
            }
        });

        frame.getContentPane().setLayout(null);

        table_1 = new JTable();
        table_1.setBounds(25, 31, 223, 169);
        frame.getContentPane().add(table_1);
        scrollPane = new JScrollPane(table_1);
        table_1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                client.RowDataSelected();
            }
        });
        scrollPane.setBounds(10, 38, 388, 161);
        frame.getContentPane().add(scrollPane);

        JButton btn_display_all = new JButton("Display All");
        btn_display_all.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnDisplayAllClicked();
            }
        });
        btn_display_all.setBounds(79, 9, 103, 23);
        frame.getContentPane().add(btn_display_all);

        JLabel lblShows = new JLabel("Angajati");
        lblShows.setBounds(10, 13, 46, 14);
        frame.getContentPane().add(lblShows);

        textField_search = new JTextField();
        textField_search.setBounds(20, 210, 111, 20);
        frame.getContentPane().add(textField_search);
        textField_search.setColumns(10);

     

        table_2 = new JTable();
        table_2.setBounds(10, 265, 388, 96);
        frame.getContentPane().add(table_2);
        scrollPane_2 = new JScrollPane(table_2);
        scrollPane_2.setBounds(10, 265, 388, 96);
        frame.getContentPane().add(scrollPane_2);

        JLabel lblDetails = new JLabel("Details");
        lblDetails.setBounds(10, 240, 46, 14);
        frame.getContentPane().add(lblDetails);

        table_3 = new JTable();
        table_3.setBounds(408, 39, 320, 161);
        frame.getContentPane().add(table_3);
        scrollPane_3 = new JScrollPane(table_3);
        scrollPane_3.setBounds(408, 39, 320, 161);
        frame.getContentPane().add(scrollPane_3);

        JLabel lblHistory = new JLabel("Subordonati");
        lblHistory.setBounds(408, 13, 46, 14);
        frame.getContentPane().add(lblHistory);

        JButton btnAddToViewed = new JButton("Add Subordinates");
        btnAddToViewed.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                client.btnAddSubClicked();
            }
        });
        btnAddToViewed.setBounds(10, 414, 121, 23);
        frame.getContentPane().add(btnAddToViewed);

        textField_add = new JTextField();
        textField_add.setBounds(155, 415, 86, 20);
        frame.getContentPane().add(textField_add);
        textField_add.setColumns(10);

    
        tableModel = new DefaultTableModel(col_table, 0);
        tableModel_2 = new DefaultTableModel(col_table_2, 0);
        tableModel_3 = new DefaultTableModel(col_table_3, 0);
        
    }


    public void displayShows(List<Object[]> data) {

        tableModel.setRowCount(0);	 // delete the old rows
        table_1.setModel(tableModel);

        System.out.println("View table: ");
        for(Object[] ob : data) {
            System.out.println(ob[2] + " " + ob[0]);
        }

        for(Object[] show: data) {
            tableModel.addRow(show);
        }

    }


    public String getSearchData() {
        return textField_search.getText();
    }


    public void displayDetails(List<Object[]> data) {
        tableModel_2.setRowCount(0);	 // delete the old rows
        table_2.setModel(tableModel_2);

        System.out.println("View 2: ");
        for(Object[] ob : data) {
            System.out.println("detali "+ob[0] + " " + ob[1] + " " + ob[2]);
        }

        for(Object[] show: data) {
            tableModel_2.addRow(show);
        }
    }

    public String getRowData() {
        // get the model from the jtable
        DefaultTableModel model = (DefaultTableModel)table_1.getModel();

        // get the selected row index
        int selectedRowIndex = table_1.getSelectedRow();

        // set the selected row data into jtextfields
        String name = model.getValueAt(selectedRowIndex, 0).toString();

        textField_add.setText(name);

        return name;
    }

    public String getAddTextField() {
        System.out.println("!!!!!!" +  textField_add.getText().toString());
        return textField_add.getText().toString();
    }


    public void displayHistory(List<Object[]> data) {
        tableModel_3.setRowCount(0);	 // delete the old rows
        table_3.setModel(tableModel_3);

        System.out.println("View 3: ");
        for(Object[] ob : data) {
            System.out.println(ob[0]);
        }

        for(Object[] show: data) {
            tableModel_3.addRow(show);
        }
    }

    public String getRateTextField() {
        return textField_rate.getText().toString();
    }

    public String getComentTextField() {
        return textField_coment.getText().toString();
    }
   
	 
}
