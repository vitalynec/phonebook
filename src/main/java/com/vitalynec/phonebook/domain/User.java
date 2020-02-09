package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    protected Long id;

    @Column
    protected String name;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private Set<Phone> phoneList = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
//                ", phones{=" + phoneList +
                '}';
    }
}
