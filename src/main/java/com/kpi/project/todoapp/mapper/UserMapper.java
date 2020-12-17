package com.kpi.project.todoapp.mapper;

import com.kpi.project.todoapp.model.UserItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserItem> {

    public static final String BASE_SQL
            = "SELECT u.id, u.first_name, u.last_name, u.email, u.password FROM users u";


    public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {

        UserItem user = new UserItem();
        user.setId(rs.getLong("user_id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setEnabled(rs.getBoolean("enabled"));

        return user;
    }
}
