package com.vitalynec.phonebook.entity.dto;

import lombok.Data;

import java.util.Set;

@Data
public class UserDto {
    protected Integer id;
    protected String name;
    private Set<PhoneDto> phoneList;
}
