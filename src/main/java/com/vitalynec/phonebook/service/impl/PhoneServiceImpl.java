package com.vitalynec.phonebook.service.impl;

import com.vitalynec.phonebook.entity.Phone;
import com.vitalynec.phonebook.entity.dto.PhoneDto;
import com.vitalynec.phonebook.repository.PhoneRepository;
import com.vitalynec.phonebook.service.PhoneService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class PhoneServiceImpl implements PhoneService {

    public static final String REG_EXP = "^((\\+7|7|8)+([0-9]){10})$";

    protected PhoneRepository phoneRepository;
    protected ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Autowired
    public void setPhoneRepository(PhoneRepository phoneRepository) {
        this.phoneRepository = phoneRepository;
    }

    @Override
    public Optional<PhoneDto> save(PhoneDto dto) {
        Phone phone = convertToEntity(dto);
        phone.setNumber(extractNumber(dto.getNumber()));
        return Optional.of(convertToDto(phoneRepository.save(phone)));
    }

    @Override
    public List<PhoneDto> findAll() {
        return phoneRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Integer id) {
        phoneRepository.deleteById(id);
    }

    /**
     * Извлекает номер телефона из строки до шаблона (+7/7/8-1234567890)
     */
    public String extractNumber(String phoneNumber) {
        Matcher matcher = Pattern.compile(REG_EXP).matcher(phoneNumber);

        Assert.isTrue(matcher.matches(), "Телефонный номер не может быть распознан в строке: " + phoneNumber);

        return matcher.group(1);
    }

    private PhoneDto convertToDto(Phone phone) {
        return modelMapper.map(phone, PhoneDto.class);
    }

    private Phone convertToEntity(PhoneDto phoneDto) {
        return modelMapper.map(phoneDto, Phone.class);
    }
}
