package com.vitalynec.phonebook.entity.dto;

import com.vitalynec.phonebook.entity.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
public class PhoneDto {
    protected Integer id;
    private User user;
    protected String number;
}
