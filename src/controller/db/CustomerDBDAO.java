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
		
		String sql = "INSERT INTO CUSTOMERS_SAMPLE (ID, FIRST_NAME, LAST_NAME, ADDRESS)" +
				 	 "VALUES (?, ?, ?, ?)";
		
		try (Connection c = ConnectionFactory.newConnectio(accout);
			 PreparedStatement pstm = c.prepareStatement(sql)
		 ) {
			
			pstm.setString(1, cust.getId());
			pstm.setString(2, cust.getFirstName());
			pstm.setString(3, cust.getLastName());
			pstm.setString(4, cust.getAddress());
			pstm.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(String id) {
		
		String sql = "DELETE FROM CUSTOMERS_SAMPLE WHERE CUSTOMERS_SAMPLE.ID = ?";
		
		try (Connection c = ConnectionFactory.newConnectio(accout);
			 PreparedStatement pstm = c.prepareStatement(sql);
		) {
			
			pstm.setString(1, id);
			pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		
		String sql = "SELECT * FROM CUSTOMERS_SAMPLE";
		
		try (Connection c = ConnectionFactory.newConnectio(accout);
			 Statement stm = c.createStatement();
			 ResultSet rs = stm.executeQuery(sql);
		) {
			while(rs.next()) {
				Customer currentCustomer = new Customer(
						rs.getString(1),
						rs.getString(2),
						rs.getString(3),
						rs.getString(4)
						);
				customers.add(currentCustomer);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	public void createTable() {
		
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
	
		
		try (Connection c = ConnectionFactory.newConnectio(accout);
			 Statement stm = c.createStatement();
		) {
			
			stm.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void dropTable() {
		
		String sql = "DROP TABLE CUSTOMERS_SAMPLE";
		try (Connection c = ConnectionFactory.newConnectio(accout);
			 Statement stm = c.createStatement();
			) {
			
			stm.execute(sql);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
