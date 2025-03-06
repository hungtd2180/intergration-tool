package com.hungtd.repository;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by hungtd
 * Date: 06/03/2025
 * Time: 9:13 AM
 * for all issues, contact me: hungtd2180@gmail.com
 */

public class DBConnection {
    private static DBConnection instance;
    private Connection connection;
    public DBConnection(){
        try{
            connection = DriverManager.getConnection("jdbc:h2:./mydb", "sa", "");
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance(){
        if(instance == null){
            synchronized (DBConnection.class){
                if(instance == null){
                    instance = new DBConnection();
                }
            }
        }
        return instance;
    }
}