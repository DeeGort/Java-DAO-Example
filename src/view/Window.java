package view;

import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import model.Customer;
import model.db.Account;
import controller.db.CustomerDBDAO;

import javax.swing.JScrollPane;




public class Window extends JFrame {

	private JPanel contentPane;
	private JTextField idBox3;
	private JTable table;
	private Account account;
	private JTextField firstNameBox;
	private JTextField lastNameBox;
	private JTextField addressBox;
	private JTextField idBox;

	public Window() {
		try {
			  UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch(Exception e) {
			  System.out.println("Error setting native LAF: " + e);
			}
		
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 314);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel deletePanel = new JPanel();
		deletePanel.setLayout(null);
		deletePanel.setBorder(BorderFactory.createTitledBorder("Delete customer"));
		deletePanel.setBounds(10, 185, 240, 93);
		contentPane.add(deletePanel);
		
		JButton deleteButton = new JButton("Delete");
		deleteButton.setBounds(141, 59, 89, 23);
		deletePanel.add(deleteButton);
		
		idBox3 = new JTextField();
		idBox3.setColumns(10);
		idBox3.setBounds(112, 28, 118, 20);
		deletePanel.add(idBox3);
		
		JLabel lblId_1 = new JLabel("ID");
		lblId_1.setBounds(10, 28, 92, 20);
		deletePanel.add(lblId_1);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(BorderFactory.createTitledBorder("Table"));
		panel_3.setBounds(260, 11, 376, 267);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 21, 356, 201);
		panel_3.add(scrollPane);
		
		
		table = new JTable(new DefaultTableModel(new Object[][]{}, new Object[]{"ID", "FIRST_NAME", "LAST_NAME", "ADDRESS"}));
		scrollPane.setViewportView(table);
		
		JButton createTableButton = new JButton("Create sample table");
		createTableButton.setBounds(10, 233, 129, 23);
		panel_3.add(createTableButton);
		
		JButton dropButton = new JButton("Drop table");
		dropButton.setBounds(149, 233, 111, 23);
		panel_3.add(dropButton);
		
		JButton refreshButton = new JButton("Refresh");
		refreshButton.setBounds(270, 233, 96, 23);
		panel_3.add(refreshButton);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(BorderFactory.createTitledBorder("Record customer"));
		panel.setBounds(10, 11, 240, 170);
		contentPane.add(panel);
		
		JButton recordButton = new JButton("Record");
		recordButton.setBounds(141, 136, 89, 23);
		panel.add(recordButton);
		
		firstNameBox = new JTextField();
		firstNameBox.setColumns(10);
		firstNameBox.setBounds(112, 53, 118, 20);
		panel.add(firstNameBox);
		
		JLabel label_3 = new JLabel("First Name");
		label_3.setBounds(10, 53, 92, 20);
		panel.add(label_3);
		
		JLabel label_4 = new JLabel("Last Name");
		label_4.setBounds(10, 79, 92, 20);
		panel.add(label_4);
		
		lastNameBox = new JTextField();
		lastNameBox.setColumns(10);
		lastNameBox.setBounds(112, 79, 118, 20);
		panel.add(lastNameBox);
		
		JLabel label_5 = new JLabel("Address");
		label_5.setBounds(10, 105, 92, 20);
		panel.add(label_5);
		
		addressBox = new JTextField();
		addressBox.setColumns(10);
		addressBox.setBounds(112, 105, 118, 20);
		panel.add(addressBox);
		
		JLabel label_6 = new JLabel("ID");
		label_6.setBounds(10, 27, 92, 20);
		panel.add(label_6);
		
		idBox = new JTextField();
		idBox.setColumns(10);
		idBox.setBounds(112, 27, 118, 20);
		panel.add(idBox);
		
		recordButton.addActionListener(e -> {
			CustomerDBDAO dbdao = new CustomerDBDAO(account);
			Customer customer = new Customer(idBox.getText(),
											firstNameBox.getText(),
											lastNameBox.getText(),
											addressBox.getText());
			dbdao.recordCustomer(customer);
			listCustomers();
		});
		
		deleteButton.addActionListener(e -> {
			CustomerDBDAO dbdao = new CustomerDBDAO(account);
			dbdao.deleteCustomer(idBox3.getText());
			listCustomers();
		});
		
		createTableButton.addActionListener(e -> {
			CustomerDBDAO dbdao = new CustomerDBDAO(account);
			dbdao.createTable();
		});
		
		dropButton.addActionListener(e -> {
			CustomerDBDAO dbdao = new CustomerDBDAO(account);
			dbdao.dropTable();
		});
		
		refreshButton.addActionListener(e -> {
			listCustomers();
		});
	}

	public void setAccout(Account account) {
		this.account = account;
	}
	
	public void listCustomers() {
		CustomerDBDAO dbdao = new CustomerDBDAO(account);
		
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(0);
		for (Customer c : dbdao.getCustomers()) {
			
			model.addRow(new Object[]{c.getId(), c.getFirstName(), c.getLastName(), c.getAddress()});
		}
	}
}
