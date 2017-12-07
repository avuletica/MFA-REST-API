package com.fesb.mfa.service;

import com.fesb.mfa.domain.User;

import java.util.List;

public interface GenericService {
    User findByUsername(String username);

    List<User> findAllUsers();
}
