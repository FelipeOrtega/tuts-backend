package com.anchietastudent.tuts.user.controller;

import com.anchietastudent.tuts.user.dto.UserProfileDTO;
import com.anchietastudent.tuts.user.model.User;
import com.anchietastudent.tuts.user.service.UserService;
import com.anchietastudent.tuts.util.dto.MessageResponseDTO;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("{id}/profile")
    public ResponseEntity<UserProfileDTO> findUserData(@PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(userService.findUserData(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageResponseDTO> update(@RequestBody User updated, @PathVariable UUID id) throws NotFoundException {
        return ResponseEntity.ok(userService.update(updated, id));
    }
}
