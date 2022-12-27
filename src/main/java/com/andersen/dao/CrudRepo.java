package com.andersen.dao;

import com.andersen.domain.User;

import java.util.List;

public interface CrudRepo {

    void save(User user);

    User get(long userId);

    List<User> getAll();

    void update(User user);

    void delete(long userId);
}
