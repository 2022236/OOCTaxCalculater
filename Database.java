/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package OOCTaxCalculater;

/**
 *
 * @author Lizandra Matos 2022336 and Taciana Oliveira 2022404
 */
// an abstract class representing a Database
public abstract class Database {
   // Constants defining database connection parameters
   protected final static String dbBaseURL = "jdbc:mysql://localhost";
   protected final static String USER = "OOC2023";
   protected final static String PASSWORD = "ooc2023";
   protected final static String dbName = "mydb";
   protected final static String tableName = "User";
   protected final static String db_URL = dbBaseURL + "/" + dbName;
   
   
}
