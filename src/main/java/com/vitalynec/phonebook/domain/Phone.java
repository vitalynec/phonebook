package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    protected Long id;

    @JoinColumn(nullable = false, name = "user_id")
    @ManyToOne
    protected User userId;

    @Column(nullable = false, name = "number")
    protected String number;
}
