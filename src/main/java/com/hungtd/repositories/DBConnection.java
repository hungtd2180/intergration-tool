package com.hungtd.repositories;


import java.sql.*;

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

    private static void setParameters(PreparedStatement stmt, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
    }

    public ResultSet executeQuery(String query, Object... params) {
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            setParameters(stmt, params);
            return stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int executeUpdate(String query, Object... params) {
        try  {
            PreparedStatement stmt = connection.prepareStatement(query);
            setParameters(stmt, params);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}