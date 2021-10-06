package com.anchietastudent.tuts.user.service;

import com.anchietastudent.tuts.user.model.User;
import com.anchietastudent.tuts.user.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findOne(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }
}
