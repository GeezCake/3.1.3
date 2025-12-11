package ru.kata.spring.boot_security.demo.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import ru.kata.spring.boot_security.demo.model.User;

public interface UserService extends UserDetailsService {

    void create(User user);

    void update(User user);

    void delete(Long id);

    User getById(Long id);

    List<User> getAll();

    User findByUsername(String username);
}

