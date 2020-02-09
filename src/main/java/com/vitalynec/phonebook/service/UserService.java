package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> save(User entity);

    List<User> findAll();

    Optional<User> findById(Long id);
    Optional<User> findByName(String name);
}
