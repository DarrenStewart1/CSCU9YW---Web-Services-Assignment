package contact;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ContactService extends ContactServiceSkeleton {
	
	//The information used for creating the connection to the database
	 String databaseHost = "jdbc:mysql://localhost:3306/";
	 String databaseName ="mydb";
	 String databasePassword ="dst1";
	 String databaseUser =  "root";
	 String contactTable = "contact";
	 
	 //Regex used to validate a text field only contains upper case or lower case letters
	 private String validationRegex  = "^[a-zA-Z]+$";
	
	
	
	/**
	 * Method that is used to create a connection to an SQL database
	 * @return the required connection information to be able to access the database
	 * @throws ErrorFault Handles error message for when the database couldn't be created
	 */
	private Connection createDatabaseConnection() throws ErrorFault{
		
		
		try {
			//create a new instance of the JDBC driver for connecting to the database
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			//Creates a new connection to the database 
			Connection connection = DriverManager.getConnection(databaseHost + databaseName + "?user="+databaseUser + "&password=" + databasePassword);
			return connection;
			
		
		}catch(Exception e)
		{
			throw new ErrorFault("Error Accessing database : " + e.getMessage());
		}
		
	}
	
	/**
	 * Method that will perform an sql query to get all of the cities in the database that match the city
	 * that the user searched for
	 *@param RequestCity the city that was searched for by the user
	 *@return all of the contacts that match the city that was searched for by the user
	 *@ErrorFault Handles error message for when the city couldn't be found
	 */
	public contact.MultipleContacts getByCity(contact.RequestCity city) throws ErrorFault{
		
		//query that will search the contact info database table for the matching phone number that the user wants to search for
		String query ="SELECT * "+"FROM " + contactTable +" "+"WHERE city = '" + city.getRequestCity() +"'";
		
		MultipleContacts requestContact = new MultipleContacts();		
		ContactDetail contactInfo = new ContactDetail();
		ContactDetails contactInfos = new ContactDetails();
	

		try {
			Connection connection = createDatabaseConnection();
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY );			
			ResultSet resultSet = statement.executeQuery(query);
			
			//sets the result set to the last row so that the number of elements needed for the contact detail array can be assigned
			resultSet.last();

			//create an array with the size depending on the number of values stored in the result set that is used hold the contact information 
			ContactDetail[] contactInfoList = new ContactDetail[resultSet.getRow()];
			
			//sets the result set back to the first row
			resultSet.beforeFirst();
						
			if (resultSet.isBeforeFirst()) {
		
				//variable used to iterate the 
				int resultIndex = 0;
				//only iterate if the result set contains more elements
				while (resultSet.next()) {
				
				//sets the value of the contact information to the value of each returned column from the row that was returned by the result set
					
				contactInfo = getContactDetails(resultSet);

				//store each persons contact info that was returned from the query in an array
				contactInfoList[resultIndex++]  = contactInfo;
				
				//sets the value of the list of contacts to the array containing the returned elements
				contactInfos.setListOfContacts(contactInfoList);
						
				//sets the value of the request contact information to the value of the list of contact details
				requestContact.setMultipleContacts(contactInfos);						
				
			}
			connection.close();
			return requestContact;
			
			}else					
				throw new ErrorFault("City doesn't exist in database");
		
		}catch(Exception e){
			throw new ErrorFault("City doesn't exist in database");
		}
		
	}
	
		
	/**
	 * Method that will search for and return a persons contact info based on a phone number entered by the user
	 * @param contact the contact details that are being searched for
	 * @return the contact details of the person that has been searched for 
	 * @throws ErrorFault Handles error message for when the phone number couldn't be found
	 */
	public contact.RequestSingleContact getByPhoneNumber(contact.ContactPhoneNumber phoneNumber)throws ErrorFault{
		
		//Query that will search the contact info database table for the matching phone number that the user wants to search for
		String query ="SELECT * "+"FROM " + contactTable +" "+"WHERE phonenumber = '" + phoneNumber.getContactPhoneNumber() + "'";		
		RequestSingleContact requestContact = new RequestSingleContact();		
		ContactDetail contactInfo= new ContactDetail();
		
	
		try {
			Connection connection = createDatabaseConnection();
			Statement statement = connection.createStatement();			
			ResultSet resultSet = statement.executeQuery(query);
			
			
			
			if (resultSet.isBeforeFirst())
			{
				//only iterate if the result set contains another value
				while (resultSet.next()) {
				
				//sets the value of the contact information to the value of each returned column from the row that was returned by the result set

				contactInfo = getContactDetails(resultSet);
		
				//sets the value of the request to the value of the contact information that was found in the database
				requestContact.setRequestSingleContact(contactInfo);				
				
			}
				connection.close();
				return requestContact;
				
			}
			else					
				throw new ErrorFault("Phone number doesn't exist in database");
	
				
					
		}catch(Exception e){
			throw new ErrorFault("Phone number doesn't exist in database");
		}
		
	}

	/**
	 *Method that is used to insert a new row into the database
	 *@param RequestSignleContact the requested contact info to be edited
	 *@return the edited contact info
	 *@throws ErrorFault error message for when the new contact couldn't be created
	 */
	public contact.ContactResponse addContact(contact.RequestSingleContact contact)throws ErrorFault {
		
	
		try {
			
			//query that is used to insert the new row into the database using input provided by the user
			String query = "INSERT into " + contactTable + " (phonenumber, firstname, lastname, street, city, postcode)"+" values(?, ? ,? , ?, ?, ?)";		
			
			//get and stores the requested contact details so that the information given by the user can be used
			ContactDetail contactInfo = contact.getRequestSingleContact();
			ContactResponse contactResponse = new ContactResponse();
			
			//store the values sent from the client to the added to the database
			String firstName = contactInfo.getFirstname();
			String surname = contactInfo.getSurname();
			String phoneNumber = contactInfo.getPhonenumber();
			String street = contactInfo.getAddress().getStreet();
			String city = contactInfo.getAddress().getCity();
			String postcode = contactInfo.getAddress().getPostcode();
			
			
			
			//only allow the contact information to be added if all of the columns in the row contain a value if not display an error message
			if(!firstName.trim().isEmpty() && !surname.trim().isEmpty() && !street.trim().isEmpty() && !city.trim().isEmpty() && !postcode.trim().isEmpty()) {
				
				
				if(firstName.matches(validationRegex) && surname.matches(validationRegex) && city.matches(validationRegex)) {
					
					Connection connection = createDatabaseConnection();
					PreparedStatement prepareStatement = connection.prepareStatement(query);
					
					//set the value of each column for the row being created using the users input
					prepareStatement.setString(1, phoneNumber);
					prepareStatement.setString(2, firstName);
					prepareStatement.setString(3, surname);		
					prepareStatement.setString(4, street);
					prepareStatement.setString(5, city);
					prepareStatement.setString(6, postcode);
					
					
					//Execute the statement to add the contact information
					prepareStatement.execute();
					
					connection.close();
					
					//create a response message to confirm that the row was added 
					contactResponse.setContactResponse("Contact added to database");
					
					return contactResponse;
					
				}
				else
					throw new ErrorFault("Error Adding new contact first name , surname or city cannot contain numbers");
			
	
			}
			else
				throw new ErrorFault("Error Adding new contact Field cannot be empty");
			
		}catch(SQLException e)
		{
			throw new ErrorFault("Error Adding new contact invalid phone number");
			
		}
				
	}
	/**
	 *Method that is used to edit a persons contact info based on the given phone number
	 *@param RequestSignleContact the requested contact info that is to be edited
	 *@return the edited contact info
	 *@throws ErrorFault Handles error message for when the contact info couldn't be edited
	 */
	public contact.RequestSingleContact editContact(contact.RequestSingleContact contact)throws ErrorFault{
		
		try {
			ContactDetail contactInfo = contact.getRequestSingleContact();
			
			String firstName = contactInfo.getFirstname();
			String surname = contactInfo.getSurname();
			String street = contactInfo.getAddress().getStreet();
			String city = contactInfo.getAddress().getCity();
			String postcode = contactInfo.getAddress().getPostcode();
									
			//Query that is used for updating the row if the phone number given by the user matches a value in the database
			//the values of each column are set using the values that where given by the user
			String query = "UPDATE " + contactTable + " SET firstname= ?, lastname= ?, street= ?, city= ?, postcode= ?" +" WHERE phonenumber = '" + contact.getRequestSingleContact().getPhonenumber() +"'";
			
			if(!firstName.trim().isEmpty() && !surname.trim().isEmpty() && !street.trim().isEmpty() && !city.trim().isEmpty() && !postcode.trim().isEmpty()) {
				
				
				if(firstName.matches(validationRegex) && surname.matches(validationRegex) && city.matches(validationRegex)) {
					Connection connection = createDatabaseConnection();
					PreparedStatement prepareStatement = connection.prepareStatement(query);			
					
					
					//set the value of each column in the row to the value entered by the user in the client
					prepareStatement.setString(1, firstName);
					prepareStatement.setString(2, surname);		
					prepareStatement.setString(3, street);
					prepareStatement.setString(4, city);
					prepareStatement.setString(5, postcode);
					
					//stores if there was a contact found with the searched for phone number
					int foundPhoneNumber = prepareStatement.executeUpdate();

					//if no contact could be found display an error message
					if(foundPhoneNumber == 0)
					{
						throw new ErrorFault("No Matching Contact Information found with this phone number");				
					}
					else
											
					connection.close();
					return contact;
						
				}
			
				else
					throw new ErrorFault("Error Editting contact first name, surname or city cannot contain numbers");						
			}
			else
				throw new ErrorFault("Error Adding new contact Field cannot be empty");
				
		
		}catch(SQLException e)
		{
			throw new ErrorFault("Cannot Edit Selected Record");
		}
		

	}
	
	/**
	 *Method used for deleting a persons contact info from the database
	 *
	 *@return return a contact response message to confirm that the contact was removed from the database
	 *@param 
	 * @throws ErrorFault Handles error message for when the contact info couldn't be deleted
	 */
	public contact.ContactResponse deleteContact(contact.ContactPhoneNumber phoneNumber) throws ErrorFault
	{
		
		try {
			
		//SQL query that if a matching phone number is found that persons contact info will be deleted from the database
		String query ="DELETE FROM " + contactTable +" WHERE phonenumber = '" + phoneNumber.getContactPhoneNumber() + "'";	
		Connection connection = createDatabaseConnection();
		Statement statement = connection.createStatement();	
		ContactResponse contactResponse = new ContactResponse();
		
		//Executes the SQL query then stores if the statement was successful
		int removedFromDb = statement.executeUpdate(query);
		
		//if the statement was not successful display an error message
		if(removedFromDb == 0)
		{			
			throw new ErrorFault("No Matching Phone number was found");						
		}
		else
	
		//The response message to the user to confirm that the contact info was removed from the database
		contactResponse.setContactResponse("Contact information removed from database");
		
		connection.close();
		
				
		return contactResponse;
		
		}catch(SQLException e)
		{			
			throw new ErrorFault("Unable to delete contact with this phone number");
		}
	}
	
	/**
	 * Method that will set the value of each of the elements in the complex type to the value that is in the selected row
	 * @param resultSet the data that is in the database
	 * @return the contact info that the user searched for
	 * @throws Exception
	 */
	public ContactDetail getContactDetails(ResultSet resultSet) throws Exception
	{
		ContactDetail contactInfo = new ContactDetail();
		Address addressInfo = new Address();
		
		
		//set each of the values in contact info to the value of the row in the selected column
		contactInfo.setPhonenumber(resultSet.getString("phonenumber"));	
		contactInfo.setFirstname(resultSet.getString("firstname"));
		contactInfo.setSurname(resultSet.getString("lastname"));															
		addressInfo.setStreet(resultSet.getString("street"));
		addressInfo.setCity(resultSet.getString("city"));
		addressInfo.setPostcode(resultSet.getString("postcode"));
		contactInfo.setAddress(addressInfo);
		
		return contactInfo;		
	}
	
	
}
				
	

		
		
		
		
	

