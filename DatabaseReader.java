package OOCTaxCalculater;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static taxcalculationsca.Database.PASSWORD;
import static taxcalculationsca.Database.USER;
import static taxcalculationsca.Database.db_URL;
import static taxcalculationsca.Database.tableName;

/**
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */

// This method retrieves user information from the database based on the provided username and password.
public class DatabaseReader extends Database {
    public User getUser(String userName, String password) {
        try (
            Connection conn = DriverManager.getConnection(db_URL, USER, PASSWORD); // database connection
            Statement stmt = conn.createStatement();  // creates a statement object for executing SQL queries
        ) {
            String sql = String.format("SELECT * FROM %s u " +
                        "LEFT JOIN financialData f ON u.PPS = f.User_PPS " +
                        "LEFT JOIN Address a ON u.PPS = a.User_PPS " +
                        "WHERE u.UserName='%s' AND u.PassWord='%s';", tableName, userName, password); //SQL query to retrieve user data


            ResultSet results = stmt.executeQuery(sql); // Executes the query and retrieves the results

            if (results.next()) {
                // Retrieves data from the "User" table
                String retrievedUserName = results.getString("u.UserName");
                String retrievedFirstName = results.getString("u.FirstName");
                String retrievedLastName = results.getString("u.LastName");
                String retrievedPosition = results.getString("u.Position");
                Date retrievedBirthDate = results.getDate("u.BirthDate");
                String retrievedEmail = results.getString("u.Email");
                String retrievedPassWord = results.getString("u.PassWord");
                String retrievedType = results.getString("u.Type");
                commonVariables.Type userType;
                // Maps user type based on retrieved data
                if (retrievedType.equals("Regular")) {
                    userType = commonVariables.Type.Regular;
                } else {
                    userType = commonVariables.Type.Admin;
                }
                String retrievedMaritalStatus = results.getString("u.MaritalStatus");
                commonVariables.MaritalStatus userMaritalStatus;
                 // Maps user marital status based on retrieved data
                switch (retrievedMaritalStatus) {
                    case "Single":
                        userMaritalStatus = commonVariables.MaritalStatus.Single;
                        break;
                    case "Married":
                        userMaritalStatus = commonVariables.MaritalStatus.Married;
                        break;
                    case "Widowed":
                        userMaritalStatus = commonVariables.MaritalStatus.widowed;
                        break;
                    default:
                        throw new AssertionError();
                }

                // Retrieves data from the "financialData" table
                float retrievedGrossPayment = results.getFloat("f.GrossPayment");
                float retrievedNetPayment = results.getFloat("f.NetPayment");
                float retrievedUsc = results.getFloat("f.USC");
                float retrievedPrsci = results.getFloat("f.PRSCI");
                float retrievedIcommingTax = results.getFloat("f.IcommingTax");
                int retrievedFinacialDataID = results.getInt("f.FinacialDataID");
                boolean retrievedIsEmployer = results.getBoolean("f.IsEmployer");
                int retrievedUserPPS = results.getInt("f.User_PPS");

                // Retrieves data from the "Address" table
                String retrievedCountry = results.getString("a.Country");
                String retrievedState = results.getString("a.State");
                String retrievedCity = results.getString("a.City");
                int retrievedZIPCode = results.getInt("a.ZIPCode");
                String retrievedStreetName = results.getString("a.StreetName");
                int retrievedStreetNumber = results.getInt("a.StreetNumber");

                // Checks for a match in the "financialData" table
                if (retrievedFinacialDataID != 0 && retrievedGrossPayment != 0) {
                     // Creates and returns a new User object with retrieved data
                    return new User(
                            retrievedUserName,
                            retrievedFirstName,
                            retrievedLastName,
                            retrievedPassWord,
                            retrievedBirthDate,
                            retrievedPosition,
                            userType,
                            retrievedEmail,
                            userMaritalStatus,
                            retrievedGrossPayment,
                            retrievedNetPayment, 
                            retrievedUsc, 
                            retrievedPrsci, 
                            retrievedIcommingTax,
                            retrievedCountry,
                            retrievedState,
                            retrievedCity,
                            retrievedZIPCode,
                            retrievedStreetName,
                            retrievedStreetNumber,
                            retrievedUserPPS
                    );
                } 
            }

            return null; // Returns null if no matching user is found
        } catch (Exception e) { // Prints the exception stack trace
            e.printStackTrace();
            return null; // Returns null in case of an exception
        }
    }
    
    
    // This method retrieves all users' information from the database.
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>(); // Creates a new list to store User objects


        try (
                 Connection conn = DriverManager.getConnection(db_URL, USER, PASSWORD);// Establishes a database connection
                Statement stmt = conn.createStatement();) { // Creates a statement object for executing SQL queries
            String sql = String.format("SELECT * FROM %s u "
                    + "LEFT JOIN financialData f ON u.PPS = f.User_PPS "
                    + "LEFT JOIN Address a ON u.PPS = a.User_PPS;", tableName); // SQL query to retrieve all user data

            ResultSet results = stmt.executeQuery(sql); // Executes the query and retrieves the results

            while (results.next()) {
                // Obtenha os dados da tabela "User"
                String retrievedUserName = results.getString("u.UserName");
                String retrievedFirstName = results.getString("u.FirstName");
                String retrievedLastName = results.getString("u.LastName");
                String retrievedPosition = results.getString("u.Position");
                Date retrievedBirthDate = results.getDate("u.BirthDate");
                String retrievedEmail = results.getString("u.Email");
                String retrievedPassWord = results.getString("u.PassWord");
                String retrievedType = results.getString("u.Type");
                commonVariables.Type userType;
                if (retrievedType.equals("Regular")) {
                    userType = commonVariables.Type.Regular;
                } else {
                    userType = commonVariables.Type.Admin;
                }
                String retrievedMaritalStatus = results.getString("u.MaritalStatus");
                commonVariables.MaritalStatus userMaritalStatus;

                switch (retrievedMaritalStatus) {
                    case "Single":
                        userMaritalStatus = commonVariables.MaritalStatus.Single;
                        break;
                    case "Married":
                        userMaritalStatus = commonVariables.MaritalStatus.Married;
                        break;
                    case "Widowed":
                        userMaritalStatus = commonVariables.MaritalStatus.widowed;
                        break;
                    default:
                        throw new AssertionError();
                }

                // Retrieves data from the "financialData" table
                float retrievedGrossPayment = results.getFloat("f.GrossPayment");
                float retrievedNetPayment = results.getFloat("f.NetPayment");
                float retrievedUsc = results.getFloat("f.USC");
                float retrievedPrsci = results.getFloat("f.PRSCI");
                float retrievedIcommingTax = results.getFloat("f.IcommingTax");
                int retrievedFinacialDataID = results.getInt("f.FinacialDataID");
                boolean retrievedIsEmployer = results.getBoolean("f.IsEmployer");
                int retrievedUserPPS = results.getInt("f.User_PPS");

                 // Retrieves data from the "Address" table
                String retrievedCountry = results.getString("a.Country");
                String retrievedState = results.getString("a.State");
                String retrievedCity = results.getString("a.City");
                int retrievedZIPCode = results.getInt("a.ZIPCode");
                String retrievedStreetName = results.getString("a.StreetName");
                int retrievedStreetNumber = results.getInt("a.StreetNumber");

                // Checks for a match in the "financialData" table
                if (retrievedFinacialDataID != 0 && retrievedGrossPayment != 0) {
                    // Creates a new User object and adds it to the list
                    userList.add(new User(
                            retrievedUserName,
                            retrievedFirstName,
                            retrievedLastName,
                            retrievedPassWord,
                            retrievedBirthDate,
                            retrievedPosition,
                            userType,
                            retrievedEmail,
                            userMaritalStatus,
                            retrievedGrossPayment,
                            retrievedNetPayment,
                            retrievedUsc,
                            retrievedPrsci,
                            retrievedIcommingTax,
                            retrievedCountry,
                            retrievedState,
                            retrievedCity,
                            retrievedZIPCode,
                            retrievedStreetName,
                            retrievedStreetNumber,
                            retrievedUserPPS
                    ));
                }
            }

        } catch (Exception e) {
            e.printStackTrace(); // Prints the exception stack trace
        }

        return userList; // Returns the list of User objects retrieved from the database
}
    }



