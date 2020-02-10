package com.vitalynec.phonebook.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    protected Integer id;

    @Column
    protected String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Phone> phoneList;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones{=" + phoneList +
                '}';
    }
}
