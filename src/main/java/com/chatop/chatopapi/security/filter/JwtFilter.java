package com.chatop.chatopapi.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.chatop.chatopapi.model.User;
import com.chatop.chatopapi.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    private final String JWT_PREFIX = "Bearer ";


    private String getEmailFromJwt(String jwt){
        if(jwt == null){
            throw new JWTVerificationException("no token");
        }
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256("")).build();
        return verifier.verify(jwt).getSubject();
    }

    private String getJwtFromReq(HttpServletRequest request){
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith(this.JWT_PREFIX)){
            return null;
        }
        return header.replace(this.JWT_PREFIX, "");
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String jwt = this.getJwtFromReq(request);

        if(jwt == null){
            filterChain.doFilter(request, response);
            return;
        }

        final String email = this.getEmailFromJwt(jwt);
        final Optional<User> user = this.userService.findByEmail(email);

        final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, null);

        SecurityContextHolder.getContext().setAuthentication(auth);

        filterChain.doFilter(request, response);

    }
}