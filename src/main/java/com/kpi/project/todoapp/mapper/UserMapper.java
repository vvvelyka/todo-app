package com.kpi.project.todoapp.mapper;

import com.kpi.project.todoapp.model.UserItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserItem> {

    public static final String BASE_SQL
            = "SELECT u.id, u.first_name, u.last_name, u.email, u.password FROM users u";

    @Override
    public UserItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("id");
        String firstName = rs.getString("first_name");
        String lastName = rs.getString("last_name");
        String email = rs.getString("email");
        String password = rs.getString("password");

        return new UserItem(id, firstName, lastName, email, password);
    }
}
