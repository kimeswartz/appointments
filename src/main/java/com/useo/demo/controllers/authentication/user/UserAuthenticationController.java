package com.useo.demo.controllers.authentication.user;

import com.useo.demo.entities.user.User;
import com.useo.demo.dtos.user.UserLoginRequestDto;
import com.useo.demo.dtos.user.UserSaveRequestDto;
import com.useo.demo.responses.LoginResponse;
import com.useo.demo.services.authentication.AuthenticationService;
import com.useo.demo.services.jwt.JwtService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user-access")
public class UserAuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public UserAuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody UserSaveRequestDto userSaveRequestDto) {
        User registeredUser = authenticationService.signup(userSaveRequestDto);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody UserLoginRequestDto userLoginRequestDto) {
        User authenticatedUser = authenticationService.authenticate(userLoginRequestDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}