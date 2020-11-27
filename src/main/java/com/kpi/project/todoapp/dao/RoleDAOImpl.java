package com.kpi.project.todoapp.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAOImpl implements RoleDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<String> getRoleNames(Long userId) {
        String sql = "SELECT r.role " //
                + " FROM user_role ur, roles r " //
                + " WHERE ur.role_Id = r.id AND ur.user_Id = ? ";

        Object[] params = new Object[] { userId };

        List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

        return roles;
    }

}
