/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOCTaxCalculater;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
/**
 *Initially we tried to connect the database but it didnt work how we expected. We chose to let this way and keep with the code and try the other requirements. 
 *We know that the structure is not much like this, have to be the MySQL form. We are very sorry for that.
 * @author Lizandra 2022236 and Taciana 2022404
 */

public class DatabaseSetup extends Database {

    final static String dbBaseURL = "jdbc:mysql://localhost";
    final static String USER = "OOC2023";
    final static String PASSWORD = "ooc2023";

    public static boolean setupDB() throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        try (Connection conn = DriverManager.getConnection(dbBaseURL, USER, PASSWORD);  Statement stmt = conn.createStatement();) {

            // Verify if user 'OOC2023' exists in the table
            String checkUserExistsQuery = "SELECT * FROM User WHERE UserName = 'OOC2023'";
            ResultSet resultSet = stmt.executeQuery(checkUserExistsQuery);

            if (!resultSet.next()) {
                // The user 'OOC2023' does not exists, so put it
                String insertUserQuery = "INSERT INTO User (UserName, PassWord) VALUES ('OOC2023', 'ooc2023')";
                stmt.executeUpdate(insertUserQuery);
                System.out.println("User 'OOC2023' added successfully!");
            } else {
                System.out.println("User 'OOC2023' already in the table.");
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
