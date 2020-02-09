package com.vitalynec.phonebook.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Phone {

    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    protected Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "FK_user_id")
    @JsonManagedReference
    private User user;

    @Column(nullable = false, name = "number")
    protected String number;

    @Override
    public String toString() {
        return "Phone{" +
                "id=" + id +
                ", number='" + number + '\'' +
                '}';
    }
}
