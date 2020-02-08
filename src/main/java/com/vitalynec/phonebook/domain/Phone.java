package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class Phone {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    protected UUID id;

    @Column (nullable = false, name = "user_id")
    protected UUID userId;

    @Column (nullable = false, name = "number")
    protected String number;
}
