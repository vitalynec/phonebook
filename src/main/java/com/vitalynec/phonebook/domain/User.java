package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    protected UUID id;

    @Column
    protected String name;

    @OneToMany
    protected Set<Phone> phoneList = new HashSet<>();
}
