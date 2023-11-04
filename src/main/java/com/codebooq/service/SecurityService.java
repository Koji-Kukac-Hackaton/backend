//package com.codebooq.service;
//
//import com.codebooq.model.domain.User;
//import com.codebooq.model.domain.enums.Role;
//import com.codebooq.model.domain.request.CreateUserRequest;
//import com.codebooq.model.domain.request.LoginRequest;
//import com.codebooq.model.domain.request.oAuth2LoginRequest;
//import com.codebooq.repository.UserRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.Optional;
//
//
//@Service
//@RequiredArgsConstructor
//public class SecurityService {
//
//    private final UserRepository userRepository;
//    private final BCryptPasswordEncoder passwordEncoder;
//    private final JwtService jwtService;
//    private final AuthenticationManager authenticationManager;
//
//    public ResponseEntity<Void> register(CreateUserRequest request) {
//
//        if(userRepository.existsByEmail(request.getEmail())) {
//            return ResponseEntity.badRequest().build();
//        }
//
//        User user = User.builder()
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .firstName(request.getFirstName())
//                .lastName(request.getLastName())
//                .role(Role.ROLE_USER)
//                .build();
//
//        userRepository.save(user);
//
//        return ResponseEntity.ok().build();
//    }
//
//
//    public ResponseEntity<String> login(LoginRequest request) {
//
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        User user = userRepository.findByEmail(request.getEmail())
//                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
//        String token = jwtService.generateAccessToken(user);
//        return ResponseEntity.ok(token);
//
//    }
//
//
//    public ResponseEntity<String> oauth2Login(oAuth2LoginRequest request) {
//        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
//
//        if(optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            String token = jwtService.generateAccessToken(user);
//            return ResponseEntity.ok(token);
//        } else {
//
//            User newUser = User.builder()
//                    .email(request.getEmail())
//                    .firstName(request.getFirstName())
//                    .lastName(request.getLastName())
//                    .role(Role.ROLE_USER)
//                    .build();
//            userRepository.save(newUser);
//            String token = jwtService.generateAccessToken(newUser);
//            return ResponseEntity.ok(token);
//        }
//    }
//}
