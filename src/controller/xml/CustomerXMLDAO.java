package controller.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import model.Customer;
import model.CustomerDAO;
import model.xml.DocumentFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class CustomerXMLDAO implements CustomerDAO {
	
	private final String URL = "./customerssample.xml";

	@Override
	public void recordCustomer(Customer cust) {
		Document d = DocumentFactory.openDocument(URL);
		
		Element root = d.getDocumentElement();
		
		Element customer = d.createElement("Customer");
		root.appendChild(customer);
		
		Attr attr = d.createAttribute("ID");
		attr.setValue(cust.getId());
		customer.setAttributeNode(attr);
		
		Element firstName = d.createElement("FirstName");
		firstName.appendChild(d.createTextNode(cust.getFirstName()));
		customer.appendChild(firstName);
		
		Element lastName = d.createElement("LastName");
		lastName.appendChild(d.createTextNode(cust.getLastName()));
		customer.appendChild(lastName);
		
		Element address = d.createElement("Address");
		address.appendChild(d.createTextNode(cust.getAddress()));
		customer.appendChild(address);
		
		writeXML(d);
	}

	@Override
	public void deleteCustomer(String id) {
		Document d = DocumentFactory.openDocument(URL);
		
		Element root = d.getDocumentElement();
		
		NodeList nList = d.getElementsByTagName("Customer");
		for (int i = 0; i != nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element customer = (Element) node;
				if (customer.getAttribute("ID").equals(id)) {
					root.removeChild(customer);
					break;
				}
			}
		}
		
		writeXML(d);
	}

	@Override
	public List<Customer> getCustomers() {
		List<Customer> customers = new ArrayList<>();
		
		Document d = DocumentFactory.openDocument(URL);
		
		NodeList nList = d.getElementsByTagName("Customer");
		for (int i = 0; i != nList.getLength(); i++) {
			Node node = nList.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element cust = (Element) node;
				
				String id = cust.getAttribute("ID");
				Customer customer = new Customer(id, cust.getFirstChild().getTextContent(),
														cust.getFirstChild().getNextSibling().getTextContent(),
														cust.getFirstChild().getNextSibling().getNextSibling().getTextContent());
				customers.add(customer);
			}
		}
		return customers;
	}
	
	public void createXML() {
		Document d = DocumentFactory.newDocument();
		Element root = d.createElement("Customers-Sample");
		
		d.appendChild(root);
		
		writeXML(d);
	}
	
	public void dropXML() {
    	new File(URL).delete();
	}
	
	private void writeXML(Document d) {
		try {
			Transformer transformer = TransformerFactory.newInstance()
				.newTransformer();
			
			transformer.transform(new DOMSource(d),
			new StreamResult(new File(URL)));
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
