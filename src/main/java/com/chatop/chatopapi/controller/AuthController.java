package com.chatop.chatopapi.controller;

import com.chatop.chatopapi.dto.request.LoginRequest;
import com.chatop.chatopapi.dto.request.RegisterRequest;
import com.chatop.chatopapi.dto.response.LoginResponse;
import com.chatop.chatopapi.dto.response.RegisterResponse;
import com.chatop.chatopapi.dto.response.UserDetailsResponse;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.JwtService;
import com.chatop.chatopapi.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DaoAuthenticationProvider authenticationProvider;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest req){
        final Optional<User> user = this.userService.findByEmail(req.getEmail());
        if(user.isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        final User newUser = User.builder()
                .name(req.getName())
                .email(req.getEmail())
                .password(passwordEncoder.encode(req.getPassword()))
                .build();
        this.userService.createUser(newUser);

        return ResponseEntity.ok(new RegisterResponse(this.jwtService.createJwt(newUser.getEmail())));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest req){

        try{
            this.authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword()));
        }
        catch (AuthenticationException e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return ResponseEntity.ok(new LoginResponse(this.jwtService.createJwt(req.getEmail())));

    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailsResponse> me(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(UserDetailsResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .created_at(user.getCreated_at())
                .updated_at(user.getUpdated_at())
                .build()
        );
    }

}
