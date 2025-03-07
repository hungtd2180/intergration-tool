package com.hungtd.repositories;


import com.hungtd.entities.IdEntity;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.hungtd.utils.CommonUtil.*;

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

    public Boolean create(T entity){
    // Implement logic to create a new entity
        createTable();
        StringBuilder stmt = new StringBuilder("INSERT INTO " + entityClass.getSimpleName() + " " + getPropertyName(entityClass) + " VALUES ");
        List<String> placeholders = new ArrayList<>();
        List<Object> values = new ArrayList<>();
        for (Field field : entityClass.getDeclaredFields()) {
            field.setAccessible(true);
            placeholders.add("?");
            try {
                values.add(field.get(entity));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }
        stmt.append(convertListToValue(placeholders));
        return dbConnection.executeUpdate(stmt.toString(), values.toArray()) > 0;
    }

    public void createTable () {
        // Implement logic to create a new entity
        StringBuilder stmt = new StringBuilder("CREATE TABLE IF NOT EXISTS " + entityClass.getSimpleName() + " ");
        Field[] fields = entityClass.getDeclaredFields();
        List<String> values = new ArrayList<>();
        values.add("id INT PRIMARY KEY AUTO_INCREMENT");
        for (Field field : fields){
            switch (field.getType().getSimpleName()) {
                case "String":
                    values.add(field.getName() + " VARCHAR(255)");
                    break;
                case "Long":
                    values.add(field.getName() + " BIGINT");
                    break;
                case "Integer":
                    values.add(field.getName() + " INT");
                    break;
                case "Double":
                    values.add(field.getName() + " DOUBLE");
                    break;
                case "Float":
                    values.add(field.getName() + " FLOAT");
                    break;
                case "Boolean":
                    values.add(field.getName() + " BOOLEAN");
                    break;
                default:
                    break;
            }
        }
        stmt.append(convertListToValue(values));
        dbConnection.executeUpdate(stmt.toString());
    }
}