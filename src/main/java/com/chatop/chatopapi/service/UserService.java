package com.chatop.chatopapi.service;

import com.chatop.chatopapi.dto.request.RegisterRequest;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> getUser(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email){
        return this.userRepository.findByEmail(email);
    }

    public Optional<User> findById(Long id){
        return this.userRepository.findById(id);
    }

    public User createUser(User user){
        return this.userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = this.userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("user not found")
        );

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .authorities(List.of(new SimpleGrantedAuthority("USER")))
                .build();
    }
}
