package com.vitalynec.phonebook.service;

import com.vitalynec.phonebook.domain.AbstractEntity;
import com.vitalynec.phonebook.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractService<E extends AbstractEntity, R extends CommonRepository<E>> implements CommonService<E> {

    protected final R repository;

    @Autowired
    public AbstractService(R repository) {
        this.repository = repository;
    }
}
