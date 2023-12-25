/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ca2calculationtax;

/**
 *
 * @author lizam
 */
public abstract class Database {
   protected final static String dbBaseURL = "jdbc:mysql://localhost";
   protected final static String USER = "ooc2023";
   protected final static String PASSWORD = "ooc2023";
   protected final static String dbName = "mydb";
   protected final static String tableName = "User";
   protected final static String db_URL = dbBaseURL + "/" + dbName;
}
