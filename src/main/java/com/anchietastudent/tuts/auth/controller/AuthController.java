package com.anchietastudent.tuts.auth.controller;

import com.anchietastudent.tuts.util.dto.MessageResponseDTO;
import com.anchietastudent.tuts.auth.dto.SigninRequestDTO;
import com.anchietastudent.tuts.auth.dto.SigninResponseDTO;
import com.anchietastudent.tuts.auth.dto.SignupRequestDTO;
import com.anchietastudent.tuts.security.jwt.JwtTokenUtils;
import com.anchietastudent.tuts.security.service.UserDetailsImpl;
import com.anchietastudent.tuts.user.model.Role;
import com.anchietastudent.tuts.user.model.User;
import com.anchietastudent.tuts.user.model.enumeration.RoleName;
import com.anchietastudent.tuts.user.repository.RoleRepository;
import com.anchietastudent.tuts.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    JwtTokenUtils jwtUtils;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/auth/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SigninRequestDTO loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new SigninResponseDTO(
                userDetails.getId(),
                userDetails.getName(),
                userDetails.getEmail(),
                jwt,
                roles));
    }

    @PostMapping("/auth/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDTO dto) {
        if(!validateFields(dto)) return ResponseEntity.badRequest().body(new MessageResponseDTO("Preencha todos os campos!"));

        if (userRepository.existsByEmail(dto.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponseDTO("Email já está em uso!"));
        }

        User user = new User( dto.getName(), dto.getEmail(), dto.getBirthDate(), encoder.encode(dto.getPassword()), dto.getPhone());
        String strRoles = dto.getRole();
        Set<Role> roles = new HashSet<>();

        if (StringUtils.isEmpty(strRoles)) {
            Role userRole = roleRepository.findByName(RoleName.STUDENT)
                    .orElseThrow(() -> new RuntimeException("Função de usuário não encontrada"));
            roles.add(userRole);
        } else {
            switch (strRoles) {
                case "Aluno":
                     Role adminRole = roleRepository.findByName(RoleName.STUDENT)
                            .orElseThrow(() -> new RuntimeException("Função de usuário não encontrada"));
                     roles.add(adminRole);
                     break;
                case "Professor":
                     Role modRole = roleRepository.findByName(RoleName.TEACHER)
                             .orElseThrow(() -> new RuntimeException("Função de usuário não encontrada"));
                     roles.add(modRole);
                     break;
                }
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDTO("Usuário cadastrado com sucesso!"));
    }

    private Boolean validateFields(SignupRequestDTO signupRequestDTO) {
        if(Stream.of(signupRequestDTO.getEmail(),
                signupRequestDTO.getPassword(),
                signupRequestDTO.getBirthDate(),
                signupRequestDTO.getName()).allMatch(StringUtils::isEmpty)) {
            return false;
        }
        return true;
    }
}