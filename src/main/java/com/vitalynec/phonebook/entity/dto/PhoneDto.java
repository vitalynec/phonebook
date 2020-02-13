package com.vitalynec.phonebook.entity.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;

@Data
@EqualsAndHashCode(exclude = "user")
@ToString(exclude = "user")
@XmlRootElement(name = "phone")
@XmlType(propOrder = {"id", "number"})
public class PhoneDto {
    protected Integer id;
    private UserDto user;
    protected String number;

    @XmlTransient
    public UserDto getUser() {
        return user;
    }
}
