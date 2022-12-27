package com.andersen;

import com.andersen.dao.UserDao;
import com.andersen.domain.User;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Main {
    public static void main(String[] args) throws ConfigurationException {

        PropertiesConfiguration config = new PropertiesConfiguration();
        config.load("src/main/resources/db.properties");
        DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(config.getString("db.driver"));
        dataSource.setUrl(config.getString("db.url"));
        dataSource.setUsername(config.getString("db.user"));
        dataSource.setPassword(config.getString("db.password"));

        UserDao userDao = new UserDao(dataSource);
        User user = new User("John", "Rambo", 40);
        userDao.save(user);
        System.out.println(user);
    }
}