package com.vitalynec.phonebook.entity.dto;

import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Set;

@Data
@XmlRootElement(name = "user")
@XmlType(propOrder = {"id", "name", "phoneList"})
public class UserDto {
    protected Integer id;
    protected String name;

    private Set<PhoneDto> phoneList;

    @XmlElement(name = "phone")
    public Set<PhoneDto> getPhoneList() {
        return phoneList;
    }
}
