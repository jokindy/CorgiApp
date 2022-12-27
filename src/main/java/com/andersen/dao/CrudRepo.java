package com.andersen.dao;

import com.andersen.domain.User;

public interface CrudRepo {

    void save(User user);

    User get(long userId);

    void update(User user);

    void delete(long userId);
}
