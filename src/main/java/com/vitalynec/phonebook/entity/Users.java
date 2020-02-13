package com.vitalynec.phonebook.entity;

import com.vitalynec.phonebook.entity.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Обертка для List<{@link UserDto}> для экспорта в XML
 */
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
@Getter
@Setter
public class Users {
    @XmlElement(name = "user")
    private List<UserDto> users;
}
