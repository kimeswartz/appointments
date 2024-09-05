package com.useo.demo.controllers.authentication;

import com.useo.demo.entities.user.User;
import com.useo.demo.dtos.user.UserLoginRequestDto;
import com.useo.demo.dtos.user.UserSaveRequestDto;
import com.useo.demo.responses.LoginResponse;
import com.useo.demo.services.authentication.AuthenticationService;
import com.useo.demo.services.authentication.JwtService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<User> register(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        User registeredUser = authenticationService.signup(userSaveRequestDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        User authenticatedUser = authenticationService.authenticate(userLoginRequestDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}

