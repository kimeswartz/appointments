package com.useo.demo.controllers.authentication.admin;

import com.useo.demo.dtos.user.UserLoginRequestDto;
import com.useo.demo.entities.user.User;

import com.useo.demo.services.jwt.JwtService;
import com.useo.demo.responses.LoginResponse;
import com.useo.demo.services.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin-access")
public class AdminAuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AdminAuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        User authenticatedUser = authenticationService.authenticate(userLoginRequestDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}