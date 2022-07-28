package com.example.basicsecurity.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.basicsecurity.request.LoginRequest;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/api/auth/login")
    public void login(@RequestBody LoginRequest loginRequest, HttpSession session){
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
        
        Authentication authentication = authenticationManager.authenticate(token);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        session.setAttribute("MY_SESSION", authentication.getName());
    }

    
}
