package com.vitalynec.phonebook.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class PhoneDto {
    protected Integer id;
    private UserDto user;
    protected String number;
}
