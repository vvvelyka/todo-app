package com.kpi.project.todoapp.dao;

import com.kpi.project.todoapp.mapper.UserMapper;

import com.kpi.project.todoapp.model.Todo;
import com.kpi.project.todoapp.model.UserItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {

    //@Autowired
    private JdbcTemplate jdbcTemplate;
    private TodoDAO todoDAO;
    //DataSource dataSource;

    private final String SQL_FIND_USER = "select * from users where email = ?";
    private final String SQL_VALID_USER = "select * from users where email = ? and password = ? ";//!!!!!!!!!!!!!
    private final String SQL_DELETE_PERSON = "delete from users where user_id = ?";
    private final String SQL_UPDATE_PERSON = "update users set first_name = ?, last_name = ?, email  = ? where id = ?";
    private final String SQL_GET_ALL = "select * from users";
    private final String SQL_GET_ID = "select user_id from users where email = ?";
    private final String SQL_INSERT_PERSON = "insert into users(first_name, last_name, email, password) values(?,?,?,?)";

    public UserDAOImpl() {
    }

    @Autowired
    public UserDAOImpl(DataSource dataSource, TodoDAOImpl todoDAO) {

        //this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.todoDAO = todoDAO;
    }

    @Override
    public int getIdByEmail(String email) {
        int id = 1;
        return id;
    }

    @Override
    public UserItem getUserByEmail(String email) throws DataAccessException {
        return jdbcTemplate.queryForObject(SQL_FIND_USER, new Object[]{email}, new UserMapper());
        //????????????(SQL_FIND_PERSON, new UserMapper(), new Object[] { id });????????????????
    }

    @Override
    public UserItem validUser(String email, String password) {
        UserItem user = jdbcTemplate.queryForObject(SQL_FIND_USER, new Object[]{email}, new UserMapper());
        System.out.println(user.getEmail());
        System.out.println(user.getId());

        if (password.equals(user.getPassword())) {

            List<Todo> todoList = todoDAO.getAllTodos(user.getId());
//                    if(!(todoDAO.getAllTodos(user.getId())).contains(null)) {
//                        todoList
//                    }
            user.setTodoList(todoList);
//            for(int i=0;i<todoList.size();i++){
//                System.out.println(todoList.get(i));
//            }

            return user;
        } else {
            return null;
        }

    }

    @Override
    public List<UserItem> getAllUsers() {

//        List<UserItem> userList = new ArrayList<>();
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
//        return userList;

        return jdbcTemplate.query(SQL_GET_ALL, new UserMapper());
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
