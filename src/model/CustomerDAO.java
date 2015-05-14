package model;

import java.util.List;

public interface CustomerDAO {
	
	void recordCustomer(Customer cust);
	void deleteCustomer(String id);
	List<Customer> getCustomers();
}
