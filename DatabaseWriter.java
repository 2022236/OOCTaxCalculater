package OOCTaxCalculater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import jdk.jpackage.internal.IOUtils;
import java.util.Date;

/**
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */

// This class extends the 'Database' class and handles database writing operations.
public class DatabaseWriter extends Database {

     // Method to add a user to the database
    public boolean addUserDatabase(String userName, String firstName, String lastName, String position,
            Date birthDate, String email, String password, commonVariables.Type type,
            commonVariables.MaritalStatus maritalStatus, float GrossPayment, float NetPayment,
            float USC, float PRSCI, float IcommingTax, String country, String state,
            String city, int ZIPCode, String streetName, int streetNumber) throws SQLException {

        
    
        IOUtils scan = new IOUtils();
        try (
                 Connection conn = DriverManager.getConnection(db_URL, USER, PASSWORD);  Statement stmt = conn.createStatement();) {
            // 1. Insert data into the 'User' table
            String sqlUser = String.format("INSERT INTO %s (userName, FirstName, LastName, Position, BirthDate, Email, PassWord, Type, MaritalStatus) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');",
                    tableName, userName, firstName, lastName, position, birthDate, email, password, type, maritalStatus);

            stmt.execute(sqlUser);

           // 2. Retrieve the automatically generated primary key (User_PPS)
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            int userPPS = -1;

            if (generatedKeys.next()) {
                userPPS = generatedKeys.getInt(1);

                // 3. Insert data into the 'financialData' table
                String sqlFinacialData = String.format("INSERT INTO finacialData (GrossPayment, NetPayment, USC, PRSCI, IcommingTax, IsEmployer, User_PPS) VALUES (%f, %f, %f, %f, %f, %d, %d);",
                        GrossPayment, NetPayment, USC, PRSCI, IcommingTax, 1, userPPS);

                stmt.execute(sqlFinacialData);

                
                // 4. Insert data into the 'Address' table
                String sqlAddress = String.format("INSERT INTO Address (Country, State, City, ZIPCode, StreetName, StreetNumber, User_PPS) VALUES ('%s', '%s', '%s', %d, '%s', %d, %d);",
                        country, state, city, ZIPCode, streetName, streetNumber, userPPS);

                stmt.execute(sqlAddress);
            } else {
                // Deals with the failure when trying to get the key
                throw new SQLException("Something went wrong when generating key." + (User_PPS));
            }

            return true; // Returns true if the user is successfully added to the database
        } catch (Exception e) {
            e.printStackTrace();
            return false; //Returns false if there's an exception while adding the user
        }
    }

        // Method to remove a user from the database
        public boolean removeUser(String userName) {
            try (
                     Connection conn = DriverManager.getConnection(db_URL, USER, PASSWORD);  Statement stmt = conn.createStatement();) {
                // Removes a user from the 'User' table based on the username
                String deleteUserSql = String.format("DELETE FROM %s WHERE UserName='%s';", tableName, userName);
                int rowsAffected = stmt.executeUpdate(deleteUserSql);

                if (rowsAffected > 0) {
                    System.out.println("User removed successfully!");
                    return true; // Returns true if the user is successfully removed from the database
                } else {
                    System.out.println("User not found.");
                    return false;
                }
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
        
        public boolean updateUser(User modifiedUser) throws SQLException {
        try ( // Establishing a connection to the database using the DriverManager
                Connection conn = DriverManager.getConnection(db_URL, USER, PASSWORD);
                Statement stmt = conn.createStatement();
        ) {
            int userPPS = modifiedUser.getPPS_user();  // supondo que User tenha um método para obter PPS

             // 1. Update data in the 'User' table
             // Constructing SQL statement to update user information in the 'User' table
            String updateUserSql = String.format("UPDATE %s SET userName='%s', FisrtName='%s', LastName='%s', Position='%s', BirthDate='%s', Email='%s', PassWord='%s', Type='%s', MaritalStatus='%s' WHERE PPS=%d;",
                    tableName, modifiedUser.getUserName(), modifiedUser.getFirstName(), modifiedUser.getLastName(), modifiedUser.getPosition(), modifiedUser.getBirthDate(), modifiedUser.getEmail(), modifiedUser.getPassword(), modifiedUser.getTypeUser(), modifiedUser.getUserMaritalStatus(), userPPS);

            stmt.executeUpdate(updateUserSql);

            // 2. Update data in the 'financialData' table
            // Constructing SQL statement to update financial data in the 'financialData' table
            String updateFinancialDataSql = String.format("UPDATE finacialData SET GrossPayment=%f, NetPayment=%f, USC=%f, PRSCI=%f, IcommingTax=%f WHERE User_PPS=%d;",
                    modifiedUser.getGrossPayment(), modifiedUser.getNetPayment(), modifiedUser.getUSC(), modifiedUser.getPRSCI(), modifiedUser.getIcommingTax(), userPPS);

            stmt.executeUpdate(updateFinancialDataSql); // Executing the SQL statement to update 'financialData' table

            // 3. Update data in the 'Address' table
            // Constructing SQL statement to update address information in the 'Address' table
            String updateAddressSql = String.format("UPDATE Address SET Country='%s', State='%s', City='%s', ZIPCode=%d, StreetName='%s', StreetNumber=%d WHERE User_PPS=%d;",
                    modifiedUser.userAdress.getCountry(), modifiedUser.userAdress.getState(), modifiedUser.userAdress.getCity(), modifiedUser.userAdress.getZIPCode(), modifiedUser.userAdress.getStreetName(), modifiedUser.userAdress.getStreetNumber(), userPPS);

            stmt.executeUpdate(updateAddressSql); // Executing the SQL statement to update 'Address' table

            return true; // Returns true if the update is successful
        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Returns false if there's an exception while updating the database
        }
    }
    
}
