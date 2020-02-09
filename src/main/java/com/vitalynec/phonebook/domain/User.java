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

//    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
//    protected Set<Phone> phoneList;
}
