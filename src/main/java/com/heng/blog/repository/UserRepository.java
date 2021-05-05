package com.heng.blog.repository;

import com.heng.blog.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByLogin(String login);
}
