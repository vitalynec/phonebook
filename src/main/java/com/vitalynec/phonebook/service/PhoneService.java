package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.entity.Phone;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    String extractNumber(String phoneNumber);

    Optional<Phone> save(Phone entity);

    List<Phone> findAll();

    void deleteById(Integer id);
}
