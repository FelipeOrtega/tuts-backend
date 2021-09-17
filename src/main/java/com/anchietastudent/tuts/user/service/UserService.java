package com.anchietastudent.tuts.user.service;

import com.anchietastudent.tuts.user.model.User;
import com.anchietastudent.tuts.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }
}
