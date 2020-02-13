package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.entity.dto.PhoneDto;

import java.util.Optional;

public interface PhoneService {
    /**
     * Извлекает номер телефона из строки до шаблона
     */
    String extractNumber(String phoneNumber);

    Optional<PhoneDto> save(PhoneDto entity);

    void deleteById(Integer id);
}
