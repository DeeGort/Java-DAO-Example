package controller.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Customer;
import model.CustomerDAO;
import model.db.Account;
import model.db.ConnectionFactory;


public class CustomerDBDAO implements CustomerDAO {

	private Account accout;
	
	public CustomerDBDAO(Account accout) {
		super();
		this.accout = accout;
	}

	@Override
	public void recordCustomer(Customer cust) {
		try {
			Connection c = ConnectionFactory.newConnectio(accout);
		
			String sql = "INSERT INTO CUSTOMERS_SAMPLE (ID, FIRST_NAME, LAST_NAME, ADDRESS)" +
							 "VALUES (?, ?, ?, ?)";
			
			PreparedStatement pstm = c.prepareStatement(sql);
			pstm.setString(1, cust.getId());
			pstm.setString(2, cust.getFirstName());
			pstm.setString(3, cust.getLastName());
			pstm.setString(4, cust.getAddress());
			pstm.executeUpdate();
		
			pstm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(String id) {
		try {
			Connection c = ConnectionFactory.newConnectio(accout);
		
			String sql = "DELETE FROM CUSTOMERS_SAMPLE WHERE CUSTOMERS_SAMPLE.ID = ?";
			PreparedStatement pstm = c.prepareStatement(sql);
			pstm.setString(1, id);
			pstm.executeUpdate();
		
			pstm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		try {
			Connection c = ConnectionFactory.newConnectio(accout);
			Statement stm = c.createStatement();
			
			String sql = "SELECT * FROM CUSTOMERS_SAMPLE";
			
			ResultSet rs = stm.executeQuery(sql);
			
			while(rs.next()) {
				Customer currentCustomer = new Customer(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
				customers.add(currentCustomer);
			}
		
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public void createTable() {
		try {
			Connection c = ConnectionFactory.newConnectio(accout);
			Statement stm = c.createStatement();
		
			String sql = "CREATE TABLE CUSTOMERS_SAMPLE " +
						"(" +
						"  ID VARCHAR2(20) NOT NULL" +
						", FIRST_NAME VARCHAR2(20) " +
						", LAST_NAME VARCHAR2(20) " +
						", ADDRESS VARCHAR2(120) " +
						", CONSTRAINT CUSTOMERS_SAMPLE_PK PRIMARY KEY " +
						"  (" +
						"    ID " +
						"  )" +
						"  ENABLE " +
						")";
			
			stm.execute(sql);
			
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		try {
			Connection c = ConnectionFactory.newConnectio(accout);
			Statement stm = c.createStatement();
		
			String sql = "DROP TABLE CUSTOMERS_SAMPLE";
			
			stm.execute(sql);
			
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
