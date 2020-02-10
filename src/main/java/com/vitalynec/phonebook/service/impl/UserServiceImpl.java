package com.vitalynec.phonebook.service.impl;

import com.vitalynec.phonebook.entity.User;
import com.vitalynec.phonebook.entity.dto.UserDto;
import com.vitalynec.phonebook.exception.NotFoundException;
import com.vitalynec.phonebook.repository.UserRepository;
import com.vitalynec.phonebook.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    protected UserRepository userRepository;
    protected ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

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
    @Transactional
    public Optional<UserDto> findById(Integer id) throws NotFoundException {
        return Optional.of(convertToDto(userRepository.findById(id).orElseThrow(NotFoundException::new)));
    }

    private UserDto convertToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    private User convertToEntity(UserDto userDto) {
        return modelMapper.map(userDto, User.class);
    }
}
