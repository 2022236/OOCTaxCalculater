/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package OOCTaxCalculater;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author lizandra 2022236 and Taciana 2022404
 */

public class OOCTaxCalculater {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
       // Creating an instance of IOUtils for input/output operations
        IOUtils scan = new IOUtils();

         // Creating instances of DatabaseWriter and DatabaseReader classes
        DatabaseWriter dbWriter = new DatabaseWriter();
        DatabaseReader dbReader = new DatabaseReader();
        ApplicationMenu menu = new ApplicationMenu(dbWriter, dbReader);  // Creating an instance of ApplicationMenu and passing DatabaseWriter and DatabaseReader instances to it
        menu.run(); // Running the application through the menu instance
    }

/**
 * Static nested class representing the ApplicationMenu.
 * Contains private fields to store instances of DatabaseWriter, DatabaseReader, and IOUtils.
 */
    public static class ApplicationMenu {
        // Declaring private fields to store instances of DatabaseWriter, DatabaseReader, and IOUtils
        private DatabaseWriter dbWriter;
        private DatabaseReader dbReader;
        private IOUtils scan;
/**
 * Constructor for the ApplicationMenu class.
 * Initializes the database writer, reader, and input/output utility for the application menu.
 * @param dbWriter DatabaseWriter instance to handle writing to the database.
 * @param dbReader DatabaseReader instance to handle reading from the database.
 */
    public ApplicationMenu(DatabaseWriter dbWriter, DatabaseReader dbReader) {
            this.dbWriter = dbWriter; // Assigning the provided DatabaseWriter instance to the class field dbWriter
            this.dbReader = dbReader; // Assigning the provided DatabaseReader instance to the class field dbReader
            this.scan = new IOUtils(); // Creating a new instance of IOUtils and assigning it to the class field scan
        }

    public void run() throws SQLException {
            User loggedInUser = null;
            while (true) {
                // Displaying menu options
                System.out.println("1. Sign Up");
                System.out.println("2. Sign In");
                System.out.println("3. Exit");
                int choice = scan.getUserInt("Choose an option:", 1, 3); // Getting user choice within the specified range
               // Handling user actions based on their login status and role
                if (loggedInUser == null) {
                    switch (choice) {
                        case 1:
                            signUpNewUser(); // Signing up a new user
                            break;
                        case 2:
                            loggedInUser = signInUser(); // Signing in a user
                            break;
                        case 3:
                            System.exit(0); // Exiting the application
                            break;
                    }
                } else if (loggedInUser.getTypeUser().equals(commonVariables.Type.Regular)) {
                    regularFunctionsUser(loggedInUser); // Invoking regular user functions
                } else if (loggedInUser.getTypeUser().equals(commonVariables.Type.Admin)) {
                    adminFunctionsUser(loggedInUser); // Invoking admin user functions
                } else {
                    throw new AssertionError();  // Handling unexpected conditions
                }
            }
        }

    private void signUpNewUser() throws SQLException {
            // Getting user information
            String userName = scan.getUserText("Enter your username:");
            String firstName = scan.getUserText("Enter your firstName:");
            String lastName = scan.getUserText("Enter your lasName:");
            String position = scan.getUserText("Enter your position:");
            Date userBirthDate = scan.getUserData("Enter your birthDate:");
            String userEmail = scan.getUserText("Enter your E-mail:");
            String password = scan.getUserText("Enter your password:");
            commonVariables.Type userType = commonVariables.Type.Regular; // Setting user type as Regular by default
            int userMarital = scan.getUserInt("Enter yout marital status. 1 for Single, 2 for Married and 3 for widowed", 1, 3); // Getting user's marital status
            commonVariables.MaritalStatus userMaritalStatus; 

             // Converting user input into MaritalStatus enum based on the selected option
            switch (userMarital) {
                case 1:
                    userMaritalStatus = commonVariables.MaritalStatus.Single;
                    break;
                case 2:
                    userMaritalStatus = commonVariables.MaritalStatus.Married;
                    break;
                case 3:
                    userMaritalStatus = commonVariables.MaritalStatus.widowed;

                    break;
                default:
                    throw new AssertionError();
            }
            // Getting financial details of the user
            float userGrossPayment = (float) scan.getUserDecimal("Enter your payment");

            String userContry = scan.getUserText("Enter your contry");
            String userState = scan.getUserText("Enter your State");
            String userCity = scan.getUserText("Enter user city.");
            int userZIPCode = scan.getUserInt("Enter your ZIP code");
            String userStreetName = scan.getUserText("Enter your Street Name");
            int userNumber = scan.getUserInt("Enter your adress number.");

            boolean success = dbWriter.addUserDatabase(userName, firstName, lastName,
                    position, userBirthDate, userEmail, password, userType,
                    userMaritalStatus, userGrossPayment, 0, 0, 0, 0, userContry, userState,
                    userCity, userZIPCode, userStreetName, userNumber);
            if (success) {
                System.out.println("User registered successfully.");
            } else {
                System.out.println("Failed to register user.");
            }
        }

// This method facilitates the user sign-in process by collecting the username and password inputs.
// It retrieves the user information from the database by using the provided username and password.
// Returns the user object if successfully signed in.
private User signInUser() throws SQLException {
            // Requesting the user to input their username and password
            String userName = scan.getUserText("Enter your user name.");
            String userPassWord = scan.getUserText("Enter your passWord");
            // Fetching the user information from the database based on the entered credentials
            User loggedInUser = dbReader.getUser(userName, userPassWord);

            return loggedInUser; // Returns the logged-in user object
        }

    // This method handles various functions for a logged-in user based on user input.    
    private void regularFunctionsUser(User loggedInUser) throws SQLException {
           
            // Displaying a menu of options for the user    
            System.out.println("1. View My data.");
            System.out.println("2. Edit my data.");
            System.out.println("3. Calculate tax");
            System.out.println("4. Exit");
             // Getting user choice input
            int regularChoice = scan.getUserInt("Choose an option:", 1, 4);
            boolean sucess;
            // Executing different functionalities based on user choice
            switch (regularChoice) {
                case 1:
                    System.out.println(loggedInUser.getAllData()); // Viewing user data using the loggedInUser object
                    break;
                case 2:
                    // Modifying user data and displaying success/error messages
                    sucess = modifyUser(loggedInUser);
                    if (sucess) {
                        System.out.println("Update Sucefull");
                    } else {
                        System.out.println("An error ocurred. Try again");
                    }
                    break;
                case 3:
                     // Performing tax calculations for the logged-in user
                    loggedInUser.IcommingTaxCalculator();
                    loggedInUser.USCTaxCalculator();
                    loggedInUser.PRSCI();
                    loggedInUser.NetPaymentCalculator();
                    System.out.println("Calculate completed. Selec view my data option to see everything.");
                    break;
                default:
                    throw new AssertionError(); // Handling unexpected user choices
            }
        }
 private void performAdminFunctions(){
        String userName = scan.getUserText("Enter your username:");
        String password = scan.getUserText("Enter your password:");
        User user = dbReader.getUser(userName, password);        
        if (user != null && user.getRole().equals("Admin")) {
        // Here, implement the admin functionalities
        System.out.println("Access to admin functions granted.");
    } else {
        System.out.println("Access Denied: You are not an admin or invalid credentials.");
    }      
    }
    }
}

