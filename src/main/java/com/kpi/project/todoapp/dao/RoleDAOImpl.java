//package com.kpi.project.todoapp.dao;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import javax.sql.DataSource;
//import java.util.List;
//
//@Repository
//public class RoleDAOImpl implements RoleDAO {
//
//    JdbcTemplate jdbcTemplate;
//
//    final String SQL_GET_NAMES = "SELECT r.role " //
//            + " FROM user_role ur, roles r " //
//            + " WHERE ur.role_Id = r.id AND ur.user_Id = ? ";
//
//    @Autowired
//    public RoleDAOImpl(DataSource dataSource) {
//        jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    @Override
//    public List<String> getRoleNames(Long userId) {
//
//        return jdbcTemplate.queryForList(SQL_GET_NAMES, String.class);
//    }
//
//}
