package com.andersen;

import com.andersen.dao.UserDao;
import com.andersen.domain.User;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Main {
    public static void main(String[] args) throws Exception {
        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("src/main/resources/db.properties");
        String url = config.getString("db.url");
        String username = config.getString("db.user");
        String password = config.getString("db.password");

        UserDao userDao = new UserDao(url, username, password);
        System.out.println(userDao.getAll());
        User user = new User(4, "John", "Rambo", 40);
        userDao.update(user);
        System.out.println(userDao.getAll());
    }
}