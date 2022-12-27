package com.andersen.dao;

import com.andersen.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao implements CrudRepo {

    private final JdbcTemplate jdbcTemplate;

    public UserDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void save(User user) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("USERS")
                .usingGeneratedKeyColumns("id");
        long id = simpleJdbcInsert.executeAndReturnKey(user.toMap()).longValue();
        user.setId(id);
    }

    public User get(long userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", this::mapRowToUser, userId);
    }

    public void update(User user) {
        long userId = user.getId();
        User userDb = get(userId);
        if (userDb.equals(user)) {
            throw new RuntimeException("You trying to update same user");
        }
        jdbcTemplate.update("UPDATE users SET name = ?, surname = ?, age = ? WHERE id = ?",
                user.getName(), user.getSurname(), user.getAge(), userId);
    }


    public void delete(long userId) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", userId);
    }

    private User mapRowToUser(ResultSet userRows, int rowNum) throws SQLException {
        int id = userRows.getInt("id");
        String name = userRows.getString("name");
        String surname = userRows.getString("surname");
        int age = userRows.getInt("age");
        return new User(id, name, surname, age);
    }
}
