package contact;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import contact.ContactServiceStub.Address;
import contact.ContactServiceStub.ContactDetail;
import contact.ContactServiceStub.ContactDetails;
import contact.ContactServiceStub.ContactPhoneNumber;
import contact.ContactServiceStub.ContactResponse;
import contact.ContactServiceStub.MultipleContacts;
import contact.ContactServiceStub.RequestCity;
import contact.ContactServiceStub.RequestSingleContact;


import javax.swing.JButton;

public class Client extends JFrame implements ActionListener {
	

	//Components that are used in the user interface
	private JFrame addContactPane = new JFrame();
	
	private JFrame editContactPane = new JFrame();
		
	private JLabel firstNameLabel = new  JLabel("First Name :");
	private JLabel surnameNameLabel = new  JLabel("Surname Name :");
	private JLabel addPhoneNumberLabel = new  JLabel("Phone Number :");
	private JLabel streetNameLabel = new  JLabel("Street Name :");
	private JLabel cityNameLabel = new  JLabel("City Name :");
	private JLabel postCodeLabel = new  JLabel("PostCode :");
	
	private JTextField firstNameTxt = new JTextField();
	private JTextField surnameTxt = new JTextField(); 
	private JTextField addPhoneNumberTxt = new JTextField();
	private JTextField streetNameTxt = new JTextField();
	private JTextField cityNameTxt = new JTextField(); 
	private JTextField postCodeTxt = new JTextField();
	
	
	private JLabel phoneNumberUpdateLabel = new  JLabel("<html>Phone Number <br/> to Update :</html>");
	private JLabel firstNameUpdateLabel = new  JLabel("First Name :");
	private JLabel surnameNameUpdateLabel = new  JLabel("Surname Name :");
	private JLabel streetNameUpdateLabel = new  JLabel("Street Name :");
	private JLabel cityNameUpdateLabel = new  JLabel("City Name :");
	private JLabel postCodeUpdateLabel = new  JLabel("PostCode :");
	
	
	private JTextField phoneNumberToUpdateTxt = new JTextField();
	private JTextField firstNameUpdateTxt = new JTextField();
	private JTextField surnameUpdateTxt = new JTextField(); 
	private JTextField streetNameUpdateTxt = new JTextField();
	private JTextField cityNameUpdateTxt = new JTextField(); 
	private JTextField postCodeUpdateTxt = new JTextField(); 

	private JButton addContactButton = new JButton("Create Contact");
	private JButton cancelAddContactButton = new JButton("Cancel");
	private JButton cancelEditButton = new JButton("Cancel");

	private JFrame contentPane = new JFrame();
	private JButton getPhoneNumberButton = new JButton("Search Number");
	

	private JLabel enterInfoLabel = new JLabel("Enter Value :");
	private JTextField enterInfoTxt = new JTextField();

	

	private JButton createNewContact = new JButton("Add Contact");
	private JButton editContactButton = new JButton("Edit Contact");
	
	private JButton deleteContactButton = new JButton("Delete Contact");
	
	private JButton clearTextAreaButton = new JButton("Clear");
	
	private JButton editContactInfoButton = new JButton("Edit contact");
	
	
	private JButton searchCityButton = new JButton("Search city");
	
	private JTextArea contactInfoArea = new JTextArea();

	
	//Variables used to receive and send requests to the service
	private ContactServiceStub stub = new ContactServiceStub();
	
	private ContactPhoneNumber phoneNumber = new ContactPhoneNumber();
	
	private RequestSingleContact contactRequest = new RequestSingleContact();
	
	private MultipleContacts multiContactRequest = new MultipleContacts();
	
	private ContactResponse contactResponse = new ContactResponse();
	
	private RequestCity requestCity = new RequestCity();
	
	private ContactDetail contactDetail = new ContactDetail();
	
	private ContactDetails contactDetails = new ContactDetails();

	
	private String validationRegex  = "[A-Za-z]";
	
	private Address addressDetail = new Address();
	

	/**
	 * Creates the user interface to allow the user to interact with the web service
	 * @throws Exception
	 */
	public Client() throws Exception {
		
		
		createMainPage();
		createAddContactPage();
		editContactPage();
		
	}
	
	/**
	 *Sets the properties for all of the components that are used to display the main content display 
	 */
	public void createMainPage()
	{
		contentPane.getContentPane().setLayout(null);
		contentPane.setDefaultCloseOperation(EXIT_ON_CLOSE);
		contentPane.setSize(500,400);
				

		enterInfoLabel.setBounds(8, 70, 110, 10);
		contentPane.add(enterInfoLabel);
		
		enterInfoTxt.setBounds(100,70,140,20);
		contentPane.add(enterInfoTxt);
		
		getPhoneNumberButton.setBounds(240,70,120,20);
		getPhoneNumberButton.addActionListener(this);
		contentPane.add(getPhoneNumberButton);
		
		
		searchCityButton.setBounds(360,70,120,20);
		searchCityButton.addActionListener(this);
		contentPane.add(searchCityButton);
		
		editContactButton.setBounds(240,45,120,20);
		editContactButton.addActionListener(this);
		contentPane.add(editContactButton);
		
		deleteContactButton.setBounds(360,45,120,20);
		deleteContactButton.addActionListener(this);
		contentPane.add(deleteContactButton);
		
		
		clearTextAreaButton.setBounds(8,120,120,20);
		clearTextAreaButton.addActionListener(this);
		contentPane.add(clearTextAreaButton);
		
	
		createNewContact.setBounds(240,20,120,20);
		createNewContact.addActionListener(this);
		contentPane.add(createNewContact);
		
		
		contactInfoArea.setBounds(8, 140, 472, 200);
		contactInfoArea.setFocusable(false);
		contentPane.add(contactInfoArea);
	}
	
	
	/**
	 *Sets the properties for all of the components that are used to display the add new contact display 
	 */
	public void createAddContactPage() {
		
		addContactPane.getContentPane().setLayout(null);
		addContactPane.setSize(500,400);
		
		firstNameLabel.setBounds(8, 20, 100, 10);
		addContactPane.add(firstNameLabel);
		
		firstNameTxt.setBounds(110, 20, 120, 20);
		addContactPane.add(firstNameTxt);
		
		surnameNameLabel.setBounds(8, 50, 100, 10);
		addContactPane.add(surnameNameLabel);
		
		surnameTxt.setBounds(110, 50, 120, 20);
		addContactPane.add(surnameTxt);
		
		
		addPhoneNumberLabel.setBounds(8, 80, 100, 10);
		addContactPane.add(addPhoneNumberLabel);
		
		addPhoneNumberTxt.setBounds(110, 80, 120, 20);
		addContactPane.add(addPhoneNumberTxt);
		
		
		streetNameLabel.setBounds(8, 110, 100, 10);
		addContactPane.add(streetNameLabel);
		
		streetNameTxt.setBounds(110, 110, 120, 20);
		addContactPane.add(streetNameTxt);
		
		
		cityNameLabel .setBounds(8, 140, 100, 10);
		addContactPane.add(cityNameLabel );
		
		cityNameTxt.setBounds(110, 140, 120, 20);
		addContactPane.add(cityNameTxt);
		
		
		postCodeLabel.setBounds(8, 170, 100, 10);
		addContactPane.add(postCodeLabel);
		
		postCodeTxt.setBounds(110, 170, 120, 20);
		addContactPane.add(postCodeTxt);
		
		addContactButton.setBounds(8, 200, 120, 20);
		addContactButton.addActionListener(this);
		addContactPane.add(addContactButton);
		
		cancelAddContactButton.setBounds(130, 200, 120, 20);
		cancelAddContactButton.addActionListener(this);
		addContactPane.add(cancelAddContactButton);
			
	}
	
	
	/**
	 *Sets the properties for all of the components that are used to display the edit contact page  
	 */
	public void editContactPage()
	{
		editContactPane.getContentPane().setLayout(null);
		
		editContactPane.setSize(500,400);
		
		
		phoneNumberUpdateLabel.setBounds(8, 10, 120, 30);
		editContactPane.add(phoneNumberUpdateLabel);
		
		phoneNumberToUpdateTxt.setBounds(110, 20, 120, 20);
		editContactPane.add(phoneNumberToUpdateTxt);
				
		firstNameUpdateLabel.setBounds(8, 50, 100, 10);
		editContactPane.add(firstNameUpdateLabel);
		
		firstNameUpdateTxt.setBounds(110, 50, 120, 20);
		editContactPane.add(firstNameUpdateTxt);
		
		surnameNameUpdateLabel.setBounds(8, 80, 100, 10);
		editContactPane.add(surnameNameUpdateLabel);
		
		surnameUpdateTxt.setBounds(110, 80, 120, 20);
		editContactPane.add(surnameUpdateTxt);
		

		streetNameUpdateLabel.setBounds(8, 110, 100, 10);
		editContactPane.add(streetNameUpdateLabel);
		
		streetNameUpdateTxt.setBounds(110, 110, 120, 20);
		editContactPane.add(streetNameUpdateTxt);
		
		
		cityNameUpdateLabel.setBounds(8, 140, 100, 10);
		editContactPane.add(cityNameUpdateLabel );
		
		cityNameUpdateTxt.setBounds(110, 140, 120, 20);
		editContactPane.add(cityNameUpdateTxt);
		
		
		postCodeUpdateLabel.setBounds(8, 170, 100, 10);
		editContactPane.add(postCodeUpdateLabel);
		
		postCodeUpdateTxt.setBounds(110, 170, 120, 20);
		editContactPane.add(postCodeUpdateTxt);
		
		editContactInfoButton.setBounds(8, 200, 120, 20);
		editContactInfoButton.addActionListener(this);
		editContactPane.add(editContactInfoButton);
		
		
		cancelEditButton.setBounds(130, 200, 120, 20);
		cancelEditButton.addActionListener(this);
		editContactPane.add(cancelEditButton);
		
		
	}

	public static void main(String[] args) throws Exception {
		
		Client userInterface = new Client();
		
			
		userInterface.contentPane.setVisible(true);
	
		userInterface.contentPane.setTitle("Contact Info Client");	
		userInterface.addContactPane.setTitle("Create New Contact");	
		
		userInterface.editContactPane.setTitle("Edit Contact");
		
		//sets the position of the Jframes to the centre of the screen
		userInterface.contentPane.setLocationRelativeTo(null);
		userInterface.addContactPane.setLocationRelativeTo(null);
		userInterface.editContactPane.setLocationRelativeTo(null);

	}


	@Override
	public void actionPerformed(ActionEvent e) {

		
	
			if(e.getSource() == deleteContactButton)
			{
				
				deleteContactInfo();
		
			
			}
			if(e.getSource() == createNewContact)
			{
			
				addContactPane.setVisible(true);
				firstNameTxt.setText("");
				surnameTxt.setText("");
				addPhoneNumberTxt.setText("");
				streetNameTxt.setText("");
				cityNameTxt.setText("");
				postCodeTxt.setText("");
				
				
				

			}
			
			if(e.getSource() == editContactButton)
			{
				
				editContactPane.setVisible(true);
				phoneNumberToUpdateTxt.setText("");
				firstNameUpdateTxt.setText("");
				surnameUpdateTxt.setText("");			
				streetNameUpdateTxt.setText("");
				cityNameUpdateTxt.setText("");
				postCodeUpdateTxt.setText("");
				
			}
			if(e.getSource()== editContactInfoButton)
			{
				
				editContactInfo();
			
			}
			
			if(e.getSource() == clearTextAreaButton)
			{
				enterInfoTxt.setText("");
				contactInfoArea.setText(null);
			}
		
			
			if(e.getSource() == addContactButton)
			{
				
				addContactInfo();
				
			}
			
			if(e.getSource() == cancelAddContactButton)
			{
				
				
				firstNameTxt.setText("");
				surnameTxt.setText("");
				addPhoneNumberTxt.setText("");
				streetNameTxt.setText("");
				cityNameTxt.setText("");
				postCodeTxt.setText("");
				
				addContactPane.setVisible(false);
			}
			
			if(e.getSource() == cancelEditButton)
			{
				
				phoneNumberToUpdateTxt.setText("");
				firstNameUpdateTxt.setText("");
				surnameUpdateTxt.setText("");			
				streetNameUpdateTxt.setText("");
				cityNameUpdateTxt.setText("");
				postCodeUpdateTxt.setText("");
				
				editContactPane.setVisible(false);
				
			}
		
			
			if(e.getSource() == getPhoneNumberButton) {
						
				getContactInfoByPhoneNumber();
				
				
			}
			
			if(e.getSource() == searchCityButton)
			{
			
				getContactInfoByCity();
			
			}
		}	
	/**
	 *Method that will get and display each of the contact details that match the requested city 
	 */
	public void getContactInfoByCity()
	{
		try {
			String city = enterInfoTxt.getText();
			
			String currentRow ="";
			
			//set the value of the requested city to the value given by the user
			requestCity.setRequestCity(city);
			
			//send a request to the service to get the requested city
			multiContactRequest = stub.getByCity(requestCity);
	
			
			contactDetails = multiContactRequest.getMultipleContacts();
			
			//Store the returned contact details in an array
			ContactDetail[] contactList = contactDetails.getListOfContacts();
			
		
			//for loop that will iterate through the contactList to display each row of contact information in the text area
			for(int i = 0; i < contactList.length; i++)
			{
				contactDetail = contactList[i];

				currentRow += formatContactInfo(contactDetail);
			}
		
			contactInfoArea.setText(currentRow);
			}
			catch(Exception ex)
			{		
				
				String errorMessage = ex.getMessage();
				showError(contentPane,errorMessage,"Error Message",JOptionPane.ERROR_MESSAGE);
			}
			
		}
	
	/**
	 *Method that will return a persons contact information using their phone number  
	 */
	public void getContactInfoByPhoneNumber()
	{
		try {
			String enteredPhoneNumber = enterInfoTxt.getText();
			
			//set the value of the phone number that is to be sent to the service
			phoneNumber.setContactPhoneNumber(enteredPhoneNumber);
			
			//gets the requested contact information using the phone number that was set
			contactRequest = stub.getByPhoneNumber(phoneNumber);
		
			//gets and stores the requested value if the phone number exists in the database
			contactDetail = contactRequest.getRequestSingleContact();
			
			
			String contactReponseResult = formatContactInfo(contactDetail);
			
			contactInfoArea.setText(contactReponseResult);
			
			}catch(Exception ex)
			{
				String errorMessage = ex.getMessage();
				
				showError(contentPane,errorMessage,"Error Message",JOptionPane.ERROR_MESSAGE);
			}
	}
	
	/**
	 *Method that handles adding a new contact to the database using the users input
	 */
	public void addContactInfo()
	{
		try {
		
		
			int input = JOptionPane.showConfirmDialog(addContactPane,"Are you sure you want to add this contact","Add Contact",JOptionPane.YES_NO_OPTION);
			
		
			//only perform the action if the user has selected yes in the dialog box
			if(input == 0) {
				
				String firstName  = firstNameTxt.getText();
				String surname = surnameTxt.getText();
				String phonenumber = addPhoneNumberTxt.getText();
				String street = streetNameTxt.getText();
				String city = cityNameTxt.getText();
				String postcode = postCodeTxt.getText();
				
			
			    //sets the value of each element in the contact detail complex type to the values provided by the user
				contactDetail.setFirstname(firstName);
				contactDetail.setSurname(surname);
				contactDetail.setPhonenumber(phonenumber);
				addressDetail.setStreet(street);
				addressDetail.setCity(city);
				addressDetail.setPostcode(postcode);
				contactDetail.setAddress(addressDetail);
					
			
				//set the request for the contact details to the values given by the user
				contactRequest.setRequestSingleContact(contactDetail);
				
				//send a request to the service to add new contact details and return the response
				contactResponse = stub.addContact(contactRequest);
				
				//Format and store the created contact so that it can be displayed to the user
				String result = "Person Info for id: \n First Name: " + contactDetail.getFirstname() + "\n Surname: " + contactDetail.getSurname() + "\n Phone Number: " + contactDetail.getPhonenumber() +  "\n Address info:"
						+ "\n City: "+ contactDetail.getAddress().getCity() + "\n Street: " + contactDetail.getAddress().getStreet() + "\n Postcode: "+ contactDetail.getAddress().getPostcode();
				
				JOptionPane.showMessageDialog(addContactPane,result,"Contact Info Updated",JOptionPane.PLAIN_MESSAGE);
				
				addContactPane.dispose();
										
			}
			
			}catch(Exception ex) {
				
				String errorMessage = ex.getMessage();
				showError(addContactPane,errorMessage,"Error Message",JOptionPane.ERROR_MESSAGE);
				
			}
	}
	
	
	
	public void deleteContactInfo()
	{
		try {
				
			int input = JOptionPane.showConfirmDialog(contentPane,"Are you sure you want to delete this contact","Delete Contact",JOptionPane.YES_NO_OPTION);
			
			//only perform the action if the user has selected yes in the dialog box
			if(input == 0) {
			
			String enteredPhoneNumber = enterInfoTxt.getText();
			
			//Handle sending the phone number that the user entered to the client
			phoneNumber.setContactPhoneNumber(enteredPhoneNumber);
			
			//The response from the service from deleting the contact info
			contactResponse = stub.deleteContact(phoneNumber);
			
			JOptionPane.showMessageDialog(addContactPane,"Contact Information Deleted","Contact Deleted",JOptionPane.PLAIN_MESSAGE);
			}
			}
			catch(Exception ex)
			{
				
				String errorMessage = ex.getMessage();
				JOptionPane.showMessageDialog(contentPane,errorMessage,"Error Deleting Contact",JOptionPane.ERROR_MESSAGE);
				
			}
	}
	
	
	/**
	 *method that is used for the user to enter then validate the contact information that they want to update  
	 */
	public void editContactInfo()
	{
		try {

			int input = JOptionPane.showConfirmDialog(editContactPane,"are you sure you want to update this contact","Update Contact Info",JOptionPane.YES_NO_OPTION);
			
			//only perform the action if the user has selected yes in the dialog box
			if(input == 0) {
			
		
			//sets the values for each value that is to be updated using the values given by the user
			contactDetail.setFirstname(firstNameUpdateTxt.getText());
			contactDetail.setSurname(surnameUpdateTxt.getText());
			contactDetail.setPhonenumber(phoneNumberToUpdateTxt.getText());
			addressDetail.setStreet(streetNameUpdateTxt.getText());
			addressDetail.setCity(cityNameUpdateTxt.getText());
			addressDetail.setPostcode(postCodeUpdateTxt.getText());
			contactDetail.setAddress(addressDetail);
			
			//set the value of the contact information that is being sent to the service
			contactRequest.setRequestSingleContact(contactDetail);
				
			//perform the update of the information and store the result 
			contactRequest = stub.editContact(contactRequest);
			
	
			//Displays the updated information to the user
			String result = "Person Info for id:" + contactDetail.getPhonenumber() +  " \n First Name: " + contactDetail.getFirstname() + "\n Surname: " + contactDetail.getSurname() + "\n Address info:"
					+ "\n City: "	+ contactDetail.getAddress().getCity() + "\n Street: " + contactDetail.getAddress().getStreet() + "\n Postcode: "+ contactDetail.getAddress().getPostcode() ;
			
			JOptionPane.showMessageDialog(editContactPane,result,"Contact Info Updated",JOptionPane.PLAIN_MESSAGE);
			
			editContactPane.dispose();
			}
			
			
			}
			catch(Exception ex)
			{
				
				String errorMessage = ex.getMessage();
				showError(editContactPane,errorMessage,"Error Message",JOptionPane.ERROR_MESSAGE);
				
				
			}
	}
	
	
	/**
	 * method that will create a pop up message to provide feedback to the user when they perform an action
	 * @param pane the frame that the message should be displayed to
	 * @param description a short description of the error message
	 * @param title the name of the error message
	 * @param errorType the type of warning that is showed to the user
	 */
	private void showError(JFrame pane,String description,String title,int errorType)
	{
		JOptionPane.showMessageDialog(pane,description,title,errorType);
	}
	
	/**
	 * Format the contact info so it can be displayed in the text area
	 * @param contactDetail The details that have been passed from the client
	 * @return The formatted contact information
	 */
	 private String formatContactInfo(ContactDetail contactDetail)
	{
	
		
		String contactReponseResult = "Person Info : " + contactDetail.getFirstname() + ", " + contactDetail.getSurname() +", " + contactDetail.getPhonenumber() +" Address info :"
				+ contactDetail.getAddress().getCity() + ", " + contactDetail.getAddress().getStreet() +", " + contactDetail.getAddress().getPostcode() +"\n";
		
		return contactReponseResult;


	}
	 
}
