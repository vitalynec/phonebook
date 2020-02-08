package com.vitalynec.phonebook.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Data
public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    protected UUID id;
}
