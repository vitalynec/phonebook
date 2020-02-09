package com.vitalynec.phonebook.service.impl;

import com.vitalynec.phonebook.domain.Phone;
import com.vitalynec.phonebook.repository.PhoneRepository;
import com.vitalynec.phonebook.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneServiceImpl implements PhoneService {

    public static final String REG_EXP = "^((\\+7|7|8)+([0-9]){10})$";

    protected PhoneRepository phoneRepository;

    @Autowired
    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Optional<Phone> save(Phone entity) {
        return Optional.of(phoneRepository.save(entity));
    }

    @Override
    public List<Phone> findAll() {
        List<Phone> phoneList = new ArrayList<>();
        phoneRepository.findAll().forEach(phoneList::add);
        return phoneList;
    }

    /**
     * Извлекает номер телефона из строки до шаблона (1234567890)
     */
    public String extractNumber(String phoneNumber) {
        Matcher matcher = Pattern.compile(REG_EXP).matcher(phoneNumber);

        Assert.isTrue(matcher.matches(), "Телефонный номер не может быть распознан в строке: " + phoneNumber);

        return matcher.group(1);
    }
}
