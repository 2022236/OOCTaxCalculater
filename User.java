/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOCTaxCalculater;

import java.util.Date;

/**
 *Add User Class Constructor, Getters, Setters, and Data Retrieval Methods
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */


public class User extends TaxPayer{
    //variables
    private String userName;
    private String firstName;
    private String lastName;
    private String password;
    private String position;
    private Date birthDate;
    private String email;
    public  String userAddress;
    private int PPS_user;
    
    
    private commonVariables.Type typeUser;
    private commonVariables.MaritalStatus userMaritalStatus;
    
    // Creating a constructor where gets all the attributes even from the another class to get the Marital Status
    public User(String userName, String firstName, String lastName, 
            String password, Date bithDate, String position, 
            commonVariables.Type userType, String email, 
            commonVariables.MaritalStatus userMaritalStatus, float GrossPayment, 
            String retrievedCountry,String retrievedState, String retrievedCity, int retrievedZIPCode,
            String retrievedStreetName, int retrievedStreetNumber, int PPS_user) {
        
        super(GrossPayment);
        // making sure to set the right value with the value
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthDate = bithDate;
        this.email = email;
        this.password = password;
        this.typeUser = userType;
        this.userMaritalStatus = userMaritalStatus;
        this.userAddress  = new Address(retrievedCountry,retrievedState, retrievedCity, 
                retrievedZIPCode, retrievedStreetName,retrievedStreetNumber);
        this.PPS_user = PPS_user;

    }
    // Another method adding new parameters such as USC, PRSCI, IncomingTax and NetPayment
    public User(String userName, 
            String firstName,String lastName, String password, Date bithDate,
            String position,commonVariables.Type userType, 
            String email,commonVariables.MaritalStatus userMaritalStatus, 
            float GrossPayment, float NetPayment, 
            float USC, float PRSCI, float IncommingTax, String retrievedCountry,
            String retrievedState, String retrievedCity, int retrievedZIPCode,
            String retrievedStreetName, int retrievedStreetNumber, int PPS_user) {
        // now the method with other parameters where add more taxes
        super(GrossPayment, NetPayment, USC, PRSCI, IncommingTax);
        
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthDate  = bithDate;
        this.email = email;
        this.password = password;
        this.typeUser = userType;
        this.userMaritalStatus = userMaritalStatus;
        this.userAddress  = new Adress(retrievedCountry,retrievedState, retrievedCity, 
                retrievedZIPCode, retrievedStreetName,retrievedStreetNumber);
        this.PPS_user = PPS_user;
    }
    
    public User(String userName, String firstName, String lastName,
            String password, Date birthDate, String position,
            commonVariables.Type userType, String email,
            commonVariables.MaritalStatus userMaritalStatus, String retrievedCountry,
            String retrievedState, String retrievedCity, int retrievedZIPCode,
            String retrievedStreetName, int retrievedStreetNumber, int PPS_user) {
        
        // Assign the parameter to the user class
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.birthDate = birthDate;
        this.email = email;
        this.password = password;
        this.typeUser = userType;
        this.userMaritalStatus = userMaritalStatus;
        this.userAddress  = new Address(retrievedCountry,retrievedState, retrievedCity, 
                retrievedZIPCode, retrievedStreetName,retrievedStreetNumber);
        this.PPS_user = PPS_user;
    }


    // Getters and Setters for User Class
    // These methods provide controlled access to the private members of the User class.
    // Getters return the current value of the class member, while setters update the value of these members.
    public String getUserName() {
        return this.userName;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getEmail() {
        return email;
    }
    
    public int getPPS_user() {
        return this.PPS_user;
    }

    public commonVariables.Type getTypeUser() {
        return typeUser;
    }

    public commonVariables.MaritalStatus getUserMaritalStatus() {
        return userMaritalStatus;
    }
    
    public float IncommingTaxCalculator() {
        return this._IncommingTaxCalculator(this.getPosition());
    }
    
    public float NetPaymentCalculator() {
        return this._NetPaymentCalculator(this.getPosition());
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirthDate(Date bithDate) {
        this.birthDate = bithDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTypeUser(commonVariables.Type typeUser) {
        this.typeUser = typeUser;
    }

    public void setUserMaritalStatus(commonVariables.MaritalStatus userMaritalStatus) {
        this.userMaritalStatus = userMaritalStatus;
    }
    
    public String getAllData() {
        // Determine user type as a string (Regular or Admin)
        String typeUser = this.getTypeUser().equals(commonVariables.Type.Regular) ? "Regular" : "Admin";
        // Retrieve and convert the user's marital status to a string
        commonVariables.MaritalStatus maritalUser = this.getUserMaritalStatus();
        String maritalUserString;
        String additionalInformation = "";
        switch (maritalUser) {
            case Single:
                maritalUserString = "Single";
                break;
            case Married:
                maritalUserString = "Married";
                break;
            case widowed:
                maritalUserString = "Widowed";
                break;
            default:
                throw new AssertionError(); // Throws an error if marital status is not recognized
        }
        // This is for Regular users, append financial details to additionalInformation
        if(this.typeUser.equals(commonVariables.Type.Regular)) {
            additionalInformation += "Gross Payment: " + this.getGrossPayment() + "\n" +
                                     "Net Payment: " + this.getNetPayment() + "\n" +
                                     "USC: " + this.getNetPayment() + "\n" +
                                     "PRSCI: " + this.getPRSCI() + "\n" + 
                                     "Incomming Tax: " + this.IncommingTaxCalculator() + "\n";
        }
        // Now will add completetly the address to additionalInformation
        additionalInformation += this.userAdress.getCompleteAdress();
        
        // Return all the information into one String and show in a text
        return "User Name: " + this.getUserName() + "\n" + 
               "First Name: " + this.getFirstName() + "\n" + 
               "Last Name: "  + this.getLastName() + "\n" + 
               "Position: " + this.getPosition() + "\n" + 
               "BirthDate: " + this.getBirthDate() + "\n" + 
               "Email: " + this.getEmail() + "\n" + 
               "Type: " + typeUser + "\n" + 
               "Marital Status: " + maritalUserString + "\n" + additionalInformation;
    }

    
}
