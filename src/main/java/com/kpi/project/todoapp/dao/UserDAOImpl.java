package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.mapper.UserMapper;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    JdbcTemplate jdbcTemplate;
    DataSource dataSource;

    private final String SQL_FIND_PERSON = "select * from users where id = ?";
    private final String SQL_DELETE_PERSON = "delete from users where id = ?";
    private final String SQL_UPDATE_PERSON = "update users set first_name = ?, last_name = ?, email  = ? where id = ?";
    private final String SQL_GET_ALL = "select * from users";
    private final String SQL_INSERT_PERSON = "insert into users(first_name, last_name, email, password) values(?,?,?,?)";

    @Autowired
    public UserDAOImpl(DataSource dataSource) {

        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public UserItem getUserById(Long id) {
        return jdbcTemplate.queryForObject(SQL_FIND_PERSON, new Object[] { id }, new UserMapper());
        //????????????(SQL_FIND_PERSON, new UserMapper(), new Object[] { id });????????????????
    }

    @Override
    public List<UserItem> getAllUsers() {

        List<UserItem> userList = new ArrayList<>();
//        jdbcTemplate.query(sql, new ResultSetExtractor() {
//                    public List extractData(ResultSet rs) throws SQLException {
//
//                        while (rs.next()) {
//                            String name = rs.getString("name");
//                            dogList.add(name);
//                        }
//                        return dogList;
//                    }
//                }
//
//
//        );
//
        return userList;

        //return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
    }

//    @Override
//    boolean findUserAccount(String email, String password) {
//
//
//    }

//    @Override
//    public UserItem findUserAccount(String userName) {
//        // Select .. from App_User u Where u.User_Name = ?
//        String sql = UserMapper.BASE_SQL + " where u.User_Name = ? ";
//        try {
//            UserItem userInfo = jdbcTemplate.queryForObject(SQL_FIND_PERSON, new UserMapper(), new Object[] { userName });
//            return userInfo;
//        } catch (EmptyResultDataAccessException e) {
//            return null;
//        }
//    }

    @Override
    public boolean createUser(UserItem user) {
        return jdbcTemplate.update(SQL_INSERT_PERSON, user.getFirstName(), user.getLastName(),
                user.getEmail(), user.getPassword()) > 0;
    }

    @Override
    public boolean updateUser(UserItem user) {
        return jdbcTemplate.update(SQL_UPDATE_PERSON, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getId()) > 0;
    }

    @Override
    public boolean deleteUser(UserItem user) {
        return jdbcTemplate.update(SQL_DELETE_PERSON, user.getId()) > 0;
    }


}
