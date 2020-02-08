package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.domain.Phone;
import com.vitalynec.phonebook.repository.PhoneRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class PhoneService extends AbstractService<Phone, PhoneRepository> {
    public static final String REG_EXP = "^((\\+7|7|8)+([0-9]){10})$";

    public PhoneService(PhoneRepository repository) {
        super(repository);
    }

    @Override
    public Optional<Phone> save(Phone entity) {
        entity.setNumber(extractNumber(entity.getNumber()));
        return Optional.of(repository.save(entity));
    }

    /**
     * Извлекает номер телефона из строки до шаблона (+7\7\81234567890)
     */
    public String extractNumber(String phoneNumber) {
        Matcher matcher = Pattern.compile(REG_EXP).matcher(phoneNumber);

        Assert.isTrue(matcher.matches(), "Телефонный номер не может быть распознан в строке: " + phoneNumber);

        return matcher.group(1);
    }
}
