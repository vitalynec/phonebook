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

//    @ManyToOne(fetch = FetchType.LAZY)
    @Column(nullable = false, name = "user_id")
    protected Long userId;

    @Column(nullable = false, name = "number")
    protected String number;
}
