package com.anchietastudent.tuts.user.controller;

import com.anchietastudent.tuts.category.service.CategoryService;
import com.anchietastudent.tuts.user.dto.UserProfileDTO;
import com.anchietastudent.tuts.user.service.UserService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
