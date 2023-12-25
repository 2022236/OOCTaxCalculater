/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package taxcalculationsca;

import ioutils.IOUtils;
import java.sql.SQLException;

/**
 *
 * @author lizandra 2022236 and Taciana 2022404
 */

public class OOCTaxcalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        // TODO code application logic here
        IOUtils scan = new IOUtils ();
        if (DatabaseSetup.setupDB()) {
            System.out.println("Database and table created");
        }else {
            System.out.println("Oh no! There was a database creation problem...");
        }
        DatabaseWriter dbWriter = new DatabaseWriter();
        DatabaseReader dbReader = new DatabaseReader();
        ApplicationMenu menu = new ApplicationMenu(dbWriter, dbReader);
        menu.run();   
    }
    public static class ApplicationMenu {
    private DatabaseWriter dbWriter;
    private DatabaseReader dbReader;
    private IOUtils scan;

    public ApplicationMenu(DatabaseWriter dbWriter, DatabaseReader dbReader) {
        this.dbWriter = dbWriter;
        this.dbReader = dbReader;
        this.scan = new IOUtils();
    }

    public void run() throws SQLException {
        while (true) {
            System.out.println("1. Sign Up");
            System.out.println("2. Admin Functions");
            System.out.println("3. Exit");
            int choice = scan.getUserInt("Choose an option:", 1, 3);
            switch (choice) {
                case 1:
                    signUpNewUser();
                    break;
                case 2:
                    performAdminFunctions();
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }   
    private void signUpNewUser() throws SQLException{
        String userName = scan.getUserText("Enter your username:");
        String password = scan.getUserText("Enter your password:");
        String role = "Regular";
        User newUser = new User(userName, password, "Regular");
        String email = scan.getUserText("Enter your email:");        
    boolean success = dbWriter.addUserDatabase(userName, role, email, password);
    if (success) {
        System.out.println("User registered successfully.");
    } else {
        System.out.println("Failed to register user.");
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

