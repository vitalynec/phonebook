package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.domain.User;
import com.vitalynec.phonebook.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService extends AbstractService<User, UserRepository> {

    public UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> save(User entity) {
        return Optional.of(repository.save(entity));
    }

    public List<User> findAll(){
        List<User> userList = new ArrayList<>();
        repository.findAll().forEach(userList::add);
        return userList;
    }
}
