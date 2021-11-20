package com.anchietastudent.tuts.user.service;

import com.anchietastudent.tuts.user.dto.UserProfileDTO;
import com.anchietastudent.tuts.user.model.Role;
import com.anchietastudent.tuts.user.model.User;
import com.anchietastudent.tuts.user.repository.UserRepository;
import com.anchietastudent.tuts.util.dto.MessageResponseDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    PasswordEncoder encoder;

    public Optional<User> getByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findOne(UUID id) throws NotFoundException {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Usuario nao encontrado"));
    }

    public UserProfileDTO findUserData(UUID id) throws NotFoundException {
        User user = findOne(id);
        Role role = user.getRoles().stream().limit(Integer.toUnsignedLong(1)).findFirst().get();
        return UserProfileDTO.buildDTO(user, role);
    }

    public MessageResponseDTO update(User user, UUID id) throws NotFoundException {
        User toUpdate = findOne(id);
        toUpdate.setName(user.getName());
        toUpdate.setPassword(encoder.encode(user.getPassword()));
        toUpdate.setPhone(user.getPhone());
        repository.save(toUpdate);
        return new MessageResponseDTO("Atualizado com Sucesso!");
    }
}
