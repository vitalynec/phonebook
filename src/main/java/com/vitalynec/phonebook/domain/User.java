package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User extends AbstractEntity {

    @Column
    protected String name;

    @OneToMany
    protected Set<Phone> phoneList = new HashSet<>();
}
