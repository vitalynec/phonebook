package com.vitalynec.phonebook.service.impl;

import com.vitalynec.phonebook.domain.User;
import com.vitalynec.phonebook.repository.UserRepository;
import com.vitalynec.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    protected UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.of(userRepository.save(entity));
    }

    @Override
    @Transactional
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        userList.addAll(userRepository.findAll());
        return userList;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    @Transactional
    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }
}
