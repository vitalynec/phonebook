package com.vitalynec.phonebook.repository;

import com.vitalynec.phonebook.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CommonRepository<User> {
}
