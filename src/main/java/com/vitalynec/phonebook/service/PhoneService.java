package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.entity.dto.PhoneDto;

import java.util.List;
import java.util.Optional;

public interface PhoneService {
    String extractNumber(String phoneNumber);

    Optional<PhoneDto> save(PhoneDto entity);

    List<PhoneDto> findAll();

    void deleteById(Integer id);
}
