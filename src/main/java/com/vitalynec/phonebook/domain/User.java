package com.vitalynec.phonebook.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Phone> phoneList = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phones{=" + phoneList +
                '}';
    }
}
