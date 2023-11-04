//package com.codebooq.controller;
//
//import com.codebooq.model.domain.request.CreateUserRequest;
//import com.codebooq.model.domain.request.LoginRequest;
//import com.codebooq.model.domain.request.oAuth2LoginRequest;
//import com.codebooq.service.SecurityService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Map;
//
//@RestController
//@RequestMapping("/security")
//@RequiredArgsConstructor
//public class SecurityController {
//
//    private final SecurityService securityService;
//
//    @PostMapping("/register")
//    public ResponseEntity<Void> register(@RequestBody CreateUserRequest request) {
//        return securityService.register(request);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
//        return securityService.login(request);
//    }
//
//    @PostMapping("/oauth2-login")
//    public ResponseEntity<String> oauth2Login(@RequestBody oAuth2LoginRequest request) {
//        return securityService.oauth2Login(request);
//    }
//
//
//}
