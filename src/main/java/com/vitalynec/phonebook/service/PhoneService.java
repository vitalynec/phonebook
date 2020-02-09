package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.domain.Phone;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PhoneService {
    String extractNumber(String phoneNumber);
    Optional<Phone> save(Phone entity);
    List<Phone> findAll();
}
