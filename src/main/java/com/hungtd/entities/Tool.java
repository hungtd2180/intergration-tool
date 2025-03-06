package com.hungtd.entities;


import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hungtd
 * Date: 06/03/2025
 * Time: 10:18 AM
 * for all issues, contact me: hungtd2180@gmail.com
 */

public class Tool extends IdEntity{
    private String name;
    private String description;
    private String url;
    private Boolean active;

    public Tool() {}

    public Tool(ResultSet rs) throws SQLException {
        setId(rs.getLong("id"));
        setName(rs.getString("name"));
        setDescription(rs.getString("description"));
        setUrl(rs.getString("url"));
        setActive(rs.getBoolean("active"));
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

}