/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ca2calculationtax;

/**
 *
 * @author lizam
 */
public class CA2CalculationTax {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IOUtils scan = new IOUtils();
       
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

        private void signUpNewUser() throws SQLException {
            
            //crypt newEncreypeter = new crypt();
            Key newUserKey = crypt.gerarChave();
            
            String firstName = scan.getUserText("Enter your firstName:");
            String lastName = scan.getUserText("Enter your lasName:");
            String userName = scan.getUserText("Enter your username:");
            String password = scan.getUserText("Enter your password:");
            String position = scan.getUserText("Enter your position:");
            Date userData = scan.getUserData("Enter your birthDate:");
            String userEmail = scan.getUserText("Enter your E-mail:");
            String passWordOpen = scan.getUserText("Enter your Password:");
            byte[] passWordClose = crypt.criptografar(newUserKey ,passWordOpen);
            
            
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

        private void performAdminFunctions() {
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
    
}
