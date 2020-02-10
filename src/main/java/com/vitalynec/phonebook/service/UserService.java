package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.entity.User;
import com.vitalynec.phonebook.entity.dto.UserDto;
import com.vitalynec.phonebook.exception.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<User> save(User entity);

    List<UserDto> findAll();

    Optional<UserDto> findById(Integer id) throws NotFoundException;
}
