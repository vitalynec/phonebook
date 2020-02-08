package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.domain.AbstractEntity;

import java.util.Optional;

public interface CommonService<E extends AbstractEntity> {
    Optional<E> save (E entity);
}
