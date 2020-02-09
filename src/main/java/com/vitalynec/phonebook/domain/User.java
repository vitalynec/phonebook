package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    protected Long id;

    @Column
    protected String name;

    @OneToMany()
    protected Set<Phone> phoneList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones{=" + phoneList +
                '}';
    }
}
