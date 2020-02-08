package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.UUID;

@Data
@Entity
public class Phone extends AbstractEntity {

    @Column(nullable = false, name = "user_id")
    protected UUID userId;

    @Column(nullable = false, name = "number")
    protected String number;
}
