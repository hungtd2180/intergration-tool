package com.hungtd.repositories;


import com.hungtd.entities.IdEntity;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hungtd
 * Date: 06/03/2025
 * Time: 9:55 AM
 * for all issues, contact me: hungtd2180@gmail.com
 */

public class CustomRepository<T extends IdEntity> {
    protected Class<T> entityClass;
    protected DBConnection dbConnection;

    public CustomRepository(Class<T> entityClass){
        this.entityClass = entityClass;
        this.dbConnection = DBConnection.getInstance();
    }

    public T findById(Long id){
        // Implement logic to find and return entity with given id
        String stmt = "SELECT * FROM " + entityClass.getSimpleName() + " WHERE id = ?";
        ResultSet resultSet = dbConnection.executeQuery(stmt, id);
        try {
            if (resultSet.next()){
                return entityClass.getDeclaredConstructor(ResultSet.class).newInstance(resultSet);
            }
        } catch (Exception e){

        }
        return null;
    }

    public List<T> findAll(){
        // Implement logic to find and return all entities
        String stmt = "SELECT * FROM " + entityClass.getSimpleName();
        ResultSet resultSet = dbConnection.executeQuery(stmt);
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()){
                entities.add(entityClass.getDeclaredConstructor(ResultSet.class).newInstance(resultSet));
            }
        } catch (Exception e){

        }
        return entities;
    }
}